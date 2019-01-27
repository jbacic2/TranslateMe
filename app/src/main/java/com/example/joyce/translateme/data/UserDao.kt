package com.example.joyce.translateme.data

import androidx.room.*
import com.example.joyce.translateme.data.models.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users LIMIT 1")
    fun getUser(): User?

    @Insert
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

}