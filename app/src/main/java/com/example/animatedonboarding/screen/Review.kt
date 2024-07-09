package com.example.animatedonboarding.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.NotificationsActive
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.animatedonboarding.R
import com.example.animatedonboarding.data.Review


@Composable
fun ReviewItem(review: Review) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = review.username, fontSize = 20.sp, modifier = Modifier.weight(1f))
            Text(text = review.date, fontSize = 12.sp, color = Color.Gray)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            for (i in 1..5) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = if (i <= review.rating) Color.Yellow else Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = review.comment)
        Row(){
            review.images.forEach {
                image ->
                Image(
                    painter = image,
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(8.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}


@Composable
fun ReviewList(reviews: List<Review>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(reviews) { review ->
            ReviewItem(review = review)
        }
    }
}
@Composable
fun ReviewScreen(reviews: List<Review>) {
    Card(
        modifier = Modifier
            .fillMaxSize(),
        shape = RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(painter = painterResource(R.drawable.banner_app),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .clip(
                        shape = RoundedCornerShape(
                            bottomStart = 50.dp,
                            bottomEnd = 50.dp
                        )
                    ),
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color.White)
                    .clickable { }
                    .padding(6.dp)
                ) {
                    Icon(imageVector= Icons.Outlined.ArrowBack,
                        contentDescription = "",
                        tint = Color(0xFFF2B705)
                    )
                }
                Text(
                    text = "Review",
                    fontSize = 26.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(start = 88.dp)
                )

            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxSize()
                    .padding(top = 130.dp, end = 20.dp, start = 20.dp)
                    .background(
                        color = Color.White,
                    )
            ) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)) {
                    ReviewList(reviews = reviews)
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        val reviews = listOf(
            Review(username = "Valverde",
                date = "2023-05-01",
                rating = 5,
                comment = "the customer service is great, friendly, and professionally.\n" +
                        "I have my parents, they were in need a\n" +
                        "wheelchair, and the staff helped me..... so\n" +
                        "appreciate.....",
                images = listOf(
                    painterResource(R.drawable.rv1),
                    painterResource(R.drawable.rv2),
                    painterResource(R.drawable.rv3),
                )
                ),
            Review(username = "Kroos",
                date = "2023-05-01",
                rating = 4,
                comment = "the room service was awesome, had accidentally spill water, and they help us change the bed sheet rightaway. I LOVE THE food deliver service",
                images = listOf(
                    painterResource(R.drawable.rv4),
                )
                ),
            Review(username = "InfiniTour User",
                date = "2023-05-01",
                rating = 5,
                comment = "Very Good",
                images = listOf()
                ),
        )
        ReviewScreen(reviews)
    }
}

