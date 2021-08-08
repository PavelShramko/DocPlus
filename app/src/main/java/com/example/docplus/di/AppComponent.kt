package com.example.docplus.di

import android.content.Context
import com.example.docplus.data.dao.DoctorDao
import com.example.docplus.data.db.AppDataBase
import com.example.docplus.data.repository.DoctorRepositoryImpl
import com.example.docplus.domain.DoctorRepository
import com.example.docplus.domain.useCase.UseCaseRemove
import com.example.docplus.domain.useCase.UseCaseSaveAndEditDoctor
import com.example.docplus.presentation.viewmodel.ListDoctorViewModel
import dagger.*

@Component(modules = [
    RepositoryModule::class,
    DataBaseModule::class
])
interface AppComponent {

    @Component.Builder
    abstract class Builder {
        @BindsInstance
        abstract fun bindContext(context: Context): Builder
        abstract fun build(): AppComponent
    }

    fun inject(viewModel: ListDoctorViewModel)
}

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindDoctorRepository(repo: DoctorRepositoryImpl): DoctorRepository
}

@Module
class DataBaseModule {

    @Provides
    fun provideDataBase(context: Context): DoctorDao {
        return AppDataBase.getInstance(context = context).doctorDao()
    }
}