package com.ozturksahinyetisir.travelguideapp.view.wallet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.ozturksahinyetisir.travelguideapp.R
import com.ozturksahinyetisir.travelguideapp.components.ItineraHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalletScreen() {
    Scaffold(
        topBar = {
            Column(modifier = Modifier.padding(16.dp)) {
                ItineraHeader()
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .background(Color(0xFFF8F8F8)),
            contentPadding = PaddingValues(bottom = 120.dp)
        ) {
            item { TopCard() }
            item { TripBudgetCard() }
            item { AddExpenseCard() }
            item { CategoryBreakdownCard() }
            item { RecentExpensesCard() }
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
                    Text("12 - 18 Oct • 2 Travelers • 4 Items Today", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                }
                Text("In 5 Days", color = Color(0xFF7B68EE), modifier = Modifier.background(Color(0xFFE6E6FA), RoundedCornerShape(12.dp)).padding(horizontal = 12.dp, vertical = 6.dp))
            }
        }
    }
}

@Composable
fun TripBudgetCard() {
    val lightBlue = Color(0xFFE6E6FA)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text("Spent", color = Color.Gray)
                    Text("$484", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("Budget", color = Color.Gray)
                    Text("$1,055", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = 0.46f, // Example progress
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically) {
                Text("Remaining: $571", color = Color.Gray)
                Spacer(modifier = Modifier.weight(1f))
                Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(containerColor = lightBlue)) { Text("Export PDF", color = Color.Black) }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(containerColor = lightBlue)) { Text("Export CSV", color = Color.Black) }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpenseCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Add Expense", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = "", onValueChange = {}, label = { Text("Amount") }, modifier = Modifier.weight(1f))
                OutlinedTextField(value = "", onValueChange = {}, label = { Text("Category") }, modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = "05/03/2025", onValueChange = {}, label = { Text("Date") }, leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = null) }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = "", onValueChange = {}, label = { Text("Note...") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7B68EE))) {
                Text("Add Expense")
            }
            Text("Tip: Export CSV/PDF For Receipts.", style = MaterialTheme.typography.bodySmall, color = Color.Gray, modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 8.dp))
        }
    }
}

@Composable
fun CategoryBreakdownCard() {
    val lightBlue = Color(0xFFE6E6FA)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Category Breakdown", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                CategoryItem(R.drawable.plane, "$242", lightBlue)
                CategoryItem(R.drawable.suite, "$123", lightBlue)
                CategoryItem(R.drawable.ramen, "$42", lightBlue)
                CategoryItem(R.drawable.public_transport, "$77", lightBlue)
            }
        }
    }
}

@Composable
fun CategoryItem(icon: Int, amount: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.size(60.dp).background(color, CircleShape), contentAlignment = Alignment.Center) {
            Image(painter = rememberAsyncImagePainter(icon), contentDescription = null, modifier = Modifier.size(30.dp))
        }
        Text(amount, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun RecentExpensesCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Recent Expenses", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(16.dp))
            ExpenseItem(R.drawable.ramen, "Onigiri & Tea", "2023-10-05 • Food", "$12.50")
            Divider(modifier = Modifier.padding(vertical = 8.dp), color = Color.LightGray)
            ExpenseItem(R.drawable.suite, "Hotel Deposit", "2023-10-04 • Stay", "$123")
        }
    }
}

@Composable
fun ExpenseItem(icon: Int, title: String, subtitle: String, amount: String) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Image(painter = rememberAsyncImagePainter(icon), contentDescription = null, modifier = Modifier.size(40.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.Bold)
            Text(subtitle, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        }
        Text(amount, fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
}


@Preview(showBackground = true)
@Composable
fun WalletScreenPreview() {
    WalletScreen()
}
