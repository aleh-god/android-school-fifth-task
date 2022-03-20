package by.godevelopment.fifthtask.data.dataentity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GeographicCoordinates(
    @Json(name = "latitude")
    val latitude: Double?, // String?,
    @Json(name = "longitude")
    val longitude: Double? // String?
)