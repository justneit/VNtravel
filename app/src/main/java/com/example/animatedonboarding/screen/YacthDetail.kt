package com.example.animatedonboarding.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Bathtub
import androidx.compose.material.icons.filled.Bed
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.GolfCourse
import androidx.compose.material.icons.filled.LocalBar
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.SetMeal
import androidx.compose.material.icons.filled.SpaceBar
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.example.animatedonboarding.R
import com.example.animatedonboarding.model.YatchViewModel


@Composable
fun YacthDetailScreen(viewModel: YatchViewModel, imageYatchId: Int) {
    val yatchItem = viewModel.yatch.find { it.imageYatchId == imageYatchId}
    Scaffold(
        bottomBar = {
            Book()
        }
    ) {
            padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            SlideYatchs(modifier = Modifier,
                viewModel = viewModel,
                imageYatchId = imageYatchId)
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (yatchItem != null) {
                    Text(
                        text = yatchItem.yatchName,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        fontSize = 24.sp,
                        modifier = Modifier.weight(1f)
                    )
                }
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
                if (yatchItem != null) {
                    Text(
                        text = "${yatchItem.rate}",
                        fontSize = 20.sp,
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "(5 reviews)", fontSize = 17.sp)
            }
            OutsFeat()
            Introduce(viewModel = viewModel,
                imageYatchId = imageYatchId)
        }
    }

}

@Composable
fun OutsFeat() {
    Column(
        modifier = Modifier.padding(15.dp)
    ) {
        Text(text = "Outstanding features",
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontSize = 20.sp,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(imageVector = Icons.Filled.SetMeal, contentDescription = "")
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "All meals included",
                    fontFamily = FontFamily(Font(R.font.poppins_medium))
                )
            }
            Spacer(modifier = Modifier.width(50.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.GolfCourse, contentDescription = "")
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Mini Gotf",
                    fontFamily = FontFamily(Font(R.font.poppins_medium))
                )
            }
        }
        Spacer(modifier = Modifier.height(13.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.Bathtub, contentDescription = "")
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Has a bathtub",
                    fontFamily = FontFamily(Font(R.font.poppins_medium))
                )
            }
            Spacer(modifier = Modifier.width(80.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.Restaurant, contentDescription = "")
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Restaurant",
                    fontFamily = FontFamily(Font(R.font.poppins_medium))
                )
            }
        }
        Row(
            modifier = Modifier.padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.Verified, contentDescription = "", tint = Color(0xFFF2B705))
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "The newest luxury cruise in Ha Long Bay",
                fontFamily = FontFamily(Font(R.font.poppins_medium))
            )
        }
        Row(
            modifier = Modifier.padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.Verified, contentDescription = "", tint = Color(0xFFF2B705))
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "The cruise is designed in a modern style",
                fontFamily = FontFamily(Font(R.font.poppins_medium))
            )
        }
        Row(
            modifier = Modifier.padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.Verified, contentDescription = "", tint = Color(0xFFF2B705))
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "The cruise schedule will take visitors to explore the romantic Lan Ha Bay",
                fontFamily = FontFamily(Font(R.font.poppins_medium))
            )
        }
    }
}




@Composable
fun Introduce(viewModel: YatchViewModel, imageYatchId: Int) {
    val yatchItem = viewModel.yatch.find { it.imageYatchId == imageYatchId}
    Column(
        modifier = Modifier.padding(start = 15.dp)
    ) {
        Text(text = "Introduce",
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontSize = 20.sp,
        )
        if (yatchItem != null) {
            Text(text = stringResource(yatchItem.introduce),
                fontFamily = FontFamily(Font(R.font.poppins_medium))
            )
        }
    }
}




@Composable
fun Book() {
    Row(modifier = Modifier
        .padding(start = 16.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        var isSelected by remember { mutableStateOf(false) }
        Box(
            modifier = Modifier
                .width(100.dp)
                .clip(RoundedCornerShape(20.dp))
                .clickable { isSelected = !isSelected },
            contentAlignment = Alignment.Center
        ) {
            Column {
                Text(text = "Starting Price", fontFamily = FontFamily(Font(R.font.poppins_medium)))
                Text(
                    text = "109$",
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    fontSize = 20.sp,
                    color = Color(0xFFF2B705)
                )
            }
        }
        Button(
            onClick = {},
            modifier = Modifier
                .padding(start = 60.dp, top = 10.dp)
                .width(200.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF2B705))
        ) {
            Text(text = "See the rooms",
                fontSize = 15.sp,
                color = Color.Black,
                fontFamily = FontFamily(Font(R.font.poppins_medium))
            )
        }
    }
}
@OptIn(ExperimentalPagerApi::class)
@Composable
fun SlideYatchs(modifier: Modifier = Modifier, viewModel: YatchViewModel, imageYatchId: Int) {
    val yatchItem = viewModel.yatch.find { it.imageYatchId == imageYatchId}
    val images = yatchItem?.let {
        listOf(
            it.imageYatchId1,
            it.imageYatchId2,
            it.imageYatchId3,


        )
    }
    val pagerState = images?.let {
        rememberPagerState(
        pageCount =
        it.size
    )
    }
    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            val nextPage = ((pagerState?.currentPage ?: R.drawable.eots1) + 1) % (pagerState?.pageCount
                ?: R.drawable.eots1)
            if (pagerState != null) {
                pagerState.scrollToPage(nextPage)
            }
        }
    }
    val scope = rememberCoroutineScope()

    Column(
        modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = modifier.wrapContentSize()) {
            if (pagerState != null) {
                HorizontalPager(
                    state = pagerState,
                    modifier
                        .wrapContentSize()

                ) { currentPage ->
                    Card(
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(8.dp),
                    ) {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(id = images?.get(currentPage) ?: R.drawable.eots1 ),
                            contentDescription = "",
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
            IconButton(
                onClick = {
                    val nextPage = (pagerState?.currentPage ?: R.drawable.eots1) + 1
                    if (images != null) {
                        if (nextPage < images.size) {
                            scope.launch {
                                if (pagerState != null) {
                                    pagerState.scrollToPage(nextPage)
                                }
                            }
                        }
                    }
                },
                modifier
                    .padding(30.dp)
                    .size(30.dp)
                    .align(Alignment.CenterEnd)
                    .clip(CircleShape),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color(0x52373737)
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowForward, contentDescription = "",
                    modifier.fillMaxSize(),
                    tint = Color.LightGray
                )
            }
            IconButton(
                onClick = {
                    val prevPage = (pagerState?.currentPage ?: R.drawable.eots1) -1
                    if (prevPage >= 0) {
                        scope.launch {
                            if (pagerState != null) {
                                pagerState.scrollToPage(prevPage)
                            }
                        }
                    }
                },
                modifier
                    .padding(30.dp)
                    .size(30.dp)
                    .align(Alignment.CenterStart)
                    .clip(CircleShape),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color(0x52373737)
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack, contentDescription = "",
                    modifier.fillMaxSize(),
                    tint = Color.LightGray
                )
            }
        }

    }
}