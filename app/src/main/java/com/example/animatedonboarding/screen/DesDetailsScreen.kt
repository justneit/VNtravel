package com.example.animatedonboarding.screen


import android.app.DatePickerDialog
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.animatedonboarding.R
import com.example.animatedonboarding.model.DataViewModel
import com.example.animatedonboarding.model.LoginViewModel
import com.example.animatedonboarding.model.addToWishlist
import com.example.animatedonboarding.model.removeFromWishlist
import com.google.firebase.firestore.FirebaseFirestore
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.text.SimpleDateFormat

import java.util.Calendar
import java.util.Locale

@Composable
fun DetailScreen(viewModel: DataViewModel, nameID: String, navController: NavController, loginViewModel: LoginViewModel) {
    val desItem = viewModel.state.value.find { it.name == nameID }
    val desTitle = desItem?.name
    var numberGR = remember { mutableStateOf(1) }
    val SelectedDate = remember { mutableStateOf<String>("") }

    Scaffold(
        bottomBar = {
//            navController, number, desImg, desTitle
            if (desTitle != null) {
                Books(navController, desTitle, numberGR, SelectedDate, loginViewModel, viewModel)
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            if (desItem != null) {
                Details(viewModel = viewModel, nameID = nameID, numberGR, SelectedDate, navController)
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}



@Composable
fun Details(
    viewModel: DataViewModel,
    nameID: String,
    numberGR: MutableState<Int>,
    SelectedDate: MutableState<String>,
    navController: NavController
) {
    val desItem = viewModel.state.value.find { it.name == nameID }

    if (desItem != null) {
        Card(
            modifier = Modifier
                .fillMaxSize(),
            shape = RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    painter = rememberImagePainter(desItem.desBanner),
                    contentDescription = "",
                    modifier = Modifier
                        .clip(RectangleShape)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )

                Row(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(15.dp))
                            .background(Color.White)
                            .clickable {
                                navController.popBackStack()
                            }
                            .padding(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = "",
                            tint = Color(0xFFF2B705)
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxSize()
                        .padding(top = 200.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(
                                topStart = 35.dp,
                                topEnd = 35.dp,
                                bottomEnd = 0.dp,
                                bottomStart = 0.dp
                            )
                        )
                ) {
                    LazyColumn(
                        modifier = Modifier.padding(15.dp)
                    ) {
                        item {
                            Row(
                                modifier = Modifier
                                    .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = desItem.name,
                                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                    fontSize = 26.sp,
                                    modifier = Modifier.weight(1f)
                                )

                                Text(
                                    text = "${desItem.cost}$",
                                    fontWeight = FontWeight.ExtraBold,
                                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                    fontSize = 25.sp,
                                    color = Color(0xFFF2B705)
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(7.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.StarRate,
                                    contentDescription = "",
                                    tint = Color(0xFFF2B705)
                                )

                                Text(
                                    text = "${desItem.rate}",
                                    fontSize = 20.sp,
                                )

                                Spacer(modifier = Modifier.width(10.dp))

                                Text(text = "(5 reviews)", fontSize = 17.sp)
                            }

                            Spacer(modifier = Modifier.height(15.dp))

                            DatePickerRow(numberGR, SelectedDate)

                            Spacer(modifier = Modifier.height(15.dp))

                            Schedule(viewModel = viewModel, nameID = nameID)

                            Spacer(modifier = Modifier.height(15.dp))

                            Description(viewModel = viewModel, nameID = nameID)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun DatePickerRow(numberGR: MutableState<Int>, SelectedDate: MutableState<String>) {
    val context = LocalContext.current
    val today = Calendar.getInstance()
    val today1 = Calendar.getInstance()
    val today2 = Calendar.getInstance()
    val dateFormatter = SimpleDateFormat("dd/MM", Locale.getDefault())
    val dates = remember {
        listOf(
            dateFormatter.format(today.apply { add(Calendar.DAY_OF_MONTH, 1) }.time),
            dateFormatter.format(today1.apply { add(Calendar.DAY_OF_MONTH, 2) }.time),
            dateFormatter.format(today2.apply { add(Calendar.DAY_OF_MONTH, 3) }.time),
            )
    }
    Column(
        modifier = Modifier
            .padding(start = 10.dp)
            .fillMaxWidth(),
    ) {
        Text(
            text = "Service packages",
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            dates.forEach { date ->
                DateBox(date = date, isSelected = SelectedDate.value == date
                ) { SelectedDate.value = date }
            }
            SelectedDate.value?.let { date ->
                DateBox(date = date, isSelected = true) { SelectedDate.value = date }
            }
            Box(
                modifier = Modifier
                    .clickable {
                        val calendar = Calendar.getInstance()
                        val datePickerDialog = DatePickerDialog(
                            context,
                            { _, year, month, dayOfMonth ->
                                SelectedDate.value = "$dayOfMonth/${month + 1}"
                            },
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                        )
                        datePickerDialog.show()
                    }
                    .padding(8.dp)
                    .size(40.dp)
                    .background(Color.Gray, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Select Date",
                    tint = Color.White
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Group of: ",
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 15.sp,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            NumberPicker(
                minValue = 0,
                maxValue = 100,
                initialValue = numberGR.value,
                onValueChanged = { newValue ->
                    numberGR.value = newValue
                }
            )
        }
    }
}


@Composable
fun Schedule(viewModel: DataViewModel, nameID: String) {
    val desItem = viewModel.state.value.find { it.name == nameID }
    Column(
        modifier = Modifier
            .padding(start = 10.dp)
            .fillMaxWidth(),
    ) {
        Text(
            text = "Schedule",
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 0.dp)
        )
        if (desItem != null) {
            Text(
                text = desItem.schedule,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 15.sp,
            )
        }

        Text(text = "See detailed schedule",
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            fontSize = 15.sp,
            textDecoration = TextDecoration.Underline,
            color = Color(0xFFF2B705)
        )
    }
}

@Composable
fun Description(viewModel: DataViewModel, nameID: String) {
    val desItem = viewModel.state.value.find { it.name == nameID }
    Column(
        modifier = Modifier
            .padding(start = 10.dp)
            .fillMaxWidth(),
    ) {
        Text(
            text = "Description",
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 0.dp)
        )
        if (desItem != null) {
            Text(
                text = desItem.description,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 15.sp,
            )
        }
        if (desItem != null) {
            Image(painter =  rememberImagePainter(desItem.imgDesc1), contentDescription = "")
            Spacer(modifier = Modifier.height(10.dp))
            Image(painter =  rememberImagePainter(desItem.imgDesc2), contentDescription = "")
            Spacer(modifier = Modifier.height(10.dp))
            Image(painter =  rememberImagePainter(desItem.imgDesc3), contentDescription = "")
        }

    }
}

@Composable
fun DateBox(date: String, isSelected: Boolean, onClick: () -> Unit,) {
    Box(modifier = Modifier
        .clickable { onClick() }
        .border(
            width = 1.dp,
            color = if (isSelected) Color.Transparent else Color(0xFFF2B705),
            shape = RoundedCornerShape(10.dp)
        )
        .background(if (isSelected) Color(0xFFF2B705) else Color.White)
        .height(30.dp)
        .width(60.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = date, fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.poppins_medium)))
    }
    Spacer(modifier = Modifier.width(20.dp))
}


@Composable
fun NumberPicker(
    minValue: Int = 0,
    maxValue: Int = Int.MAX_VALUE,
    initialValue: Int = 0,
    onValueChanged: (Int) -> Unit
) {
    var value by remember { mutableStateOf(initialValue) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        IconButton(onClick = {
            if (value > minValue) {
                value--
                onValueChanged(value)
            }
        }) {
            Icon(Icons.Default.Remove, contentDescription = "Decrease")
        }

        Text(
            text = value.toString(),
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        IconButton(onClick = {
            if (value < maxValue) {
                value++
                onValueChanged(value)
            }
        }) {
            Icon(Icons.Default.Add, contentDescription = "Increase")
        }
    }
}


@Composable
fun Books(
    navController: NavController,
    desTitle: String,
    numberGR: MutableState<Int>,
    SelectedDate: MutableState<String>,
    loginViewModel: LoginViewModel,
    viewModel: DataViewModel
) {
    val date: String = SelectedDate.value ?: ""
    val encodedDate = URLEncoder.encode(date, StandardCharsets.UTF_8.toString())
    val db = FirebaseFirestore.getInstance()
    val currentUserUid = loginViewModel.getCurrentUserUid()
    val desItem = viewModel.state.value.find { it.name == desTitle }
    var showDialog by remember { mutableStateOf(false) }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            text = { Text(
                text = "Please select a date",
                fontSize = 16.sp, fontFamily =
                FontFamily(Font(R.font.poppins_bold))
            ) },
            confirmButton = {
                Button(
                    onClick = { showDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF2B705))
                ) {
                    Text("OK")
                }
            }
        )
    }
    Row(
        modifier = Modifier
            .padding(start = 15.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        var isSelected by remember { mutableStateOf(false) }
        LaunchedEffect(currentUserUid, desItem) {
            if (currentUserUid != null && desItem != null) {
                desItem.id?.let {
                    checkInitialWishlistState(db, it, currentUserUid) { initialIsSelected ->
                        isSelected = initialIsSelected
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(20.dp))
                .clickable {
                    if (currentUserUid != null && desItem != null) {
                        desItem.id?.let {
                            checkAndToggleWishlist(db, it, currentUserUid, desTitle) { newIsSelected ->
                                isSelected = newIsSelected
                            }
                        }
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = "",
                tint = if (isSelected) Color(0xFFF2B705) else Color.Black,
            )
        }
        Button(
            onClick = {
                if (date.isNotEmpty()) {
                    navController.navigate("Checkouts/$desTitle/${numberGR.value}/$encodedDate")
                } else {
                    showDialog = true
                }
            },
            modifier = Modifier
                .padding(10.dp)
                .width(310.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF2B705))
        ) {
                Text(
                    text = "Check out",
                    fontSize = 15.sp,
                    color = Color.Black,
                    fontFamily = FontFamily(Font(R.font.poppins_medium))
                )
        }
    }
}
fun checkAndToggleWishlist(db: FirebaseFirestore, desItemId: String, userId: String, desTitle: String, onToggle: (Boolean) -> Unit) {
    db.collection("wishlist")
        .whereEqualTo("desID", desItemId)
        .whereEqualTo("IDuser", userId)
        .get()
        .addOnSuccessListener { querySnapshot ->
            if (querySnapshot.isEmpty) {
                // Nếu không tìm thấy, thêm vào wishlist
                addToWishlist(db, desItemId, userId, desTitle)
                onToggle(true)
            } else {
                // Nếu tìm thấy, xóa khỏi wishlist
                for (document in querySnapshot) {
                    db.collection("wishlist").document(document.id).delete()
                }
                onToggle(false)
            }
        }
        .addOnFailureListener { e ->
            Log.w("Firestore", "Error checking document", e)
        }
}
fun checkInitialWishlistState(
    db: FirebaseFirestore,
    desItemId: String,
    userId: String,
    onResult: (Boolean) -> Unit
) {
    db.collection("wishlist")
        .whereEqualTo("desID", desItemId)
        .whereEqualTo("IDuser", userId)
        .get()
        .addOnSuccessListener { querySnapshot ->
            onResult(!querySnapshot.isEmpty)
        }
        .addOnFailureListener { e ->
            Log.w("Firestore", "Error checking document", e)
        }
}













