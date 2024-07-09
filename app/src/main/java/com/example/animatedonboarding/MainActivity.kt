package com.example.animatedonboarding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.animatedonboarding.model.DataViewModel
import com.example.animatedonboarding.model.LoginViewModel
import com.example.animatedonboarding.model.SigninViewModel
import com.example.animatedonboarding.model.YatchViewModel
import com.example.animatedonboarding.screen.AccountScreen
import com.example.animatedonboarding.screen.AppContent
import com.example.animatedonboarding.screen.CheckoutScreen
import com.example.animatedonboarding.screen.DesScreen
import com.example.animatedonboarding.screen.DetailScreen
import com.example.animatedonboarding.screen.HomeScreen
import com.example.animatedonboarding.screen.LoginScreen
import com.example.animatedonboarding.screen.OnboardingScreen
import com.example.animatedonboarding.screen.PaymentScreen
import com.example.animatedonboarding.screen.SearchScreen
import com.example.animatedonboarding.screen.SigninScreen
import com.example.animatedonboarding.screen.SplashScreen
import com.example.animatedonboarding.screen.ThanksScreen
import com.example.animatedonboarding.screen.WishListSCreen
import com.example.animatedonboarding.screen.YacthDetailScreen
import com.example.animatedonboarding.screen.YatchScreens
import com.example.animatedonboarding.screen.YourTripScreen
import com.example.animatedonboarding.ui.theme.AnimatedOnboardingTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimatedOnboardingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val yatchviewModel: YatchViewModel = viewModel()
                    val desviewModel: DataViewModel = viewModel()
                    val signinViewModel: SigninViewModel = viewModel()
                    val loginViewModel: LoginViewModel = viewModel()
                    NavHost(navController = navController, startDestination = "Splash") {
                        composable("Splash") {
                            SplashScreen(navController = navController, context = this@MainActivity)
                        }
                        composable("Onboarding") {
                            OnboardingScreen(navController = navController, context = this@MainActivity)
                        }
                        composable("Login") {
                            LoginScreen(navController = navController, loginViewModel)
                        }
                        composable("Signin") {
                            SigninScreen(navController = navController, signinViewModel)
                        }
                        composable("Home") {
                            HomeScreen(navController = navController)
                        }
                        composable("profile") {
                            AccountScreen(navController = navController)
                        }
                        composable("yourtrip") {
                            YourTripScreen(loginViewModel, navController = navController)
                        }
                        composable("favourite") {
                            WishListSCreen(navController)
                        }

                        composable("AiChat") {
                            AppContent(navController = navController)
                        }
                        composable("Search/{searchText}") { backStackEntry ->
                            val searchText = backStackEntry.arguments?.getString("searchText") ?: ""
                            SearchScreen(navController, searchText)
                        }
                        composable(
                            "DesDetails/{index}",
                            arguments = listOf(navArgument(name = "index") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val index = backStackEntry.arguments?.getString("index") ?: ""
                            DetailScreen(desviewModel, index, navController = navController, loginViewModel)
                        }
                        composable(
                            "Checkouts/{desTitle}/{numberGR}/{date}",
                            arguments = listOf(
                                navArgument(name = "desTitle") { type = NavType.StringType },
                                navArgument("numberGR") { type = NavType.IntType },
                                navArgument("date") { type = NavType.StringType }
                            )
                        ) {backStackEntry ->
                            val desTitle = backStackEntry.arguments?.getString("desTitle") ?: ""
                            val numberGR = backStackEntry.arguments?.getInt("numberGR") ?: 0
                            val date = backStackEntry.arguments?.getString("date") ?: ""
                            CheckoutScreen(navController, desTitle, desviewModel, numberGR, date)
                        }
                        composable(
                            "Payment/{desTitle}/{encodedDate}/{numberGR}/{totalPrice}/{banner}",
                            arguments = listOf(
                            navArgument(name = "desTitle") { type = NavType.StringType },
                            navArgument("encodedDate") { type = NavType.StringType },
                            navArgument("numberGR") { type = NavType.IntType },
                            navArgument("totalPrice") { type = NavType.IntType },
                            navArgument("banner") { type = NavType.StringType },
                        ) ) {backStackEntry ->
                            val desTitle = backStackEntry.arguments?.getString("desTitle") ?: ""
                            val encodedDate = backStackEntry.arguments?.getString("encodedDate") ?: ""
                            val numberGR = backStackEntry.arguments?.getInt("numberGR") ?:0
                            val totalPrice = backStackEntry.arguments?.getInt("totalPrice") ?: 0
                            val banner = backStackEntry.arguments?.getString("banner") ?: ""
                            PaymentScreen(navController, desTitle, encodedDate, numberGR, totalPrice, banner, loginViewModel)
                        }
                        composable("PorpularDes") {
                            DesScreen(navController = navController)
                        }
                        composable("Yatch") {
                            YatchScreens(navController = navController, yatchviewModel)
                        }
                        composable(
                            route = "YatchDetail/{index}",
                            arguments = listOf(navArgument(name = "index") { type = NavType.IntType })
                        ) {backStackEntry ->
                            val index = backStackEntry.arguments?.getInt("index") ?: 0
                            YacthDetailScreen(yatchviewModel, index)
                        }
                        composable("Thanks") {
                            ThanksScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}


