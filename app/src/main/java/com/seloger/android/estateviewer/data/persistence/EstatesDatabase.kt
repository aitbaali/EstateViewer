package com.seloger.android.estateviewer.data.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.seloger.android.estateviewer.data.entity.Estate

@Database(entities = [Estate::class], version = 1)
abstract class EstatesDatabase : RoomDatabase() {

    abstract fun estateDao(): EstateDao

    companion object {
        @Volatile
        private var INSTANCE: EstatesDatabase? = null

        fun getInstance(context: Context): EstatesDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                EstatesDatabase::class.java,
                "estates_db"
            ).build()
    }
}