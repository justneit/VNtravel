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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.RealEstateAgent
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.NotificationsActive
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.animatedonboarding.R
import com.example.animatedonboarding.data.Destination
import com.example.animatedonboarding.model.fetchDestinationByName

@Composable
fun SearchScreen(navController: NavController, searchText: String) {
    var destination by remember { mutableStateOf<Destination?>(null) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(searchText) {
        if (searchText.isNotEmpty()) {
            val result = fetchDestinationByName(searchText)
            destination = result
        }
    }
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
                    text = "Search Result",
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
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    if (destination != null) {
                        SearchUI(navController, destination = destination!!)
                    } else {
                        Text(text = "No result")
                    }
                }
            }
        }
    }

}


@Composable
fun SearchUI(
    navController: NavController,
    destination: Destination
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(end = 10.dp, start = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .clickable{
                    navController.navigate("DesDetails/${destination.name}")
                }
                .fillMaxSize()
                .background(
                    color = Color.White,
                ),

        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
            ) {
                Image(
                    painter = rememberImagePainter(destination.desBanner),
                    contentDescription = "",
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                )
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Text(
                        text = destination.name,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        fontSize = 17.sp,
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "",
                            tint = Color(0xFFF2B705)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "${destination.location} ",
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontSize = 12.sp,
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.width(20.dp))
    }
}