package com.example.docplus.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.docplus.data.model.DoctorEntity
import com.example.docplus.data.dao.DoctorDao
import com.example.docplus.domain.Doctor
import com.example.docplus.domain.DoctorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DoctorRepositoryImpl @Inject constructor(
    private val dao: DoctorDao
) : DoctorRepository {

    override fun getAll(): LiveData<List<Doctor>> {
        return Transformations.map(dao.getAll()) { list ->
            list.map {
                // добавить лог
                Doctor(it.id, it.type, it.name, it.time)
            }
        }
    }

    /*override suspend fun getAll(): LiveData<List<Doctor>> {
        return withContext(Dispatchers.IO) {
            Transformations.map(dao.getAll()) { list ->
                list.map {
                    // добавить лог
                    Doctor(it.id, it.type, it.name, it.time)
                }
            }
        }
    }*/

    override suspend fun save(doctor: Doctor) =
        withContext(Dispatchers.IO) {
            dao.save(DoctorEntity.fromDto(doctor))
        }

    override suspend fun removeById(id: Long) =
        withContext(Dispatchers.IO) {
            dao.removeById(id)
        }

    override suspend fun updateContentById(
        id: Long,
        type: String,
        name: String,
        time: String
        //visits: List<Long>
    ) = withContext(Dispatchers.IO) {
        dao.updateContentById(id, type, name, time/*, visits*/)
    }
}