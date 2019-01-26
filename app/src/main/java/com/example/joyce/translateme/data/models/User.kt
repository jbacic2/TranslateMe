package com.example.joyce.translateme.data.models

data class User(val username: String,
                val name: String,
                val role: Role = Role.OFFLINE,
                val matched: Boolean = false,
                val location: LatLng? = null)

data class UserPair(val user: User,
                    val translator: User)

enum class Role {
    TRANSLATOR,
    USER,
    OFFLINE
}