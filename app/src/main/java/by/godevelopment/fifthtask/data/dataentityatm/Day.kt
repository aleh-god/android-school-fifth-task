package by.godevelopment.fifthtask.data.dataentityatm


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Day(
    @Json(name = "Break")
    val breakX: Break?,
    @Json(name = "closingTime")
    val closingTime: String?,
    @Json(name = "dayCode")
    val dayCode: String?,
    @Json(name = "openingTime")
    val openingTime: String?
)