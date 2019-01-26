package com.example.joyce.translateme.data

import com.example.joyce.translateme.data.models.RegistrationResponse
import com.example.joyce.translateme.data.models.User
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("register")
    fun register(@Body user: User): Single<RegistrationResponse>

    @POST("login")
    fun login(@Body user: User): Single<RegistrationResponse>

    @POST("user/wait")
    fun requestHelp(@Body user: User)

    @POST("translator/wait")
    fun checkForRequest(@Body user: User)

}