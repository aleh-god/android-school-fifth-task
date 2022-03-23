package by.godevelopment.fifthtask.domain.usecase

import android.location.Location
import android.util.Log
import by.godevelopment.fifthtask.commons.*
import by.godevelopment.fifthtask.domain.models.GeographicPointModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CalcDistanceBetweenPointsAndCenterUseCase @Inject constructor(
    private val getAllGeographicDataUseCase: GetAllGeographicDataUseCase
) {
    operator fun invoke(): Flow<List<GeographicPointModel>> =
        getAllGeographicDataUseCase().map { oldList ->
            oldList
                .map { model ->
                    val result = FloatArray(3)
                    model.latitude?.let {
                        model.longitude?.let {
                            Location.distanceBetween(START_POINT_LAT, START_POINT_LNG,model.latitude, model.longitude, result)
                            Log.i(TAG, "CalcDistance: ${model.snippet_address} = $result")
                        }
                    }
                    model.copy(
                        distance = result[0]
                    )
                }
        }
}