package by.godevelopment.fifthtask.domain

import by.godevelopment.fifthtask.data.dataentityatm.DataModel
import by.godevelopment.fifthtask.data.dataentityfilial.DataModelFilialItem
import by.godevelopment.fifthtask.data.dataentityinfobox.DataModelInfoboxItem

interface BankRepository {

    suspend fun getAllAtm(): DataModel
    suspend fun getAllFilial(): List<DataModelFilialItem>
    suspend fun getAllInfobox(): List<DataModelInfoboxItem>
}