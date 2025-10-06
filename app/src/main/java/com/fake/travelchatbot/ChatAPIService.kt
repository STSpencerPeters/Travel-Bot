package com.fake.travelchatbot

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ChatApiService {

    @Headers("Content-Type: application/json")
    @POST("chat") // Replace with your endpoint path
    fun getChatResponse(@Body request: ChatRequest): Call<ChatResponse>
}
