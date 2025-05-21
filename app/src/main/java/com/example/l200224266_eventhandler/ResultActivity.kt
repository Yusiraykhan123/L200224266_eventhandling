package com.example.l200224266_eventhandler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.l200224266_eventhandler.ui.theme.L200224266_eventhandlerTheme

class ResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Extract data from intent
        val name = intent.getStringExtra("NAME") ?: ""
        val nis = intent.getStringExtra("NIS") ?: ""
        val className = intent.getStringExtra("CLASS") ?: ""
        val birthDate = intent.getStringExtra("BIRTHDATE") ?: ""
        val gender = intent.getStringExtra("GENDER") ?: ""
        val extracurricular = intent.getStringExtra("EXTRACURRICULAR") ?: ""
        val schedule = intent.getStringExtra("SCHEDULE") ?: ""
        
        setContent {
            L200224266_eventhandlerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold { innerPadding ->
                        ResultScreen(
                            name = name,
                            nis = nis,
                            className = className,
                            birthDate = birthDate,
                            gender = gender,
                            extracurricular = extracurricular,
                            schedule = schedule,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ResultScreen(
    name: String,
    nis: String,
    className: String,
    birthDate: String,
    gender: String,
    extracurricular: String,
    schedule: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(R.string.registration_result),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.student_data),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                DataRow("Nama", name)
                DataRow("NIS", nis)
                DataRow("Kelas", className)
                DataRow("Tanggal Lahir", birthDate)
                DataRow("Jenis Kelamin", gender)
                DataRow("Ekstrakurikuler", extracurricular)
                DataRow("Jadwal", schedule)
            }
        }
    }
}

@Composable
fun DataRow(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Divider()
        Spacer(modifier = Modifier.height(8.dp))
    }
}
