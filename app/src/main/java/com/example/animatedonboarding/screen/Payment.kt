package com.example.animatedonboarding.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.NotificationsActive
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.animatedonboarding.R
import com.example.animatedonboarding.model.LoginViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

@Composable
fun PaymentScreen(
    navController: NavHostController,
    desTitle: String,
    encodedDate: String,
    numberGR: Int,
    totalPrice: Int,
    banner: String,
    loginViewModel: LoginViewModel = viewModel()
) {
    val db = Firebase.firestore
    val collectionRef = db.collection("order")
    val currentUserUid = loginViewModel.getCurrentUserUid()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Card(
            modifier = Modifier,
            shape = RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp)
        ) {
            Box(
                modifier = Modifier

            ) {
                Image(painter = painterResource(R.drawable.banner_app),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(190.dp)
                        .clip(
                            shape = RoundedCornerShape(
                                bottomStart = 50.dp,
                                bottomEnd = 50.dp
                            )
                        ),
                    contentScale = ContentScale.Crop
                )
                Row(
                    modifier = Modifier
                        .padding(top = 50.dp, start = 20.dp, end = 20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .background(Color.White)
                        .clickable {

                        }
                        .padding(6.dp)
                    ) {
                        Icon(imageVector= Icons.Outlined.ArrowBack,
                            contentDescription = "",
                            tint = Color(0xFFF2B705)
                        )
                    }
                    Text(
                        text = "Payment",
                        fontSize = 23.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    )
                    Box(modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .background(Color.White)
                        .clickable { }
                        .padding(6.dp)
                    ) {
                        Icon(imageVector= Icons.Outlined.NotificationsActive,
                            contentDescription = "",
                            tint = Color(0xFFF2B705)
                        )
                    }

                }

            }
        }
        Text(
            text = "Contact Details",
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 10.dp, start = 20.dp)
        )
        var name by remember {
            mutableStateOf("")
        }
        var phoneNumber by remember {
            mutableStateOf("")
        }
        var email by remember {
            mutableStateOf("")
        }
        OutlinedTextField(
            value = name,
            onValueChange = {newUS ->name = newUS},
            label = { Text(text = "Name") },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
                .width(310.dp),
            trailingIcon = {
                if(name != "") {
                    IconButton(onClick = { name = ""}) {
                        Icon(Icons.Default.Clear, contentDescription = "")
                    }
                }
            }
        )
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = {newphoneNumber ->phoneNumber = newphoneNumber},
            label = { Text(text = "Phone Number") },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 10.dp)
                .width(310.dp),
            trailingIcon = {
                if(phoneNumber != "") {
                    IconButton(onClick = { phoneNumber = ""}) {
                        Icon(Icons.Default.Clear, contentDescription = "")
                    }
                }
            }
        )
        OutlinedTextField(
            value = email,
            onValueChange = {newemail ->email = newemail},
            label = { Text(text = "Email") },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 10.dp)
                .width(310.dp),
            trailingIcon = {
                if(email != "") {
                    IconButton(onClick = { email = ""}) {
                        Icon(Icons.Default.Clear, contentDescription = "")
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(end = 10.dp, start = 10.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.White,
                    ),
            ) {
                Row(
                    modifier = Modifier.padding(10.dp)
                ) {
                        Image(
                            painter = rememberImagePainter(banner),
                            contentDescription = "",
                            modifier = Modifier.clip(RoundedCornerShape(16.dp))
                        )
                    Spacer(modifier = Modifier.width(20.dp))
                    Column {
                        Text(
                            text = "$desTitle",
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            fontSize = 17.sp,
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Icon(
                                imageVector = Icons.Default.CalendarMonth,
                                contentDescription = "",
                                tint = Color(0xFFF2B705)
                            )
                                Text(
                                    text = "Date: $encodedDate",
                                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                    fontSize = 12.sp,
                                )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Icon(
                                imageVector = Icons.Default.Group,
                                contentDescription = "",
                                tint = Color(0xFFF2B705)
                            )
                            Text(
                                text = "Guests: $numberGR",
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                fontSize = 12.sp,
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Icon(
                                imageVector = Icons.Default.Money,
                                contentDescription = "",
                                tint = Color(0xFFF2B705)
                            )
                            Text(
                                text = "Cost: $totalPrice $",
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                fontSize = 12.sp,
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                val dataOrder = hashMapOf(
                    "name" to name,
                    "phoneNumber" to phoneNumber,
                    "email" to email,
                    "desTitle" to desTitle,
                    "encodedDate" to encodedDate,
                    "numberGR" to numberGR,
                    "totalPrice" to totalPrice,
                    "idUser" to currentUserUid,
                    "banner" to banner
                )
                collectionRef.add(dataOrder)
                    .addOnSuccessListener { documentReference ->
                        println("DocumentSnapshot added with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        println("Error adding document: $e")
                    }
                navController.navigate("Thanks")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
                .width(310.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF2B705))
        ) {
            Text(text = "Done",
                fontSize = 15.sp,
                color = Color.Black,
                fontFamily = FontFamily(Font(R.font.poppins_medium))
            )
        }
 }
}

