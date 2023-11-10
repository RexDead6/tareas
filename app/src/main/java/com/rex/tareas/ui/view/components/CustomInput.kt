package com.rex.tareas.ui.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.rex.tareas.ui.theme.RedError

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomInput(
    label: Int,
    text: String,
    keyboardType: KeyboardType,
    onChangeText: (String) -> Unit,
    error: String = ""
) {
    TextField(
        value = text,
        onValueChange = { onChangeText(it) },
        maxLines = 1,
        label = {
            Text(
                text = stringResource(id = label),
                color = MaterialTheme.colorScheme.primary
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.background,
            errorIndicatorColor = RedError,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier.fillMaxWidth(),
        supportingText = {
            Text(text = error)
        },
        isError = error.isNotEmpty()
    )
}