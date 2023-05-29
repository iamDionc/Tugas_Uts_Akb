package com.example.catatan10120085.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
//10120085 - Dion Cahyan IF-2
@Database(
    entities = [
        Note::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dataDao() : DataDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "${context.packageName}.db"
                    ).build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}