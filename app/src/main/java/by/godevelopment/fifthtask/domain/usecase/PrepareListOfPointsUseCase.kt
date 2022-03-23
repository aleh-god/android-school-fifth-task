package by.godevelopment.fifthtask.domain.usecase

import by.godevelopment.fifthtask.R
import by.godevelopment.fifthtask.commons.*
import by.godevelopment.fifthtask.domain.models.GeographicPointModel
import by.godevelopment.fifthtask.domain.helpers.StringHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PrepareListOfPointsUseCase @Inject constructor(
    private val calcDistanceBetweenPointsAndCenterUseCase: CalcDistanceBetweenPointsAndCenterUseCase,
    private val stringHelper: StringHelper
) {
    operator fun invoke(): Flow<List<GeographicPointModel>> =
        calcDistanceBetweenPointsAndCenterUseCase().map { list ->
            val sortedList = list
                .filter {
                    it.distance != KEY_DISTANCE_TO_CENTER_POINT
                }
                .sortedBy {
                    it.distance
                }
            val takeList = sortedList
                .take(KEY_TAKE_POINTS_NAMBER)
                .toMutableList()

            takeList.add(
                GeographicPointModel(
                id = KEY_ID_CENTER_POINT,
                tittle_type = stringHelper.getString(R.string.usecase_text_center_point),
                snippet_address = stringHelper.getString(R.string.usecase_text_center_point),
                distance = KEY_DISTANCE_TO_CENTER_POINT,
                latitude = START_POINT_LAT,
                longitude = START_POINT_LNG
            )
            )
            takeList
        }
}