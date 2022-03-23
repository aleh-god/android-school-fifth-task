package by.godevelopment.fifthtask.data.dataentity.dataentityatm


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "ATM")
    val aTM: List<ATM>
)