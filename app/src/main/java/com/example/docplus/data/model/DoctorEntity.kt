package com.example.docplus.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import com.example.docplus.domain.Doctor


@Entity
data class DoctorEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var type: String,
    var name: String,
    var time: String
    //var visits: List<Long>? = null
) : Serializable {

    fun toDto() = Doctor(id, type, name, time)

    companion object {
        fun fromDto(dto: Doctor) =
            DoctorEntity(dto.id, dto.type, dto.name, dto.time)
    }
}