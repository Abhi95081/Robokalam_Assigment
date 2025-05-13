package com.example.robokalam.Screens

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.*
import androidx.room.Room
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

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
 {
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
        OutlinedTextField(value = college, onValueChange = { college = it }, label = { Text("College") })
        OutlinedTextField(value = skills, onValueChange = { skills = it }, label = { Text("Skills") })
        OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Project Title") })
        OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Project Description") })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.insertPortfolio(
                Portfolio(
                    name = name,
                    college = college,
                    skills = skills,
                    projectTitle = title,
                    projectDescription = description
                )
            )
        }) {
            Text("Save")
        }
    }
}

class PortfolioViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Room.databaseBuilder(application, AppDatabase::class.java, "portfolio-db").build()
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
