package com.example.wokolskidashboard.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import com.example.wokolskidashboard.ui.components.BalanceHeader
import com.example.wokolskidashboard.ui.components.IncomeForm
import com.example.wokolskidashboard.ui.components.TransactionCard
import com.example.wokulskidashboard.model.Transaction
import com.example.wokulskidashboard.ui.components.ExpenseForm


@Composable
fun MainScreen() {
    // STATE HOISTING — jedyna lista transakcji w całej aplikacji
    val transactions: SnapshotStateList<Transaction> = remember { mutableStateListOf() }

    // Obliczenie salda na podstawie listy
    val balance = transactions.sumOf { if (it.isExpense) -it.amount else it.amount }

    LazyColumn(
        modifier = Modifier.
        fillMaxSize()
    ) {
        // #5 — BalanceHeader (Developer A)
        item {
            BalanceHeader(balance = balance)
        }

        // #6 — IncomeForm (Developer A)
        item {
            IncomeForm(
                onAddIncome = { transaction ->
                    transactions.add(transaction)
                }
            )
        }

        // ExpenseForm (Developer B)
        item {
            ExpenseForm(
                onAddExpense = { transaction ->
                    transactions.add(transaction)
                }
            )
        }

        // #7 — Lista transakcji z TransactionCard (Developer A)
        items(transactions.reversed()) { transaction ->
            TransactionCard(transaction = transaction)
        }
    }
}
