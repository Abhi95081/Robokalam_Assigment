package com.example.robokalam

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.robokalam.Screens.AboutScreen
import com.example.robokalam.Screens.DashboardScreen
import com.example.robokalam.Screens.LoginScreen
import com.example.robokalam.Screens.PortfolioScreen
import com.example.robokalam.Screens.QuoteScreen
import com.example.robokalam.ui.theme.RobokalamTheme
import kotlinx.coroutines.delay
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.robokalam.Screens.PortfolioViewModel
import com.example.robokalam.Screens.PortfolioViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RobokalamTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    val context = LocalContext.current
                    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                    val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

                    // Start with splash screen and navigate based on login status
                    NavHost(navController, startDestination = "splash") {
                        composable("splash") { SplashScreen(navController, isLoggedIn) }
                        composable("login") { LoginScreen(navController) }
                        composable("dashboard") { DashboardScreen(navController) }
                        composable("portfolio") {
                            val application = LocalContext.current.applicationContext as Application
                            val portfolioViewModel: PortfolioViewModel = viewModel(
                                factory = PortfolioViewModelFactory(application)
                            )
                            PortfolioScreen(viewModel = portfolioViewModel)
                        }
                        composable("quote") { QuoteScreen() }
                        composable("about") { AboutScreen(navController) }
                    }
                }
            }
        }
    }
}

@Composable
fun SplashScreen(navController: NavHostController, isLoggedIn: Boolean) {

    LaunchedEffect(true) {
        delay(2000)
        if (isLoggedIn) {
            navController.navigate("dashboard") {
                popUpTo("splash") { inclusive = true }
            }
        } else {
            navController.navigate("login") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(colors = listOf(Color(0xFF6200EE), Color(0xFF3700B3)))) // Gradient background
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {

            Image(
                painter = painterResource(id = R.drawable.robokalam_logo),
                contentDescription = null,
                modifier = Modifier
                    .size(180.dp)
                    .padding(bottom = 16.dp)
            )

            Text(
                text = "Robokalam",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(top = 8.dp)
            )


            Text(
                text = "Empowering Young Minds",
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.8f),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
