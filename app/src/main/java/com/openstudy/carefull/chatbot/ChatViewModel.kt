package com.openstudy.carefull.chatbot

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ChatViewModel(val onDepartmentClick: (String) -> Unit) : ViewModel() {
    val chatMessages = mutableStateListOf<ChatMessage>()

    init {
        chatMessages.add(ChatMessage("안녕하세요! 챗봇입니다.", false))
    }

    fun onUserMessage(input: String) {
        if (input.isBlank()) return

        chatMessages.add(ChatMessage(input, true))

        val (diagnosis, department) = generateResponse(input)


        if (diagnosis.isNotEmpty()) {
            val fullMessage = "$diagnosis \n 추천 진료과: ${department.joinToString(", ")}"

            chatMessages.add(
                ChatMessage(
                    message = fullMessage,
                    isUser = false,
                    clickableDepartments = department,
                    onClickDepartment = { dept ->
                        if (department.contains(dept)) {
                            onDepartmentClick(dept)
                        }
                    }
                )
            )
        } else {
            chatMessages.add(
                ChatMessage(
                    message = "증상에 해당하는 질병을 찾을 수 없습니다.",
                    isUser = false
                )
            )
        }
    }

    private fun generateResponse(input: String): Pair<String, List<String>> {
        val matchedDiseases = mutableListOf<String>()
        val departments = mutableSetOf<String>()

        if (input.contains("기침")) {
            matchedDiseases.add("감기")
            departments.add("내과")
        }
        if (input.contains("열")) {
            matchedDiseases.add("독감")
            departments.add("내과")
        }
        if (input.contains("두통")) {
            matchedDiseases.add("코로나19")
            departments.add("내과")
            departments.add("신경과")
        }

        return if (matchedDiseases.isEmpty()) {
            "" to emptyList()
        } else {
            "${matchedDiseases.joinToString(", ")}가 의심됩니다." to departments.toList()
        }
    }
}