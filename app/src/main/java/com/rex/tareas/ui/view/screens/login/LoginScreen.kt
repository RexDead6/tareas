package com.rex.tareas.ui.view.screens.login

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rex.tareas.R
import com.rex.tareas.ui.view.components.LoadingDialog
import com.rex.tareas.ui.view.components.ShadowButton
import com.rex.tareas.ui.view.components.CustomInput
import com.rex.tareas.ui.view.screens.login.components.Footer
import com.rex.tareas.ui.view.screens.login.data.models.UserAuth

@Composable
fun LoginScreen(viewModel: LoginViewModel) {

    val userAuth by viewModel.userAuth.observeAsState(initial = UserAuth())
    val enabledLogin by viewModel.enabledLogin.observeAsState(initial = false)
    val inputErrors by viewModel.inputErrors.observeAsState(initial = mutableMapOf())
    val isLoading by viewModel.isLoading.observeAsState(initial = false)

    LoadingDialog(show = isLoading)

    Column(
        Modifier
            .padding(30.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.sign_up),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.size(40.dp))
        CustomInput(
            label = R.string.email,
            text = userAuth.email,
            keyboardType = KeyboardType.Email,
            onChangeText = {
                viewModel.onChangeLoginAuth(it, userAuth.pass)
            },
            error = if (inputErrors.containsKey("email")) {
                inputErrors["email"]!!
            } else {
                ""
            }
        )
        Spacer(modifier = Modifier.size(30.dp))
        CustomInput(label = R.string.pass,
            text = userAuth.pass,
            keyboardType = KeyboardType.Password,
            onChangeText = {
                viewModel.onChangeLoginAuth(userAuth.email, it)
            },
            error = if (inputErrors.containsKey("pass")) {
                inputErrors["pass"]!!
            } else {
                ""
            }
        )
        Spacer(modifier = Modifier.size(40.dp))
        ShadowButton(text = R.string.sign_up, enabled = enabledLogin, onClick = {
            viewModel.onClickLoginEmail()
        })
        Spacer(modifier = Modifier.size(20.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                text = stringResource(id = R.string.msg_login),
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.size(10.dp))

            Text(text = stringResource(id = R.string.msg_login1),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 12.sp,
                modifier = Modifier.clickable {
                    viewModel.navigateToRegister()
                })
        }
        Spacer(modifier = Modifier.size(30.dp))
        Footer(onLoginGoogle = {})
    }
}
