package com.pogreb.leasingshift.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.pogreb.leasingshift.R
import com.pogreb.leasingshift.main.ui.Title

@Composable
fun ProfileScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Title(R.string.profile)

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(R.string.profile_annotation),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}
