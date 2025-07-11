package com.pogreb.leasingshift.feature.carinfo.ui.booking

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pogreb.leasingshift.R
import com.pogreb.leasingshift.feature.carinfo.presentation.CarInfoViewModel
import com.pogreb.leasingshift.feature.carinfo.ui.components.NextButton


@Composable
fun SuccessfulResult(
    viewModel: CarInfoViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
    ) {
        //ResultText()

        //ResultText()

        //ResultText()

        //ResultText()

        //ResultText()

        Text(text = stringResource(R.string.successful_result_additional_information))

        //SeeResultButton()

        NextButton(
            text = stringResource(R.string.to_main_page_button),
            onClick = viewModel::switchBookingState,
        )
    }
}

@Composable
private fun SeeResultButton() {
    OutlinedButton(
        onClick = { },
        modifier = Modifier
            .padding(top = 16.dp)
            .height(56.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
    ) {
        Text(stringResource(R.string.view_status))
    }
}