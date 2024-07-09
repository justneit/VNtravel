package com.example.animatedonboarding.screen

import android.content.Context
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.animatedonboarding.MainActivity
import com.example.animatedonboarding.R
import com.example.animatedonboarding.model.LoginViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController, context: MainActivity, loginViewModel: LoginViewModel = viewModel()) {
    val loginState by loginViewModel.loginState.observeAsState()
    val alpha = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        alpha.animateTo(1f, animationSpec = tween(2000))
        delay(2500)
        if (onBoardingFinished(context = context)) {
            loginViewModel.checkCurrentUser()
        } else {
            navController.popBackStack()
            navController.navigate("Onboarding")
        }
    }

    LaunchedEffect(key1 = loginState) {
        when (loginState) {
            true -> {
                navController.navigate("Home")
            }
            false -> {
                navController.popBackStack()
                navController.navigate("Login")
            }
            null -> TODO()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoaderAnimation(
            modifier = Modifier.size(400.dp).wrapContentHeight(),
            anim = R.raw.infinite2
        )
        Box(
            modifier = Modifier
                .offset(y = (-100).dp)
                .padding(start = 4.dp, top = 4.dp, end = 4.dp, bottom = 0.dp)
        ) {
            Text(
                text = "InfiniTour",
                modifier = Modifier
                    .alpha(alpha.value)
                    .padding(4.dp),
                fontSize = 52.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFF2B705))
        }
    }
}

@Composable
fun LoaderAnimation(modifier: Modifier, anim: Int) {
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(anim))
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            modifier = modifier
            )
}

private fun onBoardingFinished(context: MainActivity):Boolean{
    val sharedPreferences = context.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
    return sharedPreferences.getBoolean("isFinished", false)
}