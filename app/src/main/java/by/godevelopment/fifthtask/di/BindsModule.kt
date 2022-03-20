package by.godevelopment.fifthtask.di

import by.godevelopment.fifthtask.data.BankRepositoryImpl
import by.godevelopment.fifthtask.domain.BankRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsModule {

    @Binds
    abstract fun bindRepositoryToImpl(bankRepositoryImpl: BankRepositoryImpl) : BankRepository
}