package com.example.docplus.di

import android.content.Context
import com.example.docplus.data.dao.DoctorDao
import com.example.docplus.data.db.AppDataBase
import com.example.docplus.data.repository.DoctorRepositoryImpl
import com.example.docplus.domain.DoctorRepository
import com.example.docplus.presentation.viewmodel.ListDoctorViewModel
import dagger.*

@Component(modules = [RepositoryModule::class, DataBaseModule::class])
interface AppComponent {
    //    val repository: DoctorRepository

    @Component.Builder
    abstract class Builder {
        @BindsInstance
        abstract fun bindContext(context: Context): Builder
        abstract fun build(): AppComponent
    }

//    @Component.Factory
//    abstract class Factory {
//        abstract fun create(@BindsInstance context: Context) : AppComponent
//    }

    fun inject(viewModel: ListDoctorViewModel)
}

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindDoctorRepository(repo: DoctorRepositoryImpl): DoctorRepository


//  TODO lead to java.lang.Object android.content.Context.getSystemService(java.lang.String)' on a null object reference
    // здесь ты создаешь какой-то новый объект application, у которого нет никакой связи с твоим app. И контектста внутри него нет,
    // если заглянешь в реализацию этого конструктора сразу поймёшь почему
//    @Provides
//    fun provideApplication(): Application {
//        return DocPlusApp()
//    }
}

@Module
class DataBaseModule {

    @Provides
    fun provideDataBase(context: Context): DoctorDao {
        return AppDataBase.getInstance(context = context).doctorDao()
    }
}