package com.example.robokalam.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.robokalam.R

@Composable
fun AboutScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("About Robokalam") },
                    backgroundColor = Color(0xFF6200EE),
                    contentColor = Color.White
                )
            },
            content = { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .padding(16.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Robokalam Logo
                    Image(
                        painter = painterResource(R.drawable.robokalam_logo),
                        contentDescription = "Robokalam Logo",
                        modifier = Modifier
                            .size(120.dp)
                            .padding(8.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Tagline
                    Text(
                        text = "Empowering Young Minds Through Technology",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF6200EE)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Description
                    Text(
                        text = "Robokalam is a 21st-century EdTech platform offering hands-on learning in robotics, AI, IoT, and coding through engaging internships, workshops, and DIY kits. Our mission is to make learning interactive and fun, preparing students for the future of work.",
                        fontSize = 16.sp,
                        lineHeight = 22.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Key Offerings Card
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        elevation = 4.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Key Offerings:",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = Color(0xFF6200EE)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("• Gamified Learning Platform")
                            Text("• Robotics & AI Workshops")
                            Text("• Coding Bootcamps")
                            Text("• DIY Educational Kits")
                            Text("• Internship Opportunities")
                            Text("• Certified Courses")
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Logout Button
                    Button(
                        onClick = {
                            navController.navigate("login") {
                                popUpTo("login") { inclusive = true }  // Clear back stack up to the login screen
                            }
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF6200EE)),
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ExitToApp,
                            contentDescription = "Logout",
                            modifier = Modifier.size(24.dp),
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Logout", color = Color.White, fontWeight = FontWeight.Bold)
                    }

                }
            }
        )
    }
}
