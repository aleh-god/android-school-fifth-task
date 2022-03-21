package by.godevelopment.fifthtask.data

import by.godevelopment.fifthtask.commons.CITY_KEY
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val bankApi: BankApi
) {
    suspend fun getAllAtm() = bankApi.getAllAtmByCity(CITY_KEY)
    suspend fun getAllFilial() = bankApi.getAllFilialByCity(CITY_KEY)
    suspend fun getAllInfobox() = bankApi.getAllInfoboxByCity(CITY_KEY)
}