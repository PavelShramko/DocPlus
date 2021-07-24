package com.example.docplus.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.docplus.data.db.AppDataBase
import com.example.docplus.domain.Doctor
import com.example.docplus.domain.DoctorRepository
import com.example.docplus.data.repository.DoctorRepositoryImpl
import kotlinx.coroutines.*

private val empty = Doctor(
    id = 0,
    type = "",
    name = "",
    time = ""
    //visits = null
)

class ListDoctorViewModel(application: Application) : AndroidViewModel(application)  {

    private val repository: DoctorRepository = DoctorRepositoryImpl(
        AppDataBase.getInstance(context = application).docDao()
    )

    val data = repository.getAll()
    val edited = MutableLiveData(empty)

    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
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

    fun removeById(id: Long) = repository.removeById(id)

    // Функция, которая будет открывать историю посещений
    fun click (doctor: Doctor){
        Log.d("kekpek", "${doctor.id} ${doctor.name}  ${doctor.type}")
    }
}