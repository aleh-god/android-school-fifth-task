package by.godevelopment.fifthtask.data

import by.godevelopment.fifthtask.domain.BankRepository
import javax.inject.Inject

class BankRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BankRepository {
    override suspend fun getAllData() = remoteDataSource.getAllData()
}