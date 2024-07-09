package com.example.animatedonboarding.screen

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.NotificationsActive
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.animatedonboarding.R
import com.example.animatedonboarding.model.DataViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun CheckoutScreen(navController: NavController, desTitle: String, viewModel: DataViewModel, numberGR: Int, date: String) {
    val desItem = viewModel.state.value.find { it.name == desTitle }
    val totalPrice = (desItem?.cost?.toInt() ?: 0) * numberGR

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Gray.copy(alpha = 0.05f))
    ) {
        Row(
            modifier = Modifier.padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "")
            Text(
                text = "Request to book",
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 15.sp,
            )
        }
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
                   if (desItem != null) {
                       Image(
                           painter = rememberImagePainter(desItem.desBanner),
                           contentDescription = "",
                           modifier = Modifier.clip(RoundedCornerShape(16.dp))
                       )
                   }
                   Spacer(modifier = Modifier.width(20.dp))
                   Column {
                       Text(
                           text = "Popular Destination",
                           fontFamily = FontFamily(Font(R.font.poppins_medium)),
                           fontSize = 12.sp,
                       )
                       Text(
                           text = "$desTitle",
                           fontFamily = FontFamily(Font(R.font.poppins_bold)),
                           fontSize = 17.sp,
                       )
                       Row(
                           verticalAlignment = Alignment.CenterVertically
                       ){
                           Icon(
                               imageVector = Icons.Default.LocationOn,
                               contentDescription = "",
                               tint = Color(0xFFF2B705)
                           )
                           if (desItem != null) {
                               Text(
                                   text = "${desItem.location}",
                                   fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                   fontSize = 12.sp,
                               )
                           }
                       }
                   }
               }
           }
       }
        Text(
            text = "Your Trip",
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontSize = 17.sp,
            modifier = Modifier.padding(15.dp)
        )
       Card(
           shape = RoundedCornerShape(16.dp),
           elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
           modifier = Modifier
               .fillMaxWidth()
               .height(100.dp)
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
                   modifier = Modifier.padding(top = 20.dp, start = 20.dp),
                   verticalAlignment = Alignment.CenterVertically,
               ) {
                   Row {
                       Icon(imageVector = Icons.Filled.CalendarMonth,
                           contentDescription = "",
                           modifier = Modifier.size(50.dp),
                           tint = Color(0xFFF2B705)
                       )
                   }
                   Spacer(modifier = Modifier.width(20.dp))
                   Column {
                       Text(
                           text = "Date",
                           fontFamily = FontFamily(Font(R.font.poppins_bold)),
                           fontSize = 17.sp,
                       )
                       Text(
                           text = "$date",
                           fontFamily = FontFamily(Font(R.font.poppins_medium)),
                           fontSize = 15.sp,
                       )
                   }
               }
           }
       }
        Spacer(modifier = Modifier.height(20.dp))
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
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
                    modifier = Modifier.padding(top = 20.dp, start = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row {
                        Icon(imageVector = Icons.Filled.Group,
                            contentDescription = "",
                            modifier = Modifier.size(50.dp),
                            tint = Color(0xFFF2B705)
                        )
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Column {
                        Text(
                            text = "Guests",
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            fontSize = 17.sp,
                        )
                        Text(
                            text = "$numberGR guests",
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontSize = 15.sp,
                        )
                    }
                }
            }
        }

        Text(
            text = "Price Details",
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontSize = 17.sp,
            modifier = Modifier.padding(15.dp)
        )
      Card(
          shape = RoundedCornerShape(16.dp),
          elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
          modifier = Modifier
              .fillMaxWidth()
              .height(150.dp)
              .padding(end = 10.dp, start = 10.dp)
      ) {
          Box(
              modifier = Modifier
                  .fillMaxWidth()
                  .background(
                      color = Color.White,
                  ),
          ) {
              Column(
                  modifier = Modifier.padding(15.dp)
              ) {
                  Row(
                      modifier = Modifier
                          .padding(start = 15.dp, end = 25.dp)
                          .fillMaxWidth(),
                      verticalAlignment = Alignment.CenterVertically
                  ) {
                      Text(
                          text = "Price",
                          modifier = Modifier.weight(1f),
                          fontFamily = FontFamily(Font(R.font.poppins_medium)),
                          fontSize = 17.sp,
                      )
                      Text(
                          text = "$ $totalPrice",
                          fontFamily = FontFamily(Font(R.font.poppins_medium)),
                          fontSize = 17.sp,
                      )
                  }
                  Spacer(modifier = Modifier.height(10.dp))
                  Row(
                      modifier = Modifier
                          .padding(start = 15.dp, end = 25.dp)
                          .fillMaxWidth(),
                      verticalAlignment = Alignment.CenterVertically
                  ){
                      Text(
                          text = "Service fee",
                          modifier = Modifier.weight(1f),
                          fontFamily = FontFamily(Font(R.font.poppins_medium)),
                          fontSize = 17.sp,
                      )
                      Text(
                          text = "$ 0" ,
                          fontFamily = FontFamily(Font(R.font.poppins_medium)),
                          fontSize = 17.sp,
                      )
                  }
                  Spacer(modifier = Modifier.height(10.dp))
                  Divider(
                      color = Color.Gray,
                      thickness = 1.dp,
                      modifier = Modifier.fillMaxWidth()
                  )
                  Spacer(modifier = Modifier.height(15.dp))
                  Row(
                      modifier = Modifier
                          .padding(start = 15.dp, end = 25.dp)
                          .fillMaxWidth(),
                      verticalAlignment = Alignment.CenterVertically
                  ){
                      Text(
                          text = "Total",
                          modifier = Modifier.weight(1f),
                          fontFamily = FontFamily(Font(R.font.poppins_bold)),
                          fontSize = 17.sp,
                      )
                      Text(
                          text = "$ $totalPrice" ,
                          fontFamily = FontFamily(Font(R.font.poppins_bold)),
                          fontSize = 17.sp,
                      )
                  }
              }
          }
      }
        Spacer(modifier = Modifier.height(25.dp))
        val encodedDate = URLEncoder.encode(date, StandardCharsets.UTF_8.toString())
        val encodeBanner = URLEncoder.encode(desItem?.desBanner ?: "" , StandardCharsets.UTF_8.toString())
        Button(
            onClick = {
                if (desItem != null) {
                    navController.navigate("Payment/$desTitle/$encodedDate/$numberGR/$totalPrice/$encodeBanner")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
                .width(310.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF2B705))
        ) {
            Text(text = "Payment",
                fontSize = 15.sp,
                color = Color.Black,
                fontFamily = FontFamily(Font(R.font.poppins_medium))
            )
        }
    }
}



