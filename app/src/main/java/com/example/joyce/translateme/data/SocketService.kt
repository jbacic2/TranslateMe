package com.example.joyce.translateme.data

import com.example.joyce.translateme.data.models.User
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import io.reactivex.Flowable

interface SocketService {

    @Send
    fun sendLocationUpdate(user: User)

    @Receive
    fun observeLocationUpdates(): Flowable<List<User>>

}