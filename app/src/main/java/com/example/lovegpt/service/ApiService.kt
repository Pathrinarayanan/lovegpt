package com.example.lovegpt.service

import com.example.lovegpt.model.ChatRequest
import com.example.lovegpt.model.ChatResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers(
        "Authorization: Bearer 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",
        "Content-Type: application/json"
    )

    @POST("chat/completions")
    suspend fun getData(@Body chatReq : ChatRequest): ChatResponse
}