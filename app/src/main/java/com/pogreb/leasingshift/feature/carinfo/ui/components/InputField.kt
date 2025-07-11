package com.pogreb.leasingshift.feature.carinfo.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pogreb.leasingshift.ui.theme.CustomTextStyle
import com.pogreb.leasingshift.ui.theme.TextQuartenery

@Composable
fun InputField(
    text: String,
    value: String,
    onValueChange: (String) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
    ) {
        Text(
            text = text,
            style = CustomTextStyle.overInput,
            modifier = Modifier
                .height(20.dp)

        )
        OutlinedTextField(
            value = value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            onValueChange = onValueChange,
            shape = RoundedCornerShape(8.dp),
            placeholder = {
                Text(
                    text = text,
                    color = TextQuartenery
                )
            },
        )
    }
}