package com.example.docplus.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.docplus.domain.Doctor
import com.example.docplus.domain.DoctorRepository
import com.example.docplus.domain.useCase.UseCaseRemove
import com.example.docplus.domain.useCase.UseCaseSaveAndEditDoctor
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

class ListDoctorViewModel: ViewModel() {

    @Inject
    lateinit var repository: DoctorRepository

    @Inject
    lateinit var useCaseSaveAndEditDoctor: UseCaseSaveAndEditDoctor

    @Inject
    lateinit var useCaseRemove: UseCaseRemove

    fun getDoctorsLiveData(): LiveData<List<Doctor>> {
        return repository.getAll()
    }

    fun save() = useCaseSaveAndEditDoctor.save()

    fun changeContent(type: String, name: String, time: String) =
        useCaseSaveAndEditDoctor.changeContent(type, name, time)

    fun removeById(id: Long) = useCaseRemove.removeById(id)

    fun edit(id: Long,
             type: String,
             name: String,
             time: String
    ) = useCaseSaveAndEditDoctor.updateContentById(id, type, name, time)


    // Функция, которая будет открывать историю посещений
    fun click(doctor: Doctor) {
        Log.d("kekpek", "${doctor.id} ${doctor.name}  ${doctor.type}")
    }
}
