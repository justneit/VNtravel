package com.example.animatedonboarding.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Web
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.animatedonboarding.R
import com.example.animatedonboarding.data.AccountData
import com.example.animatedonboarding.model.LoginViewModel


@Composable
fun AccountScreen(navController: NavHostController, loginViewModel: LoginViewModel = viewModel()) {
    val userData by loginViewModel.userData.observeAsState()
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
            userData?.let {
                ContentAccount(it, viewModel(), navController)
            } ?: run {
                Text(text = "Loading user data...")
            }
        }
    }
}
@Composable
fun ContentAccount(
    accountData: AccountData,
    loginViewModel: LoginViewModel = viewModel(),
    navController: NavHostController
) {
     Column(
         modifier = Modifier
             .padding(15.dp)
             .fillMaxSize()
     ) {
         Row(
             verticalAlignment = Alignment.CenterVertically
         ) {
             Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
             Spacer(modifier = Modifier.width(10.dp))
             Text(
                 text = "Back",
                 fontFamily = FontFamily(Font(R.font.poppins_medium)),
             )
         }
         Column(
             modifier = Modifier
                 .padding(15.dp),
             horizontalAlignment = Alignment.CenterHorizontally
         ) {
             Box(
                 modifier = Modifier
                     .size(150.dp)
                     .clip(CircleShape)

             ) {
                 Image(painter = rememberImagePainter(accountData.avt),
                     contentDescription = "",
                     modifier = Modifier.fillMaxSize(),
                     contentScale = ContentScale.Crop
                 )
             }
             Spacer(modifier = Modifier.height(20.dp))
             Text(
                 text = accountData.username ,
                 fontFamily = FontFamily(Font(R.font.poppins_bold)),
                 fontSize = 25.sp
             )
             Spacer(modifier = Modifier.height(10.dp))
             Text(
                 text = accountData.email,
                 fontFamily = FontFamily(Font(R.font.poppins_medium)),
                 fontSize = 17.sp
             )
             Spacer(modifier = Modifier.height(20.dp))
             Button(
                 onClick = {  },
                 modifier = Modifier.fillMaxWidth(),
                 colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF2B705))
                 ) {
                 Text(
                     text = "Edit",
                     color = Color.Black,
                     fontFamily = FontFamily(Font(R.font.poppins_medium)),
                     fontSize = 15.sp
                 )
             }
             Spacer(modifier = Modifier.height(20.dp))
             Divider(
                 color = Color.Black,
                 thickness = 1.dp,
                 modifier = Modifier
                     .fillMaxWidth()
                     .padding(vertical = 8.dp)
             )
             Spacer(modifier = Modifier.height(20.dp))

         }
         Box(modifier = Modifier) {
             Row(
                 verticalAlignment = Alignment.CenterVertically
             ) {
                 Icon(imageVector = Icons.Outlined.AccountCircle, contentDescription = "")
                 Spacer(modifier = Modifier.width(10.dp))
                 Text(
                     text = "Personal Information",
                     fontFamily = FontFamily(Font(R.font.poppins_medium)),
                     fontSize = 20.sp
                 )
             }
         }
         Spacer(modifier = Modifier.height(40.dp))
         Box(modifier = Modifier) {
             Row(
                 verticalAlignment = Alignment.CenterVertically
             ) {
                 Icon(imageVector = Icons.Outlined.Notifications, contentDescription = "")
                 Spacer(modifier = Modifier.width(10.dp))
                 Text(
                     text = "Notifications",
                     fontFamily = FontFamily(Font(R.font.poppins_medium)),
                     fontSize = 20.sp
                 )
             }
         }
         Spacer(modifier = Modifier.height(40.dp))
         Box(modifier = Modifier) {
             Row(
                 verticalAlignment = Alignment.CenterVertically
             ) {
                 Icon(imageVector = Icons.Outlined.Settings, contentDescription = "")
                 Spacer(modifier = Modifier.width(10.dp))
                 Text(
                     text = "Setting",
                     fontFamily = FontFamily(Font(R.font.poppins_medium)),
                     fontSize = 20.sp
                 )
             }
         }
         Spacer(modifier = Modifier.height(40.dp))
         Box(modifier = Modifier) {
             Row(
                 verticalAlignment = Alignment.CenterVertically
             ) {
                 Icon(imageVector = Icons.Outlined.ChatBubbleOutline, contentDescription = "")
                 Spacer(modifier = Modifier.width(10.dp))
                 Text(
                     text = "Support",
                     fontFamily = FontFamily(Font(R.font.poppins_medium)),
                     fontSize = 20.sp
                 )
             }
         }
         Spacer(modifier = Modifier.height(40.dp))
         Box(modifier = Modifier) {
             Row(
                 verticalAlignment = Alignment.CenterVertically
             ) {
                 Icon(
                     imageVector = Icons.Outlined.Logout,
                     contentDescription = "",
                     modifier = Modifier.clickable{
                         loginViewModel.signOut()
                         navController.navigate("Login")
                     }
                 )
                 Spacer(modifier = Modifier.width(10.dp))
                 Text(
                     text = "Logout",
                     fontFamily = FontFamily(Font(R.font.poppins_medium)),
                     fontSize = 20.sp
                 )
             }
         }
     }
}

