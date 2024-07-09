package com.example.animatedonboarding.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.DirectionsBoat
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.animatedonboarding.R
import com.example.animatedonboarding.data.Yatch
import com.example.animatedonboarding.model.YatchViewModel

@Composable
fun YatchScreens(navController: NavController, viewModel: YatchViewModel) {
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
                    text = "Yatch",
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
                val yatchItem = viewModel.yatch
                LazyColumn(contentPadding = PaddingValues(16.dp)) {
                    items(yatchItem.size) { index ->
                        YatchRowItem(
                            yatch = yatchItem[index],
                            navController = navController
                        )
                    }
                }
            }
        }
    }

}
@Composable
fun YatchRowItem(
    yatch: Yatch,
    navController: NavController,
) {
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
                painter = painterResource(yatch.imageYatchId),
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
                Row(modifier = Modifier
                    .padding(start = 12.dp, bottom = 7.dp)
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector= Icons.Outlined.LocationOn,
                        contentDescription = "",
                        modifier = Modifier.size(12.dp)
                    )
                    Text(
                        text = yatch.yatchLocation,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontSize = 10.sp,
                    )
                }
                Text(text = yatch.yatchName,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    modifier = Modifier.padding(start = 15.dp)
                )
                Row(modifier = Modifier
                    .padding(start = 10.dp)
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector= Icons.Outlined.DirectionsBoat,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(20.dp)
                    )
                    Text(
                        text = yatch.yatchInfo,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontSize = 14.sp,
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "${yatch.cost}$",
                            fontSize = 22.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            modifier = Modifier.padding(start = 15.dp)
                        )
                        Text(text = "/ person", fontFamily = FontFamily(Font(R.font.poppins_medium)))
                    }
                    Button(
                        onClick = {
                            navController.navigate("YatchDetail/${yatch.imageYatchId}")
                        },
                        modifier = Modifier
                            .padding(start = 70.dp, end = 20.dp, top = 20.dp, bottom = 20.dp)
                            .width(310.dp)
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF2B705))
                    ) {
                        Text(text = "Book now",
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