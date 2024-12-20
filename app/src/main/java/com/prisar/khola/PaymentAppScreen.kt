package com.prisar.khola

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PaymentAppScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Header()
        ServicesSection()
        PromotionalBanner()
        RecentTransactions()
        BottomNavigation()
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "Jenny Wilson",
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "+880 123456789",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
        }

        Row {
            Text(
                text = "EN",
                modifier = Modifier
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "BN",
                modifier = Modifier
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
//                imageVector = Icons.Default.AccountBalance,
                painter = painterResource(id = R.drawable.account_balance),
                contentDescription = "Balance"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Tap for Balance")
        }
    }
}

@Composable
fun ServicesSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Services",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = "See more",
                color = Color.Blue,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ServiceItem(
                icon = Icons.Default.Phone,
                text = "Mobile Recharge"
            )
//            ServiceItem(
//                icon = Icons.Default.Payment,
//                text = "Pay Bill"
//            )
//            ServiceItem(
//                icon = Icons.Default.CreditCard,
//                text = "Cross Platform"
//            )
        }

        Spacer(modifier = Modifier.height(16.dp))

//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            ServiceItem(
//                icon = Icons.Default.School,
//                text = "Education Fee"
//            )
//            ServiceItem(
//                icon = Icons.Default.AccountBalance,
//                text = "Govt. Fee"
//            )
//            ServiceItem(
//                icon = Icons.Default.Train,
//                text = "MRT Pass"
//            )
//        }
    }
}

@Composable
fun ServiceItem(
    icon: ImageVector,
    text: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = Color.Blue,
            modifier = Modifier
                .size(48.dp)
                .background(
                    color = Color.Blue.copy(alpha = 0.1f),
                    shape = CircleShape
                )
                .padding(12.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = text,
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun PromotionalBanner() {
    var currentPage by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        LazyRow {
            items(4) { index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .padding(end = if (index < 3) 16.dp else 0.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.promo_banner),
                        contentDescription = "Promotional Banner",
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(4) { index ->
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(
                            if (currentPage == index) Color.Blue
                            else Color.Gray.copy(alpha = 0.5f)
                        )
                        .padding(end = 8.dp)
                )
            }
        }
    }
}

@Composable
fun RecentTransactions() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Recent",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = "See all",
                color = Color.Blue,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

//        TransactionItem(
//            icon = Icons.Default.School,
//            title = "Education Fee",
//            transactionId = "Trx ID: 123DFUHNNK",
//            amount = "৳ 120.00",
//            time = "12:33 AM",
//            date = "12/10/2024",
//            isCredit = false
//        )

        TransactionItem(
            icon = Icons.Default.Person,
            title = "Cash In",
            subtitle = "Agent",
            transactionId = "Trx ID: 123DFUHNNK",
            amount = "৳ 1200.00",
            time = "12:53 AM",
            date = "11/10/2024",
            isCredit = true
        )
    }
}

@Composable
fun TransactionItem(
    icon: ImageVector,
    title: String,
    subtitle: String? = null,
    transactionId: String,
    amount: String,
    time: String,
    date: String,
    isCredit: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = Color.Gray.copy(alpha = 0.1f),
                        shape = CircleShape
                    )
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = title,
                    fontWeight = FontWeight.Medium
                )
                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }
                Text(
                    text = transactionId,
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = amount,
                color = if (isCredit) Color.Green else Color.Red,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "$time\n$date",
                color = Color.Gray,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun BottomNavigation() {
    NavigationBar {
        NavigationBarItem(
            selected = true,
            onClick = { },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home"
                )
            },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            },
            label = { Text("Search") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = {
                Icon(
//                    imageVector = Icons.Default.QrCode,
                    painter = painterResource(id = R.drawable.qr_code),
                    contentDescription = "Scan"
                )
            },
            label = { Text("Scan") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "Transaction"
                )
            },
            label = { Text("Transaction") }
        )
    }
}