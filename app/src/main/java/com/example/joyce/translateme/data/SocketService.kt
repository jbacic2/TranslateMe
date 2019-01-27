package com.example.joyce.translateme.data

import com.example.joyce.translateme.data.models.SocketMessage
import com.example.joyce.translateme.data.models.User
import com.example.joyce.translateme.data.models.UserPlan
import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import io.reactivex.Flowable

interface SocketService {

    @Receive
    fun observeWebSocketEvent(): Flowable<WebSocket.Event>

    @Send
    fun logon(user: SocketMessage)

    @Send
    fun sendLocationUpdate(user: SocketMessage)

    @Receive
    fun observePlan(): Flowable<UserPlan>

    @Receive
    fun observeLocationUpdates(): Flowable<User>

}