package com.example.animatedonboarding.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.NotificationsActive
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.animatedonboarding.R
import com.example.animatedonboarding.data.Placee


@Composable
fun PlaceRow(place: Placee) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(color = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = place.imageResId,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .height(150.dp),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 8.dp, top = 10.dp)
                ) {
                    Text(text = place.name,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        modifier = Modifier.padding(start = 15.dp)
                    )
                    Row(modifier = Modifier
                        .padding(start = 10.dp)
                        .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector= Icons.Outlined.LocationOn,
                            contentDescription = "",
                            tint = Color(0xFFF2B705)
                        )
                        Text(
                            text = place.location,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontSize = 14.sp,
                            color = Color(0xFFF2B705)
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "${place.cost}$",
                            fontSize = 25.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            modifier = Modifier.padding(start = 15.dp)
                        )
                        Button(
                            onClick = {},
                            modifier = Modifier
                                .padding(start = 80.dp, end = 20.dp, top = 20.dp, bottom = 20.dp)
                                .width(310.dp)
                                .height(56.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF2B705))
                        ) {
                            Text(text = "Book trip now",
                                fontSize = 15.sp,
                                color = Color.Black,
                                fontFamily = FontFamily(Font(R.font.poppins_medium))
                            )
                        }
                    }
                }
            }
        }
    Spacer(modifier = Modifier.height(25.dp))
}
@Composable
fun PlaceList(places: List<Placee>) {
    LazyColumn {
        items(places) { place ->
            PlaceRow(place = place)
        }
    }
}

@Composable
fun PopularDes() {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Gray.copy(alpha = 0.2f))
            ) {
                val places = listOf(
                    Placee(name = "Da Nang - Hoi An",
                        location = "Da Nang, Hoi An, Quang Nam",
                        imageResId = painterResource(R.drawable.dn_ha),
                        cost = 125
                    ),

                    Placee(name = "Da Lat",
                        location = "Da lat, Lam Dong",
                        imageResId = painterResource(R.drawable.dalat_ngang),
                        cost = 20
                    )
                )
                PlaceList(places = places)
            }
}

@Composable
fun DesScreen(navController: NavController) {
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
                    text = "Popular \n Destination",
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
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxSize()
                    .padding(top = 130.dp, end = 20.dp, start = 20.dp)
                    .background(
                        color = Color.White,
                    )
            ) {
                PopularDes()
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun Pre() {
    val navController = rememberNavController()
   DesScreen(navController)
}



