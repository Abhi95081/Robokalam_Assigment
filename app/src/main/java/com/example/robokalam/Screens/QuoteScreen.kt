package com.example.robokalam.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.net.URL

@Composable
fun QuoteScreen() {
    var quote by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        try {
            val response = withContext(Dispatchers.IO) {
                URL("https://zenquotes.io/api/random").readText()
            }
            val json = JSONArray(response)
            quote = json.getJSONObject(0).getString("q")
            author = json.getJSONObject(0).getString("a")
        } catch (e: Exception) {
            quote = "Error loading quote."
        } finally {
            isLoading = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            Card(
                shape = RoundedCornerShape(16.dp),
                elevation = 12.dp,
                backgroundColor = Color(0xFFfefae0),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "“$quote”",
                        fontSize = 20.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color(0xFF343a40)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "- $author",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF6c757d)
                    )
                }
            }
        }
    }
}
