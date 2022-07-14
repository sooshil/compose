package com.sukajee.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun TextFieldsSnackbar() {

    var textFieldState by remember {
        mutableStateOf("")
    }
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                label = {
                    Text("Enter your name")
                },
                value = textFieldState,
                onValueChange = {
                    textFieldState = it
                }
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Button(
                onClick = {
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = "Hello $textFieldState",
                            duration = SnackbarDuration.Long
                        )
                    }
                }
            ) {
                Text(text = "Greet me!")
            }
        }
    }
}
