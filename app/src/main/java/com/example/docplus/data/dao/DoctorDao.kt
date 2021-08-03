package com.example.docplus.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.docplus.data.model.DoctorEntity

@Dao
interface DoctorDao {

    @Query("SELECT * FROM DoctorEntity ORDER BY id DESC")
    fun getAll(): LiveData<List<DoctorEntity>>

    @Query("SELECT * FROM DoctorEntity WHERE id = :id")
    fun getById(id:Long): DoctorEntity

    @Insert
    fun insert(doctor: DoctorEntity)

    // TODO 1: метод хорош с точки зрения инкапсуляции логики в ДАО, но предполагаю что есть более элегантный способ
    // решения такой задачи с использованием аннотаций при конфликтах. См. @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(doctor: DoctorEntity) =
        if (doctor.id == 0L) insert(doctor) else updateContentById(doctor.id, doctor.type, doctor.name, doctor.time)

    //@Query("UPDATE DoctorEntity SET type = :type AND name = :name AND time = :time AND visits = :visits WHERE id = :id")
    @Query("UPDATE DoctorEntity SET type = :type AND name = :name AND time = :time WHERE id = :id")
    fun updateContentById(
        id: Long?,
        type: String,
        name: String,
        time: String
        //visits: List<Long>?
    )

    @Query("DELETE FROM DoctorEntity WHERE id = :id")
    fun removeById(id: Long)
}