package com.example.docplus.data.repository

import androidx.lifecycle.Transformations
import com.example.docplus.data.model.DoctorEntity
import com.example.docplus.data.dao.DoctorDao
import com.example.docplus.domain.Doctor
import com.example.docplus.domain.DoctorRepository

class DoctorRepositoryImpl(
    private val dao: DoctorDao
) : DoctorRepository {

    override fun getAll() = Transformations.map(dao.getAll()) { list ->
        list.map {
            Doctor(it.id, it.type, it.name, it.time /*it.visits*/)
        }
    }

    override fun save(doctor: Doctor) {
        dao.save(DoctorEntity.fromDto(doctor))
    }

    override fun removeById(id: Long) {
        dao.removeById(id)
    }

    override fun updateContentById(
        id: Long,
        type: String,
        name: String,
        time: String
        //visits: List<Long>
    ) {
        dao.updateContentById(id, type, name, time/*, visits*/)
    }
}