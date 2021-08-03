package com.example.docplus.presentation.viewmodel

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.*
import com.example.docplus.data.db.AppDataBase
import com.example.docplus.data.repository.DoctorRepositoryImpl
import com.example.docplus.di.DaggerAppComponent
import com.example.docplus.domain.Doctor
import com.example.docplus.domain.DoctorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

private val empty = Doctor(
    id = 0,
    type = "",
    name = "",
    time = ""
    //visits = null
)

// TODO: нужно быть уверенным что тебе действительно нужен контекст внутри твоей вью модели.
class ListDoctorViewModel(application: Application) : AndroidViewModel(application) {

    //    private var _listOfDoctors = MutableLiveData<List<Doctor>>()
    val listOfDoctors: LiveData<List<Doctor>>
        get() = getDoctorsLiveData()

    // Перенести в диай
    /*private val repository: DoctorRepository = DoctorRepositoryImpl(
        AppDataBase.getInstance(context = application).docDao(
        )
    )*/

    @Inject
    lateinit var repository: DoctorRepository

    // TODO: the initial way to create repo - can be removed
    // var repository: DoctorRepository = DoctorRepositoryImpl(AppDataBase.getInstance(application).docDao())

    // TODO lead to java.lang.Object android.content.Context.getSystemService(java.lang.String)' on a null object reference
//    val repository = appComponent.repository

    val edited = MutableLiveData(empty)

    fun getDoctorsLiveData(): LiveData<List<Doctor>> {
        return repository.getAll()
    }

    /*init {
        _listOfDoctors = liveData<List<Doctor>> {
            repository.getAll()
        } as MutableLiveData<List<Doctor>>
        listOfDoctors = _listOfDoctors

        Log.d("Kekpek2", listOfDoctors.value.toString())
        Log.d("Kekpek3", _listOfDoctors.value.toString())

    }*/

    fun save() {
        viewModelScope.launch(Dispatchers.IO) {
            edited.value?.let {
                repository.save(it)
            }
            edited.value = empty
        }
        Log.d("Kekpek4", edited.value.toString())
    }

    fun changeContent(type: String, name: String, time: String) {
        val editType = type.trim()
        val editName = name.trim()
        val edinTime = time.trim()
        if (edited.value?.type == editType &&
            edited.value?.name == editName &&
            edited.value?.time == edinTime
        ) {
            return
        }
        edited.value = edited.value?.copy(type = editType, name = editName, time = edinTime)
    }

    fun removeById(id: Long) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            repository.removeById(id)
        }
    }

    fun edit(doctor: Doctor) {
        // юзкейс
    }

    // Функция, которая будет открывать историю посещений
    fun click(doctor: Doctor) {
        Log.d("kekpek", "${doctor.id} ${doctor.name}  ${doctor.type}")
    }
}