package com.example.wokolskidashboard.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wokulskidashboard.model.Transaction
import com.example.wokulskidashboard.ui.components.WokulskiButton
import com.example.wokulskidashboard.ui.components.WokulskiTextField

@Composable
fun IncomeForm(
    onAddIncome: (Transaction) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf("") }
    var amountError by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "📒 Księga Przychodów", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(8.dp))

        WokulskiTextField(
            value = name,
            onValueChange = {
                name = it
                nameError = ""
            },
            label = "Nazwa towaru (np. Paryskie rękawiczki)"
        )
        if (nameError.isNotEmpty()) {
            Text(
                text = nameError,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 4.dp, top = 2.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        WokulskiTextField(
            value = amount,
            onValueChange = {
                amount = it
                amountError = ""
            },
            label = "Kwota (w rublach)"
        )
        if (amountError.isNotEmpty()) {
            Text(
                text = amountError,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 4.dp, top = 2.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        WokulskiButton(
            text = "Zapisz przychód",
            onClick = {
                val parsedAmount = amount.toDoubleOrNull()

                var valid = true

                if (name.isBlank()) {
                    nameError = "⚠️ Podaj nazwę towaru"
                    valid = false
                }
                if (parsedAmount == null || parsedAmount <= 0) {
                    amountError = "⚠️ Podaj prawidłową kwotę (większą niż 0)"
                    valid = false
                }

                if (valid) {
                    onAddIncome(
                        Transaction(
                            name = name,
                            amount = parsedAmount!!,
                            isExpense = false
                        )
                    )
                    name = ""
                    amount = ""
                    nameError = ""
                    amountError = ""
                }
            }
        )
    }
}