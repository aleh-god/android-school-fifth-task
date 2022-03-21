package by.godevelopment.fifthtask.domain

import android.util.Log
import by.godevelopment.fifthtask.R
import by.godevelopment.fifthtask.commons.TAG
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllGeographicDataUseCase @Inject constructor(
    private val bankRepository: BankRepository,
    private val stringHelper: StringHelper
) {
    suspend operator fun invoke(): Flow<List<GeographicPointModel>> = flow {
        val fetchList = mutableListOf<Deferred<List<GeographicPointModel>>>()
        coroutineScope {
            Log.i(TAG, "GetAllGeographicDataUseCas .launch")
            val deferredAtm  = async {
                Log.i(TAG, "GetAllGeographicDataUseCas deferredAtm")
                var count = 0
                bankRepository.getAllAtm().data.aTM.map {
                    GeographicPointModel(
                        id = count++,
                        tittle_type = stringHelper.getString(R.string.usecase_type_atm),
                        snippet_address =
                                it.address?.streetName +
                                stringHelper.getString(R.string.usecase_text_separator) +
                                it.address?.buildingNumber,
                        distance = null,
                        latitude = it.address?.geolocation?.geographicCoordinates?.latitude,
                        longitude = it.address?.geolocation?.geographicCoordinates?.longitude
                    )
                }
            }
            fetchList.add(deferredAtm)
            val deferredFilial = async {
                Log.i(TAG, "GetAllGeographicDataUseCas deferredFilial")
                var count = 0
                bankRepository.getAllFilial().map {
                    GeographicPointModel(
                        id = count++,
                        tittle_type = stringHelper.getString(R.string.usecase_type_filial),
                        snippet_address =
                                it.street +
                                stringHelper.getString(R.string.usecase_text_separator) +
                                it.homeNumber,
                        distance = null,
                        latitude = it.gPSX?.toDoubleOrNull(),
                        longitude = it.gPSY?.toDoubleOrNull()
                    )
                }
            }
            fetchList.add(deferredFilial)
            val deferredInfobox = async {
                Log.i(TAG, "GetAllGeographicDataUseCas deferredInfobox")
                var count = 0
                bankRepository.getAllInfobox().map {
                    GeographicPointModel(
                        id = count++,
                        tittle_type = stringHelper.getString(R.string.usecase_type_infobox),
                        snippet_address =
                                it.address +
                                stringHelper.getString(R.string.usecase_text_separator) +
                                it.house,
                        distance = null,
                        latitude = it.gpsX?.toDoubleOrNull(),
                        longitude = it.gpsY?.toDoubleOrNull()
                    )
                }
            }
            fetchList.add(deferredInfobox)
            val result = fetchList
                .awaitAll()
                .fold(listOf<GeographicPointModel>()) {
                sum, element ->
                sum + element
            }
            Log.i(TAG, "invoke: result = ${result.size}")
            emit(result)
        }
    }
}