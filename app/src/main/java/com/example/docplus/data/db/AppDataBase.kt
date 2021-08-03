package com.example.docplus.data.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.docplus.data.dao.DoctorDao
import com.example.docplus.data.model.DoctorEntity

@Database(entities = [DoctorEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun doctorDao(): DoctorDao

    companion object {
        @Volatile
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) =
            // TODO для чистоты и красоты стоит "appdata.db" и version = 1 вынести в константы
            Room.databaseBuilder(context, AppDataBase::class.java, "appdata.db").build()
    }
}