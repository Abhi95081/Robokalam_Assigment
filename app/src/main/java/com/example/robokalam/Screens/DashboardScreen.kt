package com.example.robokalam.Screens

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

// DashboardScreen.kt
@Composable
fun DashboardScreen(navController: NavHostController) {
    val context = LocalContext.current
    val name = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE).getString("name", "") ?: ""
    val email = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE).getString("email", "") ?: ""

    BackHandler {}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(Color(0xFF00BCD4), Color(0xFF1A237E)),
                    start = Offset(0f, 0f),
                    end = Offset(0f, 1000f) // Define the direction of the gradient using offsets
                )
            )
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Dashboard",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Welcome, $name",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(10.dp))

        CustomButton(
            text = "Portfolio",
            onClick = { navController.navigate("portfolio") },
            icon = Icons.Filled.AccountBox
        )

        Spacer(modifier = Modifier.height(30.dp))

        CustomButton(
            text = "Quote of the Day",
            onClick = { navController.navigate("quote") },
            icon = Icons.Filled.Star
        )

        Spacer(modifier = Modifier.height(30.dp))

        CustomButton(
            text = "About Robokalam",
            onClick = { navController.navigate("about") },
            icon = Icons.Filled.Info
        )
    }
}

@Composable
fun CustomButton(text: String, onClick: () -> Unit, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50))
    ) {
        Icon(imageVector = icon, contentDescription = text, tint = Color.White)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, color = Color.White)
    }
}

@Preview
@Composable
fun PreviewDashboardScreen() {
    val mockNavController = NavHostController(LocalContext.current)
    DashboardScreen(navController = mockNavController)
}
