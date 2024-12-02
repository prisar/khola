package com.prisar.khola

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TravelUI() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFEFF3F7)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            HeaderSection()

            Spacer(modifier = Modifier.height(16.dp))

            // Travel Details
            TravelDetails()

            Spacer(modifier = Modifier.height(16.dp))

            // Confirm Button
            ConfirmButton()
        }
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /* Handle back action */ }) {
            Icon(
                painter = painterResource(id = android.R.drawable.arrow_up_float),
                contentDescription = "Back",
                tint = Color.Gray
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "June 2022 ☀️",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            )
        }

        IconButton(onClick = { /* Handle add action */ }) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_input_add),
                contentDescription = "Add",
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun TravelDetails() {
    val destinations = listOf(
        Destination("Moraine Lake", "Tuesday 16", "One of the many wonderful turquoise lakes in Alberta.", android.R.drawable.ic_menu_gallery),
        Destination("Niagara Falls", "Wednesday 17", "Probably the most popular natural landmark in Canada that everyone...", android.R.drawable.ic_menu_gallery),
        Destination("Baffin Island", "Thursday 18", "Canada's largest island (5th in the world) is located in Nunavut.", android.R.drawable.ic_menu_gallery)
    )

    Column {
        destinations.forEachIndexed { index, destination ->
            TravelItem(destination)
            if (index < destinations.size - 1) {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun TravelItem(destination: Destination) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = destination.name,
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = destination.date,
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal, color = Color.Gray)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = destination.description,
                style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Light, color = Color.Gray)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Image(
            painter = painterResource(id = destination.imageRes),
            contentDescription = destination.name,
            modifier = Modifier
                .size(64.dp)
                .background(Color.Gray, CircleShape)
        )
    }
}

@Composable
fun ConfirmButton() {
    Button(
        onClick = { /* Handle confirm action */ },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF36C2CF)),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Confirm Journey",
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
        )
    }
}

data class Destination(
    val name: String,
    val date: String,
    val description: String,
    val imageRes: Int
)

@Preview(showBackground = true)
@Composable
fun TravelUIPreview() {
    TravelUI()
}
