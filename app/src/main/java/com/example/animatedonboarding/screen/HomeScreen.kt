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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.NotificationsActive
import androidx.compose.material.icons.outlined.Rocket
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Tour
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.animatedonboarding.R
import com.example.animatedonboarding.data.BottomNavigation
import com.example.animatedonboarding.data.Cards
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.animatedonboarding.data.Des
import com.example.animatedonboarding.data.Destination
import com.example.animatedonboarding.model.DataViewModel
import com.google.gson.Gson


@Composable
fun HomeScreen(navController: NavHostController) {
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
            Section(navController)
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavigation(
            title = "Home",
            icon = Icons.Outlined.Home
        ),
        BottomNavigation(
            title = "Your trip",
            icon = Icons.Outlined.Tour
        ),
        BottomNavigation(
            title = "Favourite",
            icon = Icons.Outlined.FavoriteBorder
        ),
        BottomNavigation(
            title = "Profile",
            icon = Icons.Outlined.AccountCircle
        ),
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val selectedIndex = when (currentRoute) {
        "home" -> 0
        "yourtrip" -> 1
        "favourite" -> 2
        "profile" -> 3
        else -> 0
    }

    NavigationBar {
        Row(
            modifier = Modifier
                .background(color = Color.White)
                .shadow(
                    elevation = 0.5.dp,
                    shape = RoundedCornerShape(0.dp)
                )
        ) {
            items.forEachIndexed { index, item ->
                val isSelected = index == selectedIndex
                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        when (item.title) {
                            "Home" -> navController.navigate("home")
                            "Your trip" -> navController.navigate("yourtrip")
                            "Favourite" -> navController.navigate("favourite")
                            "Profile" -> navController.navigate("profile")
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                            tint = if (isSelected) Color(0xFFF2B705) else Color.Gray
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            style = if (isSelected) TextStyle(
                                fontSize = 16.sp,
                                color = Color(0xFFF2B705)
                            ) else TextStyle(
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        )
                    }
                )
            }
        }
    }
}



@Composable
fun Section(navController: NavHostController) {
    val searchText = remember { mutableStateOf("") }
   Column(
       modifier = Modifier
           .fillMaxWidth()
           .padding(13.dp),
   ) {
       Row(
           modifier = Modifier
               .fillMaxWidth()
               .padding(15.dp),
           horizontalArrangement = Arrangement.SpaceBetween,
           verticalAlignment = Alignment.CenterVertically
       ) {
           Column {
               Text(text = "InfiniTour", fontSize = 22.sp,
                   fontFamily = FontFamily(Font(R.font.poppins_bold )),
                   fontWeight = FontWeight.ExtraBold,
                   color = Color(0xFFF2B705),
               )
               Spacer(modifier = Modifier.height(4.dp))
           }
           Box(modifier = Modifier
               .clip(RoundedCornerShape(15.dp))
               .background(Color(0x80D3D3D3))
               .clickable {
                   navController.navigate("AiChat")
               }
               .padding(6.dp)
           ) {
               Image(painter = painterResource(R.drawable.chatbot),
                   contentDescription = "",
                   modifier = Modifier.size(50.dp)
                   )
           }
       }
       OutlinedTextField(
           value = searchText.value,
           onValueChange = { searchText.value = it },
           label = { Text("Search your destination") },
           shape = RoundedCornerShape(15.dp),
           modifier = Modifier
               .fillMaxWidth()
               .padding(start = 10.dp, end = 10.dp),
           leadingIcon = {
               Icon(
                   imageVector = Icons.Outlined.Search,
                   contentDescription = "Tìm",
                   modifier = Modifier.clickable {
                       navController.navigate("Search/${searchText.value}")
                   }
               )
           }
       )
       ExploreMore(navController)
       Row( modifier = Modifier
           .fillMaxWidth()
           .padding(15.dp),
           verticalAlignment = Alignment.CenterVertically,
           horizontalArrangement = Arrangement.SpaceBetween
       ) {
           Text(
               text = "Popular Destinations",
               fontFamily = FontFamily(Font(R.font.poppins_bold)),
               fontWeight = FontWeight.Bold,
               fontSize = 17.sp,
           )
           Spacer(modifier = Modifier.width(100.dp))
               Text(
                   text = "See all",
                   color = Color(0xFFF2B705),
                   fontFamily = FontFamily(Font(R.font.poppins_medium)),
                   fontSize = 15.sp,
                   modifier = Modifier.clickable{
                       navController.navigate("PorpularDes")
                   }
               )

       }
       Places(navController, viewModel = viewModel())

   }
}


