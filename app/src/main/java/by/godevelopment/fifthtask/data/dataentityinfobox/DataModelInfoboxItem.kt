package by.godevelopment.fifthtask.data.dataentityinfobox


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataModelInfoboxItem(
    @Json(name = "address")
    val address: String?,
    @Json(name = "address_type")
    val addressType: String?,
    @Json(name = "area")
    val area: String?,
    @Json(name = "cash_in")
    val cashIn: String?,
    @Json(name = "cash_in_exist")
    val cashInExist: String?,
    @Json(name = "city")
    val city: String?,
    @Json(name = "city_type")
    val cityType: String?,
    @Json(name = "currency")
    val currency: String?,
    @Json(name = "gps_x")
    val gpsX: String?,
    @Json(name = "gps_y")
    val gpsY: String?,
    @Json(name = "house")
    val house: String?,
    @Json(name = "inf_printer")
    val infPrinter: String?,
    @Json(name = "inf_status")
    val infStatus: String?,
    @Json(name = "inf_type")
    val infType: String?,
    @Json(name = "info_id")
    val infoId: Int?,
    @Json(name = "install_place")
    val installPlace: String?,
    @Json(name = "location_name_desc")
    val locationNameDesc: String?,
    @Json(name = "popolnenie_platej")
    val popolneniePlatej: String?,
    @Json(name = "region_platej")
    val regionPlatej: String?,
    @Json(name = "time_long")
    val timeLong: String?,
    @Json(name = "type_cash_in")
    val typeCashIn: String?,
    @Json(name = "work_time")
    val workTime: String?
)