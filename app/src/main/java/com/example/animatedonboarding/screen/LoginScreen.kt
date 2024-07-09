package com.example.animatedonboarding.screen

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.animatedonboarding.R
import com.example.animatedonboarding.model.LoginViewModel
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(navController: NavHostController, loginViewModel: LoginViewModel = viewModel()) {
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var showDialog1 by remember { mutableStateOf(false) }
    var showDialog2 by remember { mutableStateOf(false) }
    if (showDialog1) {
        AlertDialog(
            onDismissRequest = { showDialog1 = false },
            text = { Text(
                text = "Email and password cannot be left blank !",
                fontSize = 16.sp, fontFamily =
                FontFamily(Font(R.font.poppins_bold))
            ) },
            confirmButton = {
                Button(
                    onClick = { showDialog1 = false },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF2B705))
                ) {
                    Text("OK")
                }
            }
        )
    }
    if (showDialog2) {
        AlertDialog(
            onDismissRequest = { showDialog2 = false },
            text = { Text(
                text = "Email or password is wrong !",
                fontSize = 16.sp, fontFamily =
                FontFamily(Font(R.font.poppins_bold))
            ) },
            confirmButton = {
                Button(
                    onClick = { showDialog2 = false },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF2B705))
                ) {
                    Text("OK")
                }
            }
        )
    }

        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)) {
            Image(
                painter = painterResource(id = R.drawable.login_bg),
                contentDescription = "",
                modifier = Modifier.fillMaxSize() )
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
                    text = "Login",
                    modifier = Modifier.padding(10.dp),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = {newEmail -> email = newEmail},
                    label = { Text(text = "Username") },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .padding(10.dp)
                        .width(310.dp),
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = "") },
                    trailingIcon = {
                        if(email != "") {
                            IconButton(onClick = { email = ""}) {
                                Icon(Icons.Default.Clear, contentDescription = "")
                            }
                        }
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = pass,
                    onValueChange = {newPass -> pass = newPass},
                    label = { Text(text = "Password") },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .padding(10.dp)
                        .width(310.dp),
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "") },
                    trailingIcon = {
                        if(pass != "") {
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
                        if(email.isNotEmpty() && pass.isNotEmpty()) {
                            loginViewModel.checkUserCredentials(email, pass) { isValid ->
                                if (isValid) {
                                        navController.navigate("Home")
                                } else {
                                    showDialog2 = true
                                }
                            }
                        } else {
                            showDialog1 = true
                        }
                    },
                    modifier = Modifier
                        .padding(10.dp)
                        .width(310.dp)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF2B705))
                ) {
                    Text(text = "Login", fontSize = 15.sp)
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Divider(
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )
                    Text(text = "or Login with", modifier = Modifier.padding(horizontal = 8.dp))
                    Divider(
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    OutlinedButton(
                        onClick = {},
                        modifier = Modifier
                            .weight(1f)
                            .padding(10.dp)
                            .fillMaxWidth()
                    ) {
                        Image(painter = painterResource(id = R.drawable.gg),
                            contentDescription = "",
                            modifier = Modifier
                                .size(24.dp)
                                .padding(end = 8.dp)
                        )
                        Text(text = "Google")
                    }
                    OutlinedButton(
                        onClick = {},
                        modifier = Modifier
                            .weight(1f)
                            .padding(10.dp)
                    ) {
                        Image(painter = painterResource(id = R.drawable.fb),
                            contentDescription = "",
                            modifier = Modifier
                                .size(24.dp)
                                .padding(end = 8.dp)
                        )
                        Text(text = "Facebook")
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Don't have an account?")
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedButton(
                    onClick = {
                        navController.navigate("Signin")
                    },
                    modifier = Modifier
                        .padding(10.dp)
                        .width(310.dp)
                        .border(2.dp, Color(0xFFF2B705), shape = ButtonDefaults.outlinedShape)
                        .height(56.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFFF2B705),
                    )
                ) {
                    Text(text = "Sign in", fontSize = 15.sp)
                }
            }
        }
}







