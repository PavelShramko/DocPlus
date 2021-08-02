package com.example.docplus.di

import android.app.Application
import com.example.docplus.data.db.AppDataBase
import com.example.docplus.data.repository.DoctorRepositoryImpl
import com.example.docplus.domain.DoctorRepository
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [RepositoryModule::class])
interface AppComponent {
    val repository: DoctorRepository
}

@Module
class RepositoryModule{

    @Provides
    fun provideRepository(application: Application): DoctorRepository {
        return DoctorRepositoryImpl(
            AppDataBase.getInstance(context = application).docDao()
        )
    }

    @Provides
    fun provideApplication(): Application {
        return Application()
    }
}