package by.godevelopment.fifthtask.data

import by.godevelopment.fifthtask.commons.CITY_KEY
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val bankApi: BankApi
) {
    suspend fun getAllData() = bankApi.getAllDataByCity(CITY_KEY)
}