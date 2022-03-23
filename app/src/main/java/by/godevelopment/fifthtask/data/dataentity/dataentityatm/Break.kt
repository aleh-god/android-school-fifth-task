package by.godevelopment.fifthtask.data.dataentity.dataentityatm


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Break(
    @Json(name = "breakFromTime")
    val breakFromTime: String?,
    @Json(name = "breakToTime")
    val breakToTime: String?
)