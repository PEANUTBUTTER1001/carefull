package com.openstudy.carefull.chatbot

data class ChatMessage(
    val message: String,
    val isUser: Boolean,
    val clickableDepartments: List<String>? = null,
    val onClickDepartment: ((String) -> Unit)? = null
)