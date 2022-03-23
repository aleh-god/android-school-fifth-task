package by.godevelopment.fifthtask.domain

import android.location.Location
import android.util.Log
import by.godevelopment.fifthtask.commons.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CalcDistanceBetweenPointsAndCenterUseCase @Inject constructor(
    private val getAllGeographicDataUseCase: GetAllGeographicDataUseCase
) {
    operator fun invoke(): Flow<List<GeographicPointModel>> =
        getAllGeographicDataUseCase().map { oldList ->
            val mapList = oldList
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
            Log.i(TAG, "CalcDistance: .map ${mapList.size}")
            val filterList = mapList.filter {
                it.distance != KEY_DISTANCE_TO_CENTER_POINT
            }
            Log.i(TAG, "CalcDistance: .filter ${filterList.size}")
            val sortedList = filterList.sortedBy {
                it.distance
            }
            Log.i(TAG, "CalcDistance: ${sortedList.size}")
            val takeList = sortedList
                .take(KEY_TAKE_POINTS_NAMBER)
                .toMutableList()

            takeList.add(GeographicPointModel(
                id = KEY_ID_CENTER_POINT,
                tittle_type = "Center point",
                snippet_address = "Center point",
                distance = KEY_DISTANCE_TO_CENTER_POINT,
                latitude = START_POINT_LAT,
                longitude = START_POINT_LNG
            )
            )
            Log.i(TAG, "CalcDistance: finish = $takeList")
            takeList
        }
}