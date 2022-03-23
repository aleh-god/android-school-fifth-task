package by.godevelopment.fifthtask.data.dataentity.dataentityatm

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ATM(
    @Json(name = "Accessibilities")
    val accessibilities: Accessibilities?,
    @Json(name = "Address")
    val address: Address?,
    @Json(name = "atmId")
    val atmId: String?,
    @Json(name = "Availability")
    val availability: Availability?,
    @Json(name = "baseCurrency")
    val baseCurrency: String?,
    @Json(name = "cards")
    val cards: List<String>,
    @Json(name = "ContactDetails")
    val contactDetails: ContactDetails?,
    @Json(name = "currency")
    val currency: String?,
    @Json(name = "currentStatus")
    val currentStatus: String?,
    @Json(name = "Services")
    val services: List<Service>,
    @Json(name = "type")
    val type: String?
)