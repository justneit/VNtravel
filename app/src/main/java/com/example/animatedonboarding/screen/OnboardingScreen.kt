package com.example.animatedonboarding.screen

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.animatedonboarding.MainActivity
import com.example.animatedonboarding.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState


@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(navController: NavHostController, context: MainActivity) {
    val animations = listOf(
        R.raw.intro2,
        R.raw.beach,
        R.raw.intro1
    )
    val titles = listOf(
        "EXPLORE THE WORLD",
        "RELAX AND RENEW",
        "IMMERSED IN NATURE"
    )
    val descriptions = listOf(
        "Explore life, discover passion.",
        "Restore yourself, enhance your life.",
        "Immerse your soul, immerse yourself in nature."
    )
    val pagerState = rememberPagerState(
        pageCount = animations.size
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.wrapContentSize()
        ) {
            currentPage->
            Column(
                Modifier
                    .wrapContentSize()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animations[currentPage]))
                LottieAnimation(composition = composition,
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier.size(400.dp)
                    )
                Text(
                    text = titles[currentPage],
                    Modifier.padding(top = 10.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 33.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFF2B705)
                )
                Text(
                    text = descriptions[currentPage],
                    Modifier.padding(40.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
        }
        PageIndicator(
            pageCount = animations.size,
            currentPage = pagerState.currentPage,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
    ButtonSection(
        pagerState = pagerState,
        navController = navController,
        context = context
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ButtonSection(pagerState: PagerState, navController: NavHostController, context: MainActivity) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 100.dp, start = 30.dp, end = 30.dp)) {
            if(pagerState.currentPage == 2) {
                OutlinedButton(onClick = {
                    onBoardingFinished(context = context)
                    navController.popBackStack()
                    navController.navigate("Login")
                }, modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                    colors = ButtonDefaults.buttonColors(
                        containerColor =  Color(0xFFF2B705)
                    )
                ) {
                     Text(
                         text = "Được rồi đi thôi",
                         fontSize = 20.sp,
                         color = Color.White
                         )
                }
            }
        }
}

@Composable
fun PageIndicator(pageCount: Int, currentPage: Int, modifier: Modifier) {
   Row(
       horizontalArrangement = Arrangement.Absolute.SpaceBetween,
       modifier = modifier
   ) {
       repeat(pageCount){
           Indicators(isSelected = it == currentPage)
       }
   }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun Indicators(isSelected: Boolean) {
    val width = animateDpAsState(targetValue = if (isSelected) 35.dp else 15.dp)
        Box(
            modifier = Modifier
                .padding(2.dp)
                .height(15.dp)
                .width(width.value)
                .clip(CircleShape)
                .background(if (isSelected) Color(0xFFF2B705) else Color(0xFFE7C86A))
        )
}
private fun onBoardingFinished(context: MainActivity){
    val sharedPreferences = context.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putBoolean("isFinished", true)
    editor.apply()
}
