package by.godevelopment.fifthtask.data.dataentity.dataentityatm


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Availability(
    @Json(name = "access24Hours")
    val access24Hours: Boolean?,
    @Json(name = "isRestricted")
    val isRestricted: Boolean?,
    @Json(name = "sameAsOrganization")
    val sameAsOrganization: Boolean?,
    @Json(name = "StandardAvailability")
    val standardAvailability: StandardAvailability?
)