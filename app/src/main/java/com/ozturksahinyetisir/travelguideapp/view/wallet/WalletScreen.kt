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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.ozturksahinyetisir.travelguideapp.R
import com.ozturksahinyetisir.travelguideapp.components.ItineraHeader

data class Expense(
    val amount: Double,
    val category: String,
    val date: String,
    val note: String,
    val icon: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalletScreen(walletViewModel: WalletViewModel = viewModel()) {
    var amount by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("05/03/2025") }
    var note by remember { mutableStateOf("") }

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
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .background(Color(0xFFF8F8F8)),
            contentPadding = PaddingValues(bottom = 120.dp)
        ) {
            item { TopCard() }
            item { TripBudgetCard(totalSpent, budget, remaining) }
            item {
                AddExpenseCard(
                    amount = amount,
                    onAmountChange = { amount = it },
                    category = category,
                    onCategoryChange = { category = it },
                    date = date,
                    onDateChange = { date = it },
                    note = note,
                    onNoteChange = { note = it },
                    onAddExpenseClick = {
                        val newAmount = amount.toDoubleOrNull()
                        if (newAmount != null && category.isNotBlank()) {
                            val newExpense = Expense(
                                amount = newAmount,
                                category = category,
                                date = date,
                                note = note,
                                icon = getIconForCategory(category)
                            )
                            walletViewModel.addExpense(newExpense)
                            // Reset fields
                            amount = ""
                            category = ""
                            note = ""
                        }
                    }
                )
            }
            item { CategoryBreakdownCard(expenses) }
            item { RecentExpensesCard(expenses) }
        }
    }
}

fun getIconForCategory(category: String): Int {
    return when (category.lowercase()) {
        "food" -> R.drawable.ramen
        "stay" -> R.drawable.suite
        "transport" -> R.drawable.public_transport
        "flights" -> R.drawable.plane
        else -> R.drawable.spending
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
fun TripBudgetCard(spent: Double, budget: Double, remaining: Double) {
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
                    Text(String.format("$%.2f", spent), fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("Budget", color = Color.Gray)
                    Text(String.format("$%.2f", budget), fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = (spent / budget).toFloat(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically) {
                Text(String.format("Remaining: $%.2f", remaining), color = Color.Gray)
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
fun AddExpenseCard(
    amount: String,
    onAmountChange: (String) -> Unit,
    category: String,
    onCategoryChange: (String) -> Unit,
    date: String,
    onDateChange: (String) -> Unit,
    note: String,
    onNoteChange: (String) -> Unit,
    onAddExpenseClick: () -> Unit
) {
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
                OutlinedTextField(value = amount, onValueChange = onAmountChange, label = { Text("Amount") }, modifier = Modifier.weight(1f))
                OutlinedTextField(value = category, onValueChange = onCategoryChange, label = { Text("Category") }, modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = date, onValueChange = onDateChange, label = { Text("Date") }, leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = null) }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = note, onValueChange = onNoteChange, label = { Text("Note...") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onAddExpenseClick, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7B68EE))) {
                Text("Add Expense")
            }
            Text("Tip: Export CSV/PDF For Receipts.", style = MaterialTheme.typography.bodySmall, color = Color.Gray, modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 8.dp))
        }
    }
}

@Composable
fun CategoryBreakdownCard(expenses: List<Expense>) {
    val lightBlue = Color(0xFFE6E6FA)
    val categoryTotals = expenses.groupBy { it.category.lowercase() }
        .mapValues { entry -> entry.value.sumOf { it.amount } }

    val planeTotal = categoryTotals.getOrDefault("flights", 0.0)
    val suiteTotal = categoryTotals.getOrDefault("stay", 0.0)
    val ramenTotal = categoryTotals.getOrDefault("food", 0.0)
    val transportTotal = categoryTotals.getOrDefault("transport", 0.0)

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
                CategoryItem(R.drawable.plane, String.format("$%.0f", planeTotal), lightBlue)
                CategoryItem(R.drawable.suite, String.format("$%.0f", suiteTotal), lightBlue)
                CategoryItem(R.drawable.ramen, String.format("$%.0f", ramenTotal), lightBlue)
                CategoryItem(R.drawable.public_transport, String.format("$%.0f", transportTotal), lightBlue)
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
fun RecentExpensesCard(expenses: List<Expense>) {
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
            expenses.forEachIndexed { index, expense ->
                ExpenseItem(
                    icon = expense.icon,
                    title = expense.note.ifBlank { expense.category },
                    subtitle = "${expense.date} • ${expense.category}",
                    amount = String.format("$%.2f", expense.amount)
                )
                if (index < expenses.size - 1) {
                    Divider(modifier = Modifier.padding(vertical = 8.dp), color = Color.LightGray)
                }
            }
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
