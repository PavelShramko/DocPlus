package com.example.docplus.domain

import androidx.lifecycle.LiveData
import com.example.docplus.domain.Doctor

interface DoctorRepository {
    fun getAll(): LiveData<List<Doctor>>
    fun save(doctor: Doctor)
    fun removeById(id: Long)
    fun updateContentById(id: Long, type: String, name: String, time: String/*, visits: List<Long>*/)
}