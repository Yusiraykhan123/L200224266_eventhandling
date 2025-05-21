package com.example.l200224266_eventhandler

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.l200224266_eventhandler.ui.theme.L200224266_eventhandlerTheme
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.tooling.preview.Preview
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            L200224266_eventhandlerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold { innerPadding ->
                        RegistrationForm(modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun RegistrationForm(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    
    // Get string resources
    val maleText = stringResource(R.string.male)
    val femaleText = stringResource(R.string.female)
    
    // State variables
    var name by remember { mutableStateOf("") }
    var nis by remember { mutableStateOf("") }
    var selectedClass by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("") }
    var scheduleQuery by remember { mutableStateOf("") }
    val selectedSchedule = remember { mutableStateOf("") }
    
    // Extracurricular checkboxes
    val extracurricularOptions = listOf(
        stringResource(R.string.basketball),
        stringResource(R.string.soccer),
        stringResource(R.string.robotics),
        stringResource(R.string.english_club),
        stringResource(R.string.paskibra)
    )
    val checkedState = remember { mutableStateMapOf<String, Boolean>().apply {
        extracurricularOptions.forEach { put(it, false) }
    }}
    
    // Dialogs state
    var showDatePicker by remember { mutableStateOf(false) }
    var showClassDropdown by remember { mutableStateOf(false) }
    var showScheduleSearch by remember { mutableStateOf(false) }
    
    // Class options
    val classOptions = listOf("X IPA 1", "X IPA 2", "X IPS 1", "X IPS 2", 
                             "XI IPA 1", "XI IPA 2", "XI IPS 1", "XI IPS 2",
                             "XII IPA 1", "XII IPA 2", "XII IPS 1", "XII IPS 2")
    
    // Schedule options
    val scheduleOptions = listOf(
        "Senin, 14:00 - 16:00",
        "Selasa, 14:00 - 16:00",
        "Rabu, 14:00 - 16:00",
        "Kamis, 14:00 - 16:00",
        "Jumat, 13:00 - 15:00",
        "Sabtu, 08:00 - 10:00"
    )
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.title),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        
        // Name field
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(stringResource(R.string.name_hint)) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
        
        // NIS field
        OutlinedTextField(
            value = nis,
            onValueChange = { nis = it },
            label = { Text(stringResource(R.string.nis_hint)) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
        
        // Class selection (Spinner)
        OutlinedTextField(
            value = selectedClass,
            onValueChange = { },
            label = { Text(stringResource(R.string.class_prompt)) },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showClassDropdown = true }) {
                    Icon(Icons.Default.KeyboardArrowDown, "dropdown")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
        
        // Birth Date picker
        OutlinedTextField(
            value = birthDate,
            onValueChange = { },
            label = { Text(stringResource(R.string.birthdate)) },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = true }) {
                    Icon(Icons.Default.DateRange, "calendar")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
        
        // Gender selection (Radio buttons)
        Text(
            text = stringResource(R.string.gender),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        
        Row(
            modifier = Modifier
                .selectableGroup()
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .selectable(
                        selected = selectedGender == maleText,
                        onClick = { selectedGender = maleText },
                        role = Role.RadioButton
                    )
                    .padding(end = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedGender == maleText,
                    onClick = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = maleText)
            }
            
            Row(
                modifier = Modifier
                    .selectable(
                        selected = selectedGender == femaleText,
                        onClick = { selectedGender = femaleText },
                        role = Role.RadioButton
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedGender == femaleText,
                    onClick = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = femaleText)
            }
        }
        
        // Extracurricular selection (Checkboxes)
        Text(
            text = stringResource(R.string.extracurricular),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        
        extracurricularOptions.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Checkbox(
                    checked = checkedState[option] == true,
                    onCheckedChange = { checkedState[option] = it }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = option)
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Schedule search (Search View)
        OutlinedTextField(
            value = selectedSchedule.value,
            onValueChange = { },
            label = { Text(stringResource(R.string.schedule)) },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showScheduleSearch = true }) {
                    Icon(Icons.Default.Search, "search")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )
        
        // Save button
        Button(
            onClick = {
                // Validate input
                if (name.isBlank() || nis.isBlank() || selectedClass.isBlank() || 
                    birthDate.isBlank() || selectedGender.isBlank() || selectedSchedule.value.isBlank() ||
                    !checkedState.containsValue(true)) {
                    Toast.makeText(context, "Semua field harus diisi!", Toast.LENGTH_SHORT).show()
                } else {
                    // Create string with selected extracurricular
                    val selectedExtras = checkedState.filter { it.value }.keys.joinToString(", ")
                    
                    // Start ResultActivity
                    val intent = Intent(context, ResultActivity::class.java).apply {
                        putExtra("NAME", name)
                        putExtra("NIS", nis)
                        putExtra("CLASS", selectedClass)
                        putExtra("BIRTHDATE", birthDate)
                        putExtra("GENDER", selectedGender)
                        putExtra("EXTRACURRICULAR", selectedExtras)
                        putExtra("SCHEDULE", selectedSchedule.value)
                    }
                    context.startActivity(intent)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(stringResource(R.string.save_button))
        }
    }
    
    // Date Picker Dialog
    if (showDatePicker) {
        val datePickerState = rememberDatePickerState()
        
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                Button(onClick = {
                    datePickerState.selectedDateMillis?.let {
                        val date = Date(it)
                        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale("id"))
                        birthDate = formatter.format(date)
                    }
                    showDatePicker = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Batal")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
    
    // Class selection dialog
    if (showClassDropdown) {
        Dialog(
            onDismissRequest = { showClassDropdown = false },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = stringResource(R.string.class_prompt),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    
                    classOptions.forEach { option ->
                        Text(
                            text = option,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { 
                                    selectedClass = option
                                    showClassDropdown = false
                                }
                                .padding(vertical = 12.dp)
                        )
                        if (option != classOptions.last()) {
                            Divider()
                        }
                    }
                }
            }
        }
    }
    
    // Schedule search dialog
    if (showScheduleSearch) {
        Dialog(
            onDismissRequest = { showScheduleSearch = false },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    OutlinedTextField(
                        value = scheduleQuery,
                        onValueChange = { scheduleQuery = it },
                        label = { Text(stringResource(R.string.search_schedule)) },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )
                    
                    val filteredOptions = if (scheduleQuery.isBlank()) {
                        scheduleOptions
                    } else {
                        scheduleOptions.filter { it.contains(scheduleQuery, ignoreCase = true) }
                    }
                    
                    filteredOptions.forEach { option ->
                        Text(
                            text = option,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { 
                                    selectedSchedule.value = option
                                    showScheduleSearch = false
                                }
                                .padding(vertical = 12.dp)
                        )
                        if (option != filteredOptions.last()) {
                            Divider()
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationFormPreview() {
    L200224266_eventhandlerTheme {
        RegistrationForm()
    }
}