package com.example.animatedonboarding.screen
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animatedonboarding.data.DayInfo


@Preview(showSystemUi = true)
@Composable
fun DaySelector() {
    val dayInfoList = listOf(
        DayInfo(1, "Thị trấn Hoàng Hôn SunsetTown \n Mediterranean Town"),
        DayInfo(2, "Thông tin cho ngày 2"),
        DayInfo(3, "Thông tin cho ngày 3")
    )
    var selectedDay by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (dayInfo in dayInfoList) {
            val (day, info) = dayInfo
            Button(
                onClick = {
                    selectedDay = if (selectedDay == day) null else day
                },
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text("Ngày $day")
            }
            if (selectedDay == day) {
                Text(info, modifier = Modifier.padding(bottom = 8.dp))
            }
        }
    }
}






