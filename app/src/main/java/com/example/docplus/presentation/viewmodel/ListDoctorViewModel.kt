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
)

class ListDoctorViewModel(application: Application) : AndroidViewModel(application) {

    val listOfDoctors: LiveData<List<Doctor>>
        get() = getDoctorsLiveData()

    @Inject
    lateinit var repository: DoctorRepository

    val edited = MutableLiveData(empty)

    fun getDoctorsLiveData(): LiveData<List<Doctor>> {
        return repository.getAll()
    }

    fun save() {
        viewModelScope.launch(Dispatchers.IO) {
            edited.value?.let {
                repository.save(it)
            }
            edited.value = empty
        }
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