@Composable
fun Places(navController: NavHostController, viewModel: DataViewModel) {
    val desItems = viewModel.state.value
    LazyRow(
        modifier = Modifier.padding(top = 10.dp)
    ) {
        items(desItems) { destination ->
            desCard(destination, navController)
        }
    }
}

@Composable
fun desCard(
    destination: Destination,
    navController: NavHostController
) {
    Card(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(30.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .height(280.dp)
                .width(210.dp)
                .clickable {
                    navController.navigate("DesDetails/${destination.name}")
                }
                .clip(shape = RoundedCornerShape(30.dp)),
        ) {
            Image(
                painter = rememberImagePainter(destination.imageResId),
                contentDescription = "",
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp, start = 15.dp, end = 15.dp)
                    .fillMaxWidth()
                    .background(
                        Color.Black.copy(alpha = 0.7f),
                        RoundedCornerShape(16.dp)
                    )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = destination.name,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontSize = 16.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = destination.location,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}


@Composable
fun desCardView(des: Des, navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(30.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ,
    ) {
        Box(
            modifier = Modifier
                .height(280.dp)
                .width(210.dp)
                .clickable {
                    navController.navigate("DesDetails/${des.imageResId}")
                }
                .clip(shape = RoundedCornerShape(30.dp)),
        ) {
            Image(painter = painterResource(des.imageResId),
                contentDescription = "",
                modifier = Modifier
                    .matchParentSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp, start = 15.dp, end = 15.dp)
                    .fillMaxWidth()
                    .background(
                        Color.Black.copy(alpha = 0.7f),
                        RoundedCornerShape(16.dp)
                    )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = des.name,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontSize = 16.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = des.location,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun ExploreMore(navController: NavHostController) {
    Column(modifier = Modifier
        .padding(15.dp)
        ) {
        Row( modifier = Modifier
            .padding(top = 15.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Explore",
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .alpha(0.5f),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                   Box(modifier = Modifier
                       .clip(shape = RoundedCornerShape(20.dp))
                       .background(color = Color.Red.copy(alpha = 0.2f))
                       .width(90.dp)
                       .height(70.dp),
                        contentAlignment = Alignment.Center
                   ) {
                       Image(painter = painterResource(R.drawable.hotel),
                           contentDescription = "",
                           modifier = Modifier.size(40.dp)
                       )
                   }
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = "Hotels", fontSize = 15.sp,fontFamily = FontFamily(Font(R.font.poppins_medium)))
                }
            }
            Box(
                modifier = Modifier
                    .alpha(0.5f),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(modifier = Modifier
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(color = Color(0xFF9400D3).copy(alpha = 0.2f))
                        .width(90.dp)
                        .height(70.dp)
                        .clickable {
                            navController.navigate("Yatch")
                        },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(painter = painterResource(R.drawable.yatchf),
                            contentDescription = "",
                            modifier = Modifier.size(40.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = "Yatch", fontSize = 15.sp,fontFamily = FontFamily(Font(R.font.poppins_medium)))
                }
            }

            Box(
                modifier = Modifier
                    .alpha(0.5f),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(modifier = Modifier
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(color = Color.Green.copy(alpha = 0.2f))
                        .width(90.dp)
                        .height(70.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(painter = painterResource(R.drawable.resorts),
                            contentDescription = "",
                            modifier = Modifier.size(40.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = "Resort", fontSize = 15.sp,fontFamily = FontFamily(Font(R.font.poppins_medium)))
                }
            }

        }
    }
}






@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController)
}