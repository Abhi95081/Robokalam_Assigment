package com.example.robokalam.Screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


// LoginScreen.kt
@Composable
fun LoginScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center) {
        Text("Enter your email")
        OutlinedTextField(value = email, onValueChange = { email = it })
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (email.isNotEmpty()) {
                val sharedPref = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                sharedPref.edit().putString("email", email).putBoolean("isLoggedIn", true).apply()
                navController.navigate("dashboard")
            }
        }) {
            Text("Login")
        }
    }
}
