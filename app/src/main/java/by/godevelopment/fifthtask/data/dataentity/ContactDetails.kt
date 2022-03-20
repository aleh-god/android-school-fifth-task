package by.godevelopment.fifthtask.data.dataentity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContactDetails(
    @Json(name = "phoneNumber")
    val phoneNumber: String?
)