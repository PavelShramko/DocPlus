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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job


// Добавить скоуп
// Добавить навигацию в рамках датабиндинга
// Добить юз кейс с редактированием
// Раскидать на несколько модулей этот файл
// Добавить юзкейс фильтрации по буквам
// Вью модель с инжектом через конструктор и фэктори
// Сделать экран со своим компонентом
// В докторе дао сделать функции саспенд, скоуп сделать только во вьюмоели


@Component(modules = [
    RepositoryModule::class,
    DataBaseModule::class/*,
    ScopeModule::class*/
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

    //repo - тик, который нужно создать
    // DoctorRepository - то, что вернет
    @Binds
    abstract fun bindDoctorRepository(repo: DoctorRepositoryImpl): DoctorRepository
}

/*@Module
class ScopeModule {

Подумать над тем, чтобы делать несколько скоупов - для юая и для БД
Привет был в бестпрактис
    @Provides
    fun bindScope() : CoroutineScope = CoroutineScope(Dispatchers.Main.immediate + Job())
}*/

@Module
class DataBaseModule {

    @Provides
    fun provideDataBase(context: Context): DoctorDao {
        return AppDataBase.getInstance(context = context).doctorDao()
    }
}