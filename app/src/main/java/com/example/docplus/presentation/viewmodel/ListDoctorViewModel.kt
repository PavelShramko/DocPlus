package com.example.docplus.presentation.viewmodel

import android.app.Application
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

class ListDoctorViewModel(application: Application) : AndroidViewModel(application) {

    var _listOfDoctors = MutableLiveData<List<Doctor>>()
    var listOfDoctors: LiveData<List<Doctor>>
    val appComponent = DaggerAppComponent.create()

    // Перенести в диай
    /*private val repository: DoctorRepository = DoctorRepositoryImpl(
        AppDataBase.getInstance(context = application).docDao(
        )
    )*/

    var repository: DoctorRepository = appComponent.repository

    val edited = MutableLiveData(empty)

    init {
        _listOfDoctors = repository.getAll() as MutableLiveData<List<Doctor>>
        listOfDoctors = _listOfDoctors

        Log.d("Kekpek2", listOfDoctors.value.toString())
        Log.d("Kekpek3", _listOfDoctors.value.toString())
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
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                edited.value?.let {
                    repository.save(it)
                }
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