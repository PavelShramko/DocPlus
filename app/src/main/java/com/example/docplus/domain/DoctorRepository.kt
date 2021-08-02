package com.example.docplus.domain

import androidx.lifecycle.LiveData
import com.example.docplus.data.model.DoctorEntity
import com.example.docplus.domain.Doctor

interface DoctorRepository {
    fun getAll(): LiveData<List<Doctor>>
    //suspend fun getAll(): LiveData<List<Doctor>>
    suspend fun save(doctor: Doctor)
    suspend fun removeById(id: Long)
    suspend fun updateContentById(id: Long, type: String, name: String, time: String/*, visits: List<Long>*/)
}