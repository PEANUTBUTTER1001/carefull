package com.openstudy.carefull.chatbot

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.openstudy.carefull.ui.theme.CarefullTheme

@Composable
fun ChatBotScreen(
    modifier: Modifier = Modifier,
    onDepartmentClick: (department: String, diagnosis: String) -> Unit
) {
    val viewModel = remember { ChatViewModel(onDepartmentClick) }

    var userInput by remember { mutableStateOf("") }
    val messages = viewModel.chatMessages.reversed()

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        LazyColumn(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            reverseLayout = true
        ) {
            items(messages) { message ->
                ChatBubble(message)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                modifier = Modifier.weight(1f),
                value = userInput,
                onValueChange = { userInput = it },
                placeholder = { Text("증상을 입력하세요") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                viewModel.onUserMessage(userInput)
                userInput = ""
            }) {
                Text("전송")
            }
        }
    }
}

@Composable
fun ChatBubble(message: ChatMessage) {
    val alignment = if (message.isUser) Alignment.CenterEnd else Alignment.CenterStart
    val bgColor = if (message.isUser) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
    val textColor = if (message.isUser) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = alignment
    ) {
        Surface(
            color = bgColor,
            shape = MaterialTheme.shapes.medium
        ) {
            if (message.clickableDepartments != null && message.clickableDepartments.isNotEmpty()) {
                // 추천 진료과가 있으면 부분 클릭 텍스트 표시
                ClickableDepartmentText(
                    fullText = message.message,
                    departments = message.clickableDepartments,
                    onClickDepartment = message.onClickDepartment
                )
            } else {
                Text(
                    text = message.message,
                    modifier = Modifier.padding(10.dp),
                    color = textColor
                )
            }
        }
    }
}

@Composable
fun ClickableDepartmentText(
    fullText: String,
    departments: List<String>,
    onClickDepartment: ((String) -> Unit)?
) {
    val annotatedString = buildAnnotatedString {
        append(fullText)

        departments.forEach { dept ->
            val startIndex = fullText.indexOf(dept)
            if (startIndex >= 0) {
                val endIndex = startIndex + dept.length
                addStringAnnotation(
                    tag = "DEPT",
                    annotation = dept,
                    start = startIndex,
                    end = endIndex
                )
                // 텍스트 색상 표시용
                addStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        textDecoration = TextDecoration.Underline
                    ),
                    start = startIndex,
                    end = endIndex
                )
            }
        }
    }

    ClickableText(
        text = annotatedString,
        modifier = Modifier.padding(10.dp),
        style = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.onSurface),
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "DEPT", start = offset, end = offset)
                .firstOrNull()?.let { annotation ->
                    onClickDepartment?.invoke(annotation.item)
                }
        }
    )
}