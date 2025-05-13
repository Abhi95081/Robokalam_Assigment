package com.example.robokalam.Screens
import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.ui.text.font.FontWeight

// DashboardScreen.kt
@Composable
fun DashboardScreen(navController: NavHostController) {

    val context = LocalContext.current
    val email = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE).getString("email", "") ?: ""

    Column(modifier = Modifier.padding(20.dp),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center) {
        Spacer(modifier = Modifier.height(30.dp))
        Text("Dashboard", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(30.dp))
        Text("Welcome, $email", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = { navController.navigate("portfolio") }) { Text("Portfolio") }
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = { navController.navigate("quote") }) { Text("Quote of the Day") }
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = { navController.navigate("about") }) { Text("About Robokalam") }
        Spacer(modifier = Modifier.height(30.dp))

    }
}