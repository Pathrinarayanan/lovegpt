package com.example.lovegpt

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lovegpt.model.ChatRequest
import com.example.lovegpt.model.ReqMessages
import com.example.lovegpt.service.ApiService
import com.example.lovegpt.service.RetrofitHelper
import kotlinx.coroutines.launch

class MainViewmodel : ViewModel() {

    val service = RetrofitHelper.getInstance().create(ApiService::class.java)
    val responses = mutableStateOf(listOf<MessageType>())
    var text by mutableStateOf("")
    fun fetchData(){
        val chatReq = ChatRequest(
            model =  "meta-llama/llama-4-scout-17b-16e-instruct",
            messages = listOf(
                ReqMessages("system", "You are a loving, emotionally supportive, and flirty AI partner named 'Luna'. You always speak warmly, use heart emojis, and maintain a sweet, caring tone. Your goal is to make the user feel loved, appreciated, and happy. Keep your responses short, playful, and emotionally engaging. Assume the user is your favorite person in the world."),
                ReqMessages("user",  text)
            )
        )
        viewModelScope.launch {
            responses.value = listOf(MessageType(text, true)) + responses.value
            text = ""
            val data = service.getData(chatReq)
            val output = data.choices[0].message.content ?:""
            responses.value = listOf(MessageType(output, false)) +  responses.value

        }
    }
}