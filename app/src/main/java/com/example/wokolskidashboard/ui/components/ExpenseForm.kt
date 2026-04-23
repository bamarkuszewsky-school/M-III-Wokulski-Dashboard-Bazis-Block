package com.example.wokulskidashboard.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wokulskidashboard.model.Transaction

@Composable
fun ExpenseForm(
    onAddExpense: (Transaction) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var isUnnecessary by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "📒 Księga Wydatków", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(8.dp))

        WokulskiTextField(
            value = name,
            onValueChange = { name = it },
            label = "Cel wydatku (np. Kareta dla panny Izabeli)"
        )

        Spacer(modifier = Modifier.height(8.dp))

        WokulskiTextField(
            value = amount,
            onValueChange = { amount = it },
            label = "Kwota (w rublach)"
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Wydatek zbyteczny",
                modifier = Modifier.weight(1f)
            )
            Switch(
                checked = isUnnecessary,
                onCheckedChange = { isUnnecessary = it }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        WokulskiButton(
            text = "Zapisz wydatek",
            onClick = {
                val parsedAmount = amount.toDoubleOrNull()
                if (name.isNotBlank() && parsedAmount != null && parsedAmount > 0) {
                    onAddExpense(
                        Transaction(
                            name = name,
                            amount = parsedAmount,
                            isExpense = true
                        )
                    )
                    name = ""
                    amount = ""
                    isUnnecessary = false
                }
            }
        )
    }
}