package by.godevelopment.fifthtask.domain

import by.godevelopment.fifthtask.data.dataentity.dataentityatm.DataModel
import by.godevelopment.fifthtask.data.dataentity.dataentityfilial.DataModelFilialItem
import by.godevelopment.fifthtask.data.dataentity.dataentityinfobox.DataModelInfoboxItem

interface BankRepository {

    suspend fun getAllAtm(): DataModel
    suspend fun getAllFilial(): List<DataModelFilialItem>
    suspend fun getAllInfobox(): List<DataModelInfoboxItem>
}