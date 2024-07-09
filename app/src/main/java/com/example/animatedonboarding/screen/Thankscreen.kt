package com.example.animatedonboarding.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animatedonboarding.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun ThanksScreen(
    navController: NavController
) {
    Column(
   modifier = Modifier
       .padding(start = 15.dp, end = 15.dp, top = 120.dp)
       .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.thanku),
            contentDescription = "",
            modifier = Modifier
                .size(300.dp)
            )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "We will send information and payment" +
                " method to your Email as soon as possible",
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            color = Color.Black,
            fontFamily = FontFamily(Font(R.font.poppins_medium))
            )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                      navController.navigate("Home")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
                .width(310.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF2B705))
        ) {
            Text(text = "Go Home",
                fontSize = 15.sp,
                color = Color.Black,
                fontFamily = FontFamily(Font(R.font.poppins_medium))
            )
        }
    }
}

