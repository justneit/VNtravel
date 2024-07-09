package com.example.animatedonboarding.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.animatedonboarding.R
import com.example.animatedonboarding.model.SigninViewModel
import com.example.animatedonboarding.model.SignupState
import kotlinx.coroutines.launch


@Composable
fun SigninScreen(navController: NavHostController, signinViewModel: SigninViewModel = viewModel()) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val signupState by signinViewModel.signupState.collectAsState()
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    Scaffold(
        scaffoldState = scaffoldState
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.login_bg),
                contentDescription = "",
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier.padding(start = 30.dp, end = 30.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logofinal),
                    contentDescription = "",
                    modifier = Modifier
                        .height(200.dp)
                        .clip(shape = RoundedCornerShape(4.dp))
                )
                Text(
                    text = "Sign in",
                    modifier = Modifier.padding(10.dp),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { newEmail -> email = newEmail },
                    label = { Text(text = "Email") },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .padding(10.dp)
                        .width(310.dp),
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = "") },
                    trailingIcon = {
                        if (email.isNotEmpty()) {
                            IconButton(onClick = { email = "" }) {
                                Icon(Icons.Default.Clear, contentDescription = "")
                            }
                        }
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = username,
                    onValueChange = { newUsername -> username = newUsername },
                    label = { Text(text = "Username") },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .padding(10.dp)
                        .width(310.dp),
                    leadingIcon = { Icon(Icons.Default.Face, contentDescription = "") },
                    trailingIcon = {
                        if (username.isNotEmpty()) {
                            IconButton(onClick = { username = "" }) {
                                Icon(Icons.Default.Clear, contentDescription = "")
                            }
                        }
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = pass,
                    onValueChange = { newPass -> pass = newPass },
                    label = { Text(text = "Password") },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .padding(10.dp)
                        .width(310.dp),
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "") },
                    trailingIcon = {
                        if (pass.isNotEmpty()) {
                            IconButton(onClick = { pass = "" }) {
                                Icon(Icons.Default.Clear, contentDescription = "")
                            }
                        }
                    },
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {
                        if (email.isNotEmpty() && username.isNotEmpty() && pass.isNotEmpty()) {
                            signinViewModel.sendSigninData(email, username, pass)
                        } else {
                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar("Please fill in all fields")
                            }
                        }
                    },
                    modifier = Modifier
                        .padding(10.dp)
                        .width(310.dp)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF2B705))
                ) {
                    Text(text = "Sign in", fontSize = 15.sp)
                }
                Spacer(modifier = Modifier.height(10.dp))
                when (signupState) {
                    is SignupState.Loading -> {
                        CircularProgressIndicator()
                    }
                    is SignupState.Success -> {
                        LaunchedEffect(Unit) {
                            scaffoldState.snackbarHostState.showSnackbar("Sign up successful")
                            navController.navigate("Login")
                        }
                    }
                    is SignupState.Error -> {
                        val errorMessage = (signupState as SignupState.Error).message
                        LaunchedEffect(errorMessage) {
                            scaffoldState.snackbarHostState.showSnackbar(errorMessage)
                        }
                    }
                    else -> {}
                }
            }
        }
    }
}


@Preview
@Composable
fun SigninPre() {
    val navController = rememberNavController()
    SigninScreen(navController = navController)
}
