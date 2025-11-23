package com.ozturksahinyetisir.travelguideapp.view.wallet

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ozturksahinyetisir.travelguideapp.R

class WalletViewModel : ViewModel() {
    private val _expenses = mutableStateOf(
        listOf(
            Expense(12.50, "Food", "2023-10-05", "Onigiri & Tea", R.drawable.ramen),
            Expense(123.0, "Stay", "2023-10-04", "Hotel Deposit", R.drawable.suite)
        )
    )
    val expenses: State<List<Expense>> = _expenses

    fun addExpense(expense: Expense) {
        _expenses.value = _expenses.value + expense
    }
}
