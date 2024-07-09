package com.example.animatedonboarding.data

import androidx.compose.ui.graphics.painter.Painter

data class AccountData (
    val avt: String = "",
    val email: String = "",
    val password: String = "",
    val uid: String = "",
    val username: String = "",
)
data class Session(
    val username: String
)


data class Destination(
    var id: String? = "",
    val name: String = "",
    val location: String = "",
    val imageResId: String = "",
    val desBanner: String = "",
    val schedule: String = "",
    val cost: String = "",
    val description: String = "",
    val imgDesc1: String = "",
    val imgDesc2: String = "",
    val imgDesc3: String = "",
    val rate: Float = 0.0f,
)

data class OrderData(
    val name: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val desTitle: String = "",
    val encodedDate: String = "",
    val numberGR: Int = 0,
    val totalPrice: Int = 0,
    val banner: String = "",
    val idUser: String = ""
)

data class Des(
    val name: String,
    val location: String,
    val imageResId: Int,
    val desBanner: Int,
    val schedule: String,
    val cost: Int,
    val description: Int,
    val imgDesc1: Int,
    val imgDesc2: Int,
    val imgDesc3: Int
)

data class Review(
    val username: String,
    val date: String,
    val rating: Int,
    val comment: String,
    val images: List<Painter>
)
data class DayInfo(
    val day: Int,
    val info: String
)
data class Yatch(
    val yatchLocation: String,
    val yatchName: String,
    val yatchInfo: String,
    val imageYatchId: Int,
    val imageYatchId1: Int,
    val imageYatchId2: Int,
    val imageYatchId3: Int,
    val rate: Float,
    val introduce: Int,
    val cost: Int
)
data class YatchRoom(
    val yatchRoomName: String,
    val cost: Int,
    val yatchRoomImg: Painter,
    val amountPeople: Int,
    val acreage: String
)

