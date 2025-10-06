package com.fake.travelchatbot

// Request.kt
data class ChatRequest(
    val query: String
)

// Response.kt
data class ChatResponse(
    val response: String
)
