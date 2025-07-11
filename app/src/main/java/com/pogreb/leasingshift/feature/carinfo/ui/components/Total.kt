package com.pogreb.leasingshift.feature.carinfo.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pogreb.leasingshift.R
import com.pogreb.leasingshift.shared.domain.entity.DateBooking
import com.pogreb.leasingshift.shared.ui.components.formatDateBooking
import com.pogreb.leasingshift.ui.theme.TextPrimary
import com.pogreb.leasingshift.ui.theme.TextSecondary

@Composable
fun Total(date: DateBooking, totalPrice: Long) {
    Text(
        text = stringResource(R.string.total_price, totalPrice * date.numDays),
        style = TextStyle(
            color = TextPrimary,
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
        ),
        modifier = Modifier
            .height(24.dp)
            .fillMaxWidth(),
    )

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = formatDateBooking(date),
        style = TextStyle(
            color = TextSecondary,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
        ),
        modifier = Modifier
            .height(24.dp)
            .fillMaxWidth(),
    )
}