package com.pogreb.leasingshift.main.ui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pogreb.leasingshift.ui.theme.CustomTextStyle

@Composable
fun Title(titleId: Int) {
    Text(
        text = stringResource(titleId),
        style = CustomTextStyle.appTitle,
        modifier = Modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .height(56.dp),
    )
}