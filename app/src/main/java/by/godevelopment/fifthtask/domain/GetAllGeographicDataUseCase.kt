package by.godevelopment.fifthtask.domain

import by.godevelopment.fifthtask.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllGeographicDataUseCase @Inject constructor(
    private val bankRepository: BankRepository,
    private val stringHelper: StringHelper
) {
    operator fun invoke(): Flow<List<GeographicPointModel>> = flow {
        val allDataList = bankRepository.getAllData().data.aTM
        var countId = 0
        emit(
            allDataList.map { atm ->
                val atmAddress = atm.address
                atmAddress?.geolocation?.geographicCoordinates?.latitude?.let {
                    atmAddress.geolocation.geographicCoordinates.longitude?.let {
                        GeographicPointModel(
                            id = countId++,
                            tittle_text = atm.atmId +
                                    atm.currentStatus,
                            snippet_text = atm.address.streetName +
                                    atm.address.buildingNumber,
                            latitude = atmAddress.geolocation.geographicCoordinates.latitude,
                            longitude = atmAddress.geolocation.geographicCoordinates.longitude
                        )
                    }
                } !!
            }
        )
    }
}