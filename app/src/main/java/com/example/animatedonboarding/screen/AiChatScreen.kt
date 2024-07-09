package com.example.animatedonboarding.screen

import androidx.core.graphics.drawable.toBitmap
import kotlinx.coroutines.flow.update
import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.rounded.AddPhotoAlternate
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.animatedonboarding.ChatUiEvent
import com.example.animatedonboarding.ChatViewModel
import com.example.animatedonboarding.R
import com.example.animatedonboarding.ui.theme.AnimatedOnboardingTheme
import kotlinx.coroutines.flow.MutableStateFlow

private val uriState = MutableStateFlow("")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent(navController: NavController) {
    AnimatedOnboardingTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                topBar = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.Black)
                            .height(80.dp)
                            .padding(horizontal = 16.dp),
                    ) {
                        Box(modifier = Modifier
                            .clip(RoundedCornerShape(15.dp))
                            .padding(top = 20.dp)
                            .background(Color.Gray.copy(alpha = 0f))
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
                            modifier = Modifier.align(Alignment.Center),
                            text = " InfiniTour\nAI Chat Bot",
                            fontSize = 19.sp,
                            color = Color(0xFFF2B705),
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        )
                    }
                }
            ) {
                ChatScreen(paddingValues = it)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(paddingValues: PaddingValues) {
    val chatViewModel = viewModel<ChatViewModel>()
    val chatState = chatViewModel.chatState.collectAsState().value

    val bitmap = getBitmap()

   Box(
       modifier = Modifier
           .fillMaxSize()
   ) {
       Image(
           painter = painterResource(id = R.drawable.chatbot_backgr),
           contentDescription = null,
           contentScale = ContentScale.Crop,
           modifier = Modifier
               .fillMaxSize()
       )
       Column(
           modifier = Modifier
               .fillMaxSize()
               .padding(top = paddingValues.calculateTopPadding()),
           verticalArrangement = Arrangement.Bottom
       ) {
           LazyColumn(
               modifier = Modifier
                   .weight(1f)
                   .fillMaxWidth()
                   .padding(horizontal = 8.dp),
               reverseLayout = true
           ) {
               itemsIndexed(chatState.chatList) { _, chat ->
                   if (chat.isFromUser) {
                       UserChatItem(prompt = chat.prompt, bitmap = chat.bitmap)
                   } else {
                       ModelChatItem(response = chat.prompt)
                   }
               }
           }

           Row(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(bottom = 16.dp, start = 4.dp, end = 4.dp),
               verticalAlignment = Alignment.CenterVertically
           ) {

               Column {
                   bitmap?.let {
                       Image(
                           modifier = Modifier
                               .size(40.dp)
                               .padding(bottom = 2.dp)
                               .clip(RoundedCornerShape(6.dp)),
                           contentDescription = "picked image",
                           contentScale = ContentScale.Crop,
                           bitmap = it.asImageBitmap()
                       )
                   }
               }

               Spacer(modifier = Modifier.width(8.dp))


                      OutlinedTextField(
                          modifier = Modifier.weight(1f),
                          value = chatState.prompt,
                          onValueChange = {
                              chatViewModel.onEvent(ChatUiEvent.UpdatePrompt(it))
                          },
                          placeholder = {
                              Text(text = "Type a prompt")
                          },
                          colors = TextFieldDefaults.outlinedTextFieldColors(
                              focusedBorderColor = Color(0xFFF2B705)
                          )
                      )

                      Spacer(modifier = Modifier.width(8.dp))

                      Icon(
                          modifier = Modifier
                              .size(40.dp)
                              .clickable {
                                  chatViewModel.onEvent(ChatUiEvent.SendPrompt(chatState.prompt, bitmap))
                                  uriState.update { "" }
                              },
                          imageVector = Icons.Rounded.Send,
                          contentDescription = "Send prompt",
                          tint = Color(0xFFF2B705)
                      )
           }
       }
   }
}

@Composable
fun UserChatItem(prompt: String, bitmap: Bitmap?) {
    Column(
        modifier = Modifier.padding(start = 100.dp, bottom = 16.dp)
    ) {
        bitmap?.let {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .padding(bottom = 2.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                bitmap = it.asImageBitmap()
            )
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(color = Color(0xFFF2B705))
                .padding(16.dp),
            text = prompt,
            fontSize = 17.sp,
            color = Black
        )
    }
}

@Composable
fun ModelChatItem(response: String) {
    Column(
        modifier = Modifier.padding(end = 100.dp, bottom = 16.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(color = Color(0xFFFAD976))
                .padding(16.dp),
            text = response,
            fontSize = 17.sp,
            color = Black
        )
    }
}

@Composable
private fun getBitmap(): Bitmap? {
    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            uri?.let {
                uriState.update { uri.toString() }
            }
        }
    )
    val uri = uriState.collectAsState().value

    val imageState: AsyncImagePainter.State = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(uri)
            .size(Size.ORIGINAL)
            .build()
    ).state

    if (imageState is AsyncImagePainter.State.Success) {
        return imageState.result.drawable.toBitmap()
    }

    return null
}


