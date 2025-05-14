package com.example.robokalam.Screens

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.*
import androidx.room.Room
import com.example.robokalam.R
import com.example.robokalam.RoomDb.AppDatabase
import com.example.robokalam.RoomDb.Portfolio
import kotlinx.coroutines.launch

@Composable
fun PortfolioScreen(viewModel: PortfolioViewModel) {
    var name by remember { mutableStateOf("") }
    var college by remember { mutableStateOf("") }
    var skills by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    var showSavedMessage by remember { mutableStateOf(false) }
    var showSkillError by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Portfolio Builder", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))


        InputField(label = "Full Name", value = name) { name = it }
        InputField(label = "College", value = college) { college = it }
        InputField(
            label = "Skills (comma-separated, min 3)",
            value = skills,
            isError = showSkillError
        ) { skills = it }

        InputField(label = "Project Title", value = title) { title = it }
        InputField(label = "Project Description", value = description, maxLines = 4) { description = it }

        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = {
                val skillList = skills.split(",").map { it.trim() }.filter { it.isNotEmpty() }
                if (skillList.size < 3) {
                    showSkillError = true
                    showSavedMessage = false
                } else {

                    viewModel.insertPortfolio(
                        Portfolio(
                            name = name,
                            college = college,
                            skills = skills,
                            projectTitle = title,
                            projectDescription = description
                        )
                    )
                    showSkillError = false
                    showSavedMessage = true
                    name = ""
                    college = ""
                    skills = ""
                    title = ""
                    description = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            Text("Save Portfolio")
        }


        Spacer(modifier = Modifier.height(8.dp))

        if (showSkillError) {
            Text("Please enter at least 3 skills.", color = Color.Red)
        }

        if (showSavedMessage) {
            Text("Portfolio saved successfully!", color = MaterialTheme.colors.primary)
        }
    }
}

@Composable
fun InputField(
    label: String,
    value: String,
    isError: Boolean = false,
    maxLines: Int = 1,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        isError = isError,
        maxLines = maxLines
    )
}

class PortfolioViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "portfolio-db"
    ).fallbackToDestructiveMigration().build()

    private val dao = db.portfolioDao()

    fun insertPortfolio(portfolio: Portfolio) = viewModelScope.launch {
        dao.insertPortfolio(portfolio)
    }
}

class PortfolioViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PortfolioViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PortfolioViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
