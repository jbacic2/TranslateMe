package com.example.joyce.translateme.data.models

data class SocketMessage(val data: User,
                         val type: String = "locationUpdate")
