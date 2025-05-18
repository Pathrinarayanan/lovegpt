package com.example.lovegpt.model

data class ChatRequest(
    val model : String?,
    val messages : List<ReqMessages>
)
data class ReqMessages(
    val role :String?,
    val content : String?
)