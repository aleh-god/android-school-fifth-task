package by.godevelopment.fifthtask.data

import by.godevelopment.fifthtask.data.dataentity.DataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface BankApi {

    // https://belarusbank.by/api/atm?city=%D0%93%D0%BE%D0%BC%D0%B5%D0%BB%D1%8C
    // https://belarusbank.by/open-banking/v1.0/atms?city=%D0%93%D0%BE%D0%BC%D0%B5%D0%BB%D1%8C
    @GET("atms")
    suspend fun getAllDataByCity(@Query("city") key: String): DataModel
}