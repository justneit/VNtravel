package com.example.animatedonboarding.data

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigation(
    val title: String,
    val icon: ImageVector
)
data class Cards(
    val desName: String,
    val desLocation: String,
    val desImg: Painter
)
data class Placee(
    val name: String,
    val location: String,
    val imageResId: Painter,
    val cost: Int
)
