package by.godevelopment.fifthtask.domain

import by.godevelopment.fifthtask.data.dataentity.DataModel

interface BankRepository {

    suspend fun getAllData(): DataModel
}