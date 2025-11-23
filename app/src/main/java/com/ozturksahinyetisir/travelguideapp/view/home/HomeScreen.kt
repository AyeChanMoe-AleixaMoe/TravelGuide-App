package com.ozturksahinyetisir.travelguideapp.view.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.ozturksahinyetisir.travelguideapp.R
import com.ozturksahinyetisir.travelguideapp.components.ItineraHeader
import com.ozturksahinyetisir.travelguideapp.view.wallet.TripBudgetCard
import com.ozturksahinyetisir.travelguideapp.view.wallet.WalletViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(walletViewModel: WalletViewModel = viewModel()) {
    var showPinDialog by remember { mutableStateOf(false) }
    var showDocument by remember { mutableStateOf(false) }
    var pinValue by remember { mutableStateOf("") }

    val expenses by walletViewModel.expenses
    val totalSpent = expenses.sumOf { it.amount }
    val budget = 1055.0
    val remaining = budget - totalSpent

    Scaffold(
        topBar = {
            Column(modifier = Modifier.padding(16.dp)) {
                ItineraHeader()
            }
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            item { TopCard() }
            item { QuickActionsCard() }
            item { TodayScheduleCard() }
            item { TripBudgetCard(totalSpent, budget, remaining) }
            item { TravelDocumentsCard(onOpenClick = { showPinDialog = true }) }
        }
    }

    if (showPinDialog) {
        AlertDialog(
            onDismissRequest = { showPinDialog = false },
            title = { Text("Enter PIN") },
            text = {
                OutlinedTextField(
                    value = pinValue,
                    onValueChange = { pinValue = it },
                    label = { Text("PIN") })
            },
            confirmButton = {
                Button(onClick = {
                    if (pinValue == "1234") { // Example PIN
                        showPinDialog = false
                        showDocument = true
                    }
                }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(onClick = { showPinDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    if (showDocument) {
        Dialog(onDismissRequest = { showDocument = false }) {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = rememberAsyncImagePainter("file:///android_asset/jamescook_student_card.jpg"),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
                Button(
                    onClick = { showDocument = false },
                    modifier = Modifier.align(Alignment.TopEnd).padding(16.dp)
                ) {
                    Text("Close")
                }
            }
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
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("Tokyo Getaway", fontWeight = FontWeight.Bold)
                Text("12 - 18 Oct • 2 Travelers • 4 Items Today", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
            Text("in 5 Days", color = Color(0xFF7B68EE), modifier = Modifier
                .background(Color(0xFFE6E6FA), RoundedCornerShape(12.dp))
                .padding(horizontal = 12.dp, vertical = 6.dp))
        }
    }
}

@Composable
fun QuickActionsCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Quick Actions", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                QuickActionItem(icon = R.drawable.plane, text = "Flights")
                QuickActionItem(icon = R.drawable.suite, text = "Stay")
                QuickActionItem(icon = R.drawable.checklist, text = "Activities")
                QuickActionItem(icon = R.drawable.spending, text = "Expenses")
            }
        }
    }
}

@Composable
fun QuickActionItem(icon: Int, text: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = rememberAsyncImagePainter(icon),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text, fontSize = 12.sp)
    }
}

@Composable
fun TodayScheduleCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Today's Schedule", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(16.dp))
            ScheduleItem(
                icon = R.drawable.check_in,
                title = "Hotel Check-In • MBS",
                subtitle = "3:00 PM • Confirmation #SJX439",
                buttonText = "Map"
            )
            Divider(modifier = Modifier.padding(vertical = 8.dp), color = Color.LightGray)
            ScheduleItem(
                icon = R.drawable.ticket,
                title = "Tickets For Flower Dome",
                subtitle = "7:00 PM • 2 Tickets",
                buttonText = "Tickets"
            )
        }
    }
}

@Composable
fun ScheduleItem(icon: Int, title: String, subtitle: String, buttonText: String) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = rememberAsyncImagePainter(icon),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.Bold)
            Text(subtitle, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        }
        Button(onClick = { /*TODO*/ }) {
            Text(buttonText)
        }
    }
}

@Composable
fun TravelDocumentsCard(onOpenClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Travel Documents", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(16.dp))
            DocumentItem(R.drawable.google_docs, "Passport & Visas", "Secure • PIN Enabled", onOpenClick)
            Divider(modifier = Modifier.padding(vertical = 8.dp), color = Color.LightGray)
            DocumentItem(R.drawable.pdf, "Flight PDFs", "NH 802 • 12 Oct • Gate 54", onOpenClick)
        }
    }
}

@Composable
fun DocumentItem(icon: Int, title: String, subtitle: String, onOpenClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Image(painter = rememberAsyncImagePainter(icon), contentDescription = null, modifier = Modifier.size(40.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.Bold)
            Text(subtitle, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        }
        Button(onClick = onOpenClick) {
            Text("Open")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
