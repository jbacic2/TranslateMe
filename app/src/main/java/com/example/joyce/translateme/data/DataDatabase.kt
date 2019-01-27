package com.example.joyce.translateme.data

import android.content.Context
import androidx.room.*
import com.example.joyce.translateme.data.models.RoleTypeConverter
import com.example.joyce.translateme.data.models.User

@Database(entities = [User::class], version = 1)
@TypeConverters(RoleTypeConverter::class)
abstract class DataDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao

    companion object {
        @Volatile private var INSTANCE: DataDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): DataDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE
                            ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context): DataDatabase =
                Room.databaseBuilder(context.applicationContext,
                        DataDatabase::class.java, "data.db")
                        .allowMainThreadQueries()
                        .build()
    }
}