package com.pogreb.leasingshift.carinfo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.pogreb.leasingshift.R
import com.pogreb.leasingshift.main.Title

@Composable
fun CarInfoScreen(carId: Long) {
    Column(modifier = Modifier.fillMaxSize()) {
        Title(R.string.cars)

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Автомобиль $carId",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}