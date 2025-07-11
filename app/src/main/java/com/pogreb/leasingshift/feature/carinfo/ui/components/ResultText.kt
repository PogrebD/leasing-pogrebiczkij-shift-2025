package com.pogreb.leasingshift.feature.carinfo.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pogreb.leasingshift.ui.theme.CustomTextStyle

@Composable
fun ResultText(label: String, result: String) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = label,
            style = CustomTextStyle.secondaryLight,
            modifier = Modifier
                .height(16.dp)
        )
        Text(
            text = result,
            style = CustomTextStyle.value,
            modifier = Modifier
                .height(24.dp)
        )
    }
}