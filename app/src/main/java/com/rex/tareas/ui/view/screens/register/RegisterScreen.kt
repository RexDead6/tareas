package com.rex.tareas.ui.view.screens.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rex.tareas.R
import com.rex.tareas.ui.view.components.LoadingDialog
import com.rex.tareas.ui.view.components.ShadowButton
import com.rex.tareas.ui.view.components.CustomInput
import com.rex.tareas.ui.view.screens.register.data.models.UserRegister

@Composable
fun RegisterScreen(viewModel: RegisterViewModel) {

    val userRegister by viewModel.userRegister.observeAsState(initial = UserRegister())
    val enabledButton by viewModel.enabledButton.observeAsState(initial = false)
    val isLoading by viewModel.isLoading.observeAsState(initial = false)

    LoadingDialog(show = isLoading)

    Column(
        Modifier.padding(top = 80.dp, start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.sing_in),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.size(40.dp))
        CustomInput(
            label = R.string.name,
            text = userRegister.name,
            keyboardType = KeyboardType.Text,
            onChangeText = {
                viewModel.onChangeUserRegister(
                    name = it,
                    email = userRegister.email,
                    pass = userRegister.pass,
                    passConfirm = userRegister.passConfirm
                )
            })
        Spacer(modifier = Modifier.size(20.dp))
        CustomInput(
            label = R.string.email,
            text = userRegister.email,
            keyboardType = KeyboardType.Email,
            onChangeText = {
                viewModel.onChangeUserRegister(
                    name = userRegister.name,
                    email = it,
                    pass = userRegister.pass,
                    passConfirm = userRegister.passConfirm
                )
            })
        Spacer(modifier = Modifier.size(20.dp))
        CustomInput(
            label = R.string.pass,
            text = userRegister.pass,
            keyboardType = KeyboardType.Password,
            onChangeText = {
                viewModel.onChangeUserRegister(
                    name = userRegister.name,
                    email = userRegister.email,
                    pass = it,
                    passConfirm = userRegister.passConfirm
                )
            })
        Spacer(modifier = Modifier.size(20.dp))
        CustomInput(
            label = R.string.pass_confirm,
            text = userRegister.passConfirm,
            keyboardType = KeyboardType.Password,
            onChangeText = {
                viewModel.onChangeUserRegister(
                    name = userRegister.name,
                    email = userRegister.email,
                    pass = userRegister.pass,
                    passConfirm = it
                )
            })
        Spacer(modifier = Modifier.size(30.dp))
        ShadowButton(text = R.string.sing_in, enabled = enabledButton, onClick = {
            viewModel.registerUser()
        })
        Spacer(modifier = Modifier.size(30.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                text = stringResource(id = R.string.msg_register),
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.size(10.dp))

            Text(text = stringResource(id = R.string.msg_register1),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 12.sp,
                modifier = Modifier.clickable {
                    viewModel.backButton()
                })
        }
    }
}