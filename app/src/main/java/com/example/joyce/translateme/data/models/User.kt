package com.example.joyce.translateme.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.mapbox.mapboxsdk.geometry.LatLng

@Entity(tableName = "users")
data class User(@PrimaryKey @ColumnInfo(name = "username") val username: String,
                @ColumnInfo(name = "name") val name: String,
                @ColumnInfo(name = "role") val role: Role = Role.OFFLINE,
                @ColumnInfo(name = "longitude") val longitude: Double? = null,
                @ColumnInfo(name = "latitude") val latitude: Double? = null) {

    fun getLatLng(): LatLng? =
            if (longitude == null || latitude == null) null else LatLng(longitude, latitude)

}

data class UserPair(val user: User,
                    val translator: User)

data class UserPlan(val user: User,
                    val path: String?)

enum class Role {
    TRANSLATOR,
    USER,
    OFFLINE
}

object RoleTypeConverter {
    @JvmStatic
    @TypeConverter
    fun toInteger(role: Role) = role.ordinal

    @JvmStatic
    @TypeConverter
    fun toRole(value: Int) = Role.values()[value]
}