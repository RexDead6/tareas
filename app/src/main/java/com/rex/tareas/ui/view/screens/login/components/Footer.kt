package com.rex.tareas.ui.view.screens.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rex.tareas.R
import com.rex.tareas.ui.theme.GoogleButton

@Composable
fun Footer(onLoginGoogle: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Box(
            Modifier
                .weight(1f)
                .height(1.dp)
                .background(Color.Gray)
        )
        Text(
            text = stringResource(id = R.string.login_accounts),
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Box(
            Modifier
                .weight(1f)
                .height(1.dp)
                .background(Color.Gray)
        )
    }
    Spacer(modifier = Modifier.size(20.dp))
    Button(
        onClick = { onLoginGoogle() },
        modifier = Modifier.fillMaxWidth(),
        shape = ShapeDefaults.ExtraSmall,
        colors = ButtonDefaults.buttonColors(
            containerColor = GoogleButton
        )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.google),
                contentDescription = "google buttom",
                modifier = Modifier.size(30.dp),
                tint = Color.White
            )
            Text(
                text = stringResource(id = R.string.sin_in_google),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth(),
                color = Color.White
            )
        }
    }
}