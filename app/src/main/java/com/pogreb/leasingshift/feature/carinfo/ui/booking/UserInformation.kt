package com.pogreb.leasingshift.feature.carinfo.ui.booking

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.pogreb.leasingshift.R
import com.pogreb.leasingshift.feature.carinfo.domain.entity.UserInformationModel
import com.pogreb.leasingshift.feature.carinfo.presentation.CarInfoViewModel
import com.pogreb.leasingshift.feature.carinfo.ui.components.InputField
import com.pogreb.leasingshift.feature.carinfo.ui.components.NextButton
import com.pogreb.leasingshift.ui.theme.BorderLight
import com.pogreb.leasingshift.ui.theme.Brand
import com.pogreb.leasingshift.ui.theme.CustomTextStyle
import com.pogreb.leasingshift.ui.theme.TextQuartenery
import kotlin.math.absoluteValue

@Composable
fun UserInformation(
    viewModel: CarInfoViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
    ) {
        var userInformationData by remember { mutableStateOf(UserInformationModel()) }
        var isAgree by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .weight(1F)
                .verticalScroll(rememberScrollState())
        ) {

            InputField(
                stringResource(R.string.second_name_label),
                value = userInformationData.secondName,
                onValueChange = { value ->
                    userInformationData = userInformationData.copy(secondName = value)
                },
            )

            InputField(
                stringResource(R.string.first_name_label),
                value = userInformationData.firstName,
                onValueChange = { value ->
                    userInformationData = userInformationData.copy(firstName = value)
                },
            )

            InputField(
                stringResource(R.string.middle_name_label),
                value = userInformationData.middleName,
                onValueChange = { value ->
                    userInformationData = userInformationData.copy(middleName = value)
                },
            )

            DateOfBirthField(
                stringResource(R.string.dob_label),
                value = userInformationData.dateOfBirth,
                onValueChange = { value ->
                    userInformationData = userInformationData.copy(dateOfBirth = value)
                },
            )

            PhoneNumberInputField(
                text = stringResource(R.string.phone_label),
                value = userInformationData.phoneNumber,
                onValueChange = { value ->
                    userInformationData = userInformationData.copy(phoneNumber = value)
                }
            )

            InputField(
                stringResource(R.string.email_label),
                value = userInformationData.email,
                onValueChange = { value ->
                    userInformationData = userInformationData.copy(email = value)
                },
            )

            CommentInPutField(
                stringResource(R.string.comment_label),
                value = userInformationData.comment,
                onValueChange = { value ->
                    userInformationData = userInformationData.copy(comment = value)
                },
            )

            CheckUserAgreement(
                isAgree = isAgree,
                onCheckedChange = { value -> isAgree = value })
        }
        NextButton(
            text = stringResource(R.string.next_button),
            onClick = {
                if (isAgree) {
                    viewModel.switchBookingState()
                    viewModel.updateUserInfoData(userInformationData)
                }
            },
        )
    }
}

@Composable
private fun CheckUserAgreement(
    isAgree: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row {
        Checkbox(
            checked = isAgree,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = Brand,
                uncheckedColor = BorderLight
            ),
        )
        Text(
            text = stringResource(R.string.user_agreement_check_box),
            style = CustomTextStyle.primary,
            modifier = Modifier.padding(vertical = 14.dp)
        )
    }
}

@Composable
private fun CommentInPutField(
    text: String = "",
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
                .heightIn(min = 100.dp)
                .padding(top = 4.dp),
            onValueChange = onValueChange,
            minLines = 3,
            singleLine = false,
            shape = RoundedCornerShape(8.dp),
            placeholder = {
                Text(
                    text = stringResource(R.string.comment_hint),
                    color = TextQuartenery
                )
            },
        )
    }
}

@Composable
private fun PhoneNumberInputField(
    text: String,
    value: String = "",
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
            onValueChange =
                { value ->
                    val digitsOnly = value.filter { it.isDigit() }
                    if (digitsOnly.length <= 10) {
                        onValueChange(digitsOnly)
                    }
                },
            visualTransformation = PhoneVisualTransformation(
                mask = "+7 (###) ### ##-##"
            ),
            shape = RoundedCornerShape(8.dp),
            placeholder = {
                Text(
                    text = text,
                    color = TextQuartenery
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true,
        )
    }
}

@Composable
fun DateOfBirthField(
    text: String,
    value: String = "",
    onValueChange: (String) -> Unit
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
            onValueChange = { value ->
                val digitsOnly = value.filter { it.isDigit() }
                if (digitsOnly.length <= 8) {
                    onValueChange(digitsOnly)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            visualTransformation = DateVisualTransformation(),
            placeholder = { Text("__.__.____") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true,
        )
    }
}

private class DateVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = text.text.take(8)
        var out = ""
        for (i in trimmed.indices) {
            out += trimmed[i]
            when (i) {
                1 -> out += "."
                3 -> out += "."
            }
        }

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return when {
                    offset <= 1 -> offset
                    offset <= 3 -> offset + 1
                    else -> offset + 2
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when {
                    offset <= 2 -> offset
                    offset <= 5 -> offset - 1
                    else -> offset - 2
                }
            }
        }

        return TransformedText(AnnotatedString(out), offsetMapping)
    }
}

private class PhoneVisualTransformation(private val mask: String) : VisualTransformation {
    private val specialSymbolsIndices = mask.indices.filter { mask[it] != '#' }

    override fun filter(text: AnnotatedString): TransformedText {
        var out = ""
        var maskIndex = 0
        text.forEach { char ->
            while (specialSymbolsIndices.contains(maskIndex)) {
                out += mask[maskIndex]
                maskIndex++
            }
            out += char
            maskIndex++
        }
        return TransformedText(AnnotatedString(out), offsetTranslator())
    }

    private fun offsetTranslator() = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            val offsetValue = offset.absoluteValue
            if (offsetValue == 0) return 0
            var numberOfHashtags = 0
            val masked = mask.takeWhile {
                if (it == '#') numberOfHashtags++
                numberOfHashtags < offsetValue
            }
            return masked.length + 1
        }

        override fun transformedToOriginal(offset: Int): Int {
            return mask.take(offset.absoluteValue).count { it == '#' }
        }
    }
}