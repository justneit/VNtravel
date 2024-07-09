package com.example.animatedonboarding.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Bed
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.NotificationsActive
import androidx.compose.material.icons.outlined.Person2
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animatedonboarding.R
import com.example.animatedonboarding.data.Yatch
import com.example.animatedonboarding.data.YatchRoom


@Composable
fun YatchRoomRows(yatchRoom: YatchRoom) {
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
                painter = yatchRoom.yatchRoomImg,
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
                Text(text = yatchRoom.yatchRoomName,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    modifier = Modifier.padding(start = 15.dp)
                )

                Row(modifier = Modifier
                    .padding(start = 10.dp)
                    .fillMaxWidth(),
                ) {
                    Icon(imageVector= Icons.Outlined.Bed,
                        contentDescription = "",
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = yatchRoom.acreage,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontSize = 16.sp,
                    )
                    Spacer(modifier = Modifier.width(30.dp))
                    Text(
                        text = "Max: ${yatchRoom.amountPeople}",
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontSize = 16.sp,
                    )
                    Icon(imageVector= Icons.Outlined.Person2,
                        contentDescription = "",
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "${yatchRoom.cost}$",
                        fontSize = 23.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }
                SelectRoom()
            }
        }
    }
    Spacer(modifier = Modifier.height(25.dp))
}
@Composable
fun YatchRoomList(yatchRoom: List<YatchRoom>) {
    LazyColumn {
        items(yatchRoom) { yatchRooms ->
            YatchRoomRows(yatchRoom = yatchRooms)
        }
    }
}

@Composable
fun YatchRoomData() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Gray.copy(alpha = 0.2f))
    ) {
        val yatchroom = listOf(
            YatchRoom(yatchRoomName = "Elite Junior Suite Room",
                cost = 237,
                yatchRoomImg = painterResource(R.drawable.elitesuite),
                amountPeople = 2,
                acreage = "23m²"
            ),
            YatchRoom(yatchRoomName = "Elite Senior Premium Suite",
                cost = 319,
                yatchRoomImg = painterResource(R.drawable.elite_senior_premium_suite),
                amountPeople = 2,
                acreage = "28m²"
            ),
            YatchRoom(yatchRoomName = "Executive Suite",
                cost = 421,
                yatchRoomImg = painterResource(R.drawable.executive_suite),
                amountPeople = 2,
                acreage = "30m²"
            ),

        )
        YatchRoomList(yatchRoom = yatchroom)
    }
}

@Composable
fun YatchRoomScreen() {
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
                    .clickable { }
                    .padding(6.dp)
                ) {
                    Icon(imageVector= Icons.Outlined.ArrowBack,
                        contentDescription = "",
                        tint = Color(0xFFF2B705)
                    )
                }
                Text(
                    text = "Elite of \n the Seas Cruise",
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
               YatchRoomData()
            }
        }
    }
}
@Composable
fun SelectRoom() {
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("1", "2", "3")
    var selectedOption by remember { mutableStateOf(options.first()) }
    Box(modifier = Modifier
        .padding(20.dp)
        .fillMaxWidth()
        .height(50.dp)
        .border(2.dp, Color.LightGray, shape = RoundedCornerShape(20.dp))
        .wrapContentSize()) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(

            ) {
                IconButton(onClick = { expanded = true }) {
                    androidx.compose.material.Icon(Icons.Default.KeyboardArrowDown, contentDescription = null)
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.padding(16.dp)
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(onClick = {
                            expanded = false
                            selectedOption = option // Set the selected option
                        }) {
                            androidx.compose.material.Text(text = option)
                        }
                    }
                }

                androidx.compose.material.Text(
                    text = "Quantity: $selectedOption",
                    modifier = Modifier.padding(16.dp)
                )
            }
            androidx.compose.material.Button(
                onClick = {},
                modifier = Modifier
                    .width(160.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp),
                colors = androidx.compose.material.ButtonDefaults.buttonColors(Color(0xFFF2B705))
            ) {
                androidx.compose.material.Text(text = "Book now")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun Pres() {
    YatchRoomScreen()
}