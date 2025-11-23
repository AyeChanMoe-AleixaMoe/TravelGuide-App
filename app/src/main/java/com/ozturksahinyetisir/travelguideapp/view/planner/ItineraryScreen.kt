package com.ozturksahinyetisir.travelguideapp.view.planner

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.ozturksahinyetisir.travelguideapp.R
import com.ozturksahinyetisir.travelguideapp.components.ItineraHeader

data class ItineraryItemData(
    val type: String,
    val title: String,
    val date: String,
    val time: String,
    val location: String,
    val confirmation: String,
    val notes: String,
    val icon: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItineraryScreen() {
    val itineraryItems = remember {
        mutableStateListOf(
            ItineraryItemData("Flight", "09:35 • NH 802 Arrival", "12/10/2024", "09:35", "HND • Terminal 3", "NH802", "Immigration • Keikyu Line to Shinagawa", R.drawable.plane_overview),
            ItineraryItemData("Hotel", "15:00 • Hotel Check In • Shinjuku", "12/10/2024", "15:00", "Shinjuku", "SJX439", "Ask For High Floor, Non-Smoking", R.drawable.suite)
        )
    }

    Scaffold(
        topBar = {
            Column(modifier = Modifier.padding(16.dp)) {
                ItineraHeader()
            }
        },
        bottomBar = {
            // The bottom bar from your design can be added here
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .background(Color(0xFFF8F8F8)),
            contentPadding = PaddingValues(bottom = 120.dp) // Ensures scrolling content isn't hidden
        ) {
            item { TopCard() }
            item { ItineraryActions() }
            item {
                AddToItineraryCard { newItem ->
                    itineraryItems.add(newItem)
                }
            }
            item { ItineraryItemsList(itineraryItems) }
        }
    }
}

@Composable
fun TopCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text("Tokyo Getaway", fontWeight = FontWeight.Bold)
                    Text(
                        "12 - 18 Oct • 2 Travelers • 4 Items Today",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
                Text(
                    "In 5 Days",
                    color = Color(0xFF7B68EE),
                    modifier = Modifier
                        .background(Color(0xFFE6E6FA), RoundedCornerShape(12.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }
        }
    }
}

@Composable
fun ItineraryActions() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Itinerary", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Row {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Export Word", color = Color.Black)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Export PDF", color = Color.Black)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddToItineraryCard(onAddItem: (ItineraryItemData) -> Unit) {
    val textStyle = TextStyle(fontSize = 14.sp, color = Color.Gray)
    val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        unfocusedBorderColor = Color.LightGray,
        unfocusedLabelColor = Color.Gray
    )

    val typeState = remember { mutableStateOf("") }
    val titleState = remember { mutableStateOf("") }
    val dateState = remember { mutableStateOf("05/03/2025") }
    val timeState = remember { mutableStateOf("8:00PM") }
    val locationState = remember { mutableStateOf("") }
    val confirmationState = remember { mutableStateOf("") }
    val notesState = remember { mutableStateOf("") }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Add To Itinerary", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = typeState.value,
                    onValueChange = { typeState.value = it },
                    label = { Text("Type") },
                    modifier = Modifier.weight(1f),
                    colors = textFieldColors,
                    textStyle = textStyle
                )
                OutlinedTextField(
                    value = titleState.value,
                    onValueChange = { titleState.value = it },
                    label = { Text("Title (Type Here)") },
                    modifier = Modifier.weight(1f),
                    colors = textFieldColors,
                    textStyle = textStyle
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = dateState.value,
                    onValueChange = { dateState.value = it },
                    label = { Text("Date") },
                    leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = null) },
                    modifier = Modifier.weight(1f),
                    colors = textFieldColors,
                    textStyle = textStyle
                )
                OutlinedTextField(
                    value = timeState.value,
                    onValueChange = { timeState.value = it },
                    label = { Text("Time") },
                    leadingIcon = { Icon(Icons.Default.Info, contentDescription = null) },
                    modifier = Modifier.weight(1f),
                    colors = textFieldColors,
                    textStyle = textStyle
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = locationState.value,
                onValueChange = { locationState.value = it },
                label = { Text("Location (Type Here)") },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors,
                textStyle = textStyle
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = confirmationState.value,
                onValueChange = { confirmationState.value = it },
                label = { Text("Confirmation (Type Here)") },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors,
                textStyle = textStyle
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = notesState.value,
                onValueChange = { notesState.value = it },
                label = { Text("Notes (E.g., Baggage Belt, Gate, Meet Host)") },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors,
                textStyle = textStyle
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val newItem = ItineraryItemData(
                        type = typeState.value,
                        title = titleState.value,
                        date = dateState.value,
                        time = timeState.value,
                        location = locationState.value,
                        confirmation = confirmationState.value,
                        notes = notesState.value,
                        icon = R.drawable.check_in // A default icon
                    )
                    onAddItem(newItem)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7B68EE))
            ) {
                Text("Add Item")
            }
            Text(
                "Tip: Keep Titles Short; Details Go In Notes.",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun ItineraryItemsList(items: List<ItineraryItemData>) {
    val buttonColors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE6E6FA))

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Sun, Oct 12  ${items.size} item(s)", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            Spacer(modifier = Modifier.height(16.dp))
            items.forEachIndexed { index, item ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = rememberAsyncImagePainter(item.icon),
                        contentDescription = null,
                        modifier = Modifier.size(50.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(item.title, fontWeight = FontWeight.Bold)
                        Text("${item.location} • Ref: ${item.confirmation} • ${item.notes}", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                    }
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(onClick = { /*TODO*/ }, modifier = Modifier.width(100.dp), colors = buttonColors) { Text("Map", color = Color.Black) }
                        Button(onClick = { /*TODO*/ }, modifier = Modifier.width(100.dp), colors = buttonColors) { Text("Ticket", color = Color.Black) }
                        Button(onClick = { /*TODO*/ }, modifier = Modifier.width(100.dp), colors = buttonColors) { Text("Done", color = Color.Black) }
                        Button(onClick = { /*TODO*/ }, modifier = Modifier.width(100.dp), colors = buttonColors) { Text("Delete", color = Color.Black) }
                    }
                }
                if (index < items.lastIndex) {
                    Divider(modifier = Modifier.padding(vertical = 16.dp), color = Color.LightGray)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ItineraryScreenPreview() {
    ItineraryScreen()
}