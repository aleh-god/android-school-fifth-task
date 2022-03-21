package by.godevelopment.fifthtask.data.dataentityatm


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StandardAvailability(
    @Json(name = "Day")
    val day: List<Day>?
)