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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.RealEstateAgent
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.animatedonboarding.R
import com.example.animatedonboarding.data.OrderData
import com.example.animatedonboarding.model.LoginViewModel
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun YourTripScreen(loginViewModel: LoginViewModel = viewModel(), navController: NavHostController) {
    val currentUserUid = loginViewModel.getCurrentUserUid()
    var orders by remember { mutableStateOf<List<OrderData>>(emptyList()) }

    LaunchedEffect(currentUserUid) {
        if (currentUserUid != null) {
            getOrdersForCurrentUser(currentUserUid) { retrievedOrders ->
                orders = retrievedOrders
            }
        }
    }
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) {
            padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize(),
                shape = RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
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
                            .padding(20.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier
                            .clip(RoundedCornerShape(15.dp))
                            .background(Color.White)
                            .clickable {
                                navController.popBackStack()
                            }
                            .padding(6.dp)
                        ) {
                            Icon(imageVector= Icons.Outlined.ArrowBack,
                                contentDescription = "",
                                tint = Color(0xFFF2B705)
                            )
                        }
                        Text(
                            text = "Your Trip",
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

                        }

                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxSize()
                            .padding(top = 130.dp, end = 20.dp, start = 20.dp)
                            .background(
                                color = Color.White,
                            )
                    ) {
                        Column {
                            orders.forEach { order ->
                                YourTripUI(order)
                                Spacer(modifier = Modifier.height(20.dp))
                            }
                        }
                    }
                }
            }
        }
    }


}


@Composable
fun YourTripUI(
    orderData: OrderData
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
            .padding(end = 10.dp, start = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.White,
                ),
        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
            ) {
                Image(
                    painter = rememberImagePainter(orderData.banner),
                    contentDescription = "",
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .size(width = 100.dp, height = 230.dp),
                        contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(20.dp))
                Column {
                    Text(
                        text = orderData.desTitle,
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
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Date: ${orderData.encodedDate}",
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontSize = 12.sp,
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            imageVector = Icons.Default.Group,
                            contentDescription = "",
                            tint = Color(0xFFF2B705)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Guests: ${orderData.numberGR}",
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontSize = 12.sp,
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            imageVector = Icons.Default.Money,
                            contentDescription = "",
                            tint = Color(0xFFF2B705)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Cost: ${orderData.totalPrice}",
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontSize = 12.sp,
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "",
                            tint = Color(0xFFF2B705)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Email: ${orderData.email}",
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontSize = 12.sp,
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            imageVector = Icons.Default.RealEstateAgent,
                            contentDescription = "",
                            tint = Color(0xFFF2B705)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Payment status: ",
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontSize = 12.sp,
                        )
                    }
                }
            }
        }

    }
}


fun getOrdersForCurrentUser(
    currentUserUid: String,
    onOrdersRetrieved: (List<OrderData>) -> Unit
) {
    val db = FirebaseFirestore.getInstance()
    db.collection("order")
        .whereEqualTo("idUser", currentUserUid)
        .get()
        .addOnSuccessListener { result ->
            val orders = result.documents.mapNotNull { document ->
                document.toObject(OrderData::class.java)
            }
            onOrdersRetrieved(orders)
        }
        .addOnFailureListener { exception ->
            // Handle the error
        }
}
