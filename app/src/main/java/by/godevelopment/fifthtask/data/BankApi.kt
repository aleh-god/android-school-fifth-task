package by.godevelopment.fifthtask.data

import by.godevelopment.fifthtask.data.dataentity.dataentityatm.DataModel
import by.godevelopment.fifthtask.data.dataentity.dataentityfilial.DataModelFilialItem
import by.godevelopment.fifthtask.data.dataentity.dataentityinfobox.DataModelInfoboxItem
import retrofit2.http.GET
import retrofit2.http.Query

interface BankApi {

    // https://belarusbank.by/api/atm?city=%D0%93%D0%BE%D0%BC%D0%B5%D0%BB%D1%8C
    // https://belarusbank.by/open-banking/v1.0/atms?city=%D0%93%D0%BE%D0%BC%D0%B5%D0%BB%D1%8C
    @GET("open-banking/v1.0/atms")
    suspend fun getAllAtmByCity(@Query("city") key: String): DataModel

    // https://belarusbank.by/api/filials_info?city=%D0%93%D0%BE%D0%BC%D0%B5%D0%BB%D1%8C
    @GET("api/filials_info")
    suspend fun getAllFilialByCity(@Query("city") key: String): List<DataModelFilialItem>

    // https://belarusbank.by/api/infobox?city=%D0%93%D0%BE%D0%BC%D0%B5%D0%BB%D1%8C
    @GET("api/infobox")
    suspend fun getAllInfoboxByCity(@Query("city") key: String): List<DataModelInfoboxItem>
}