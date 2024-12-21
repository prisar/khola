package com.prisar.khola

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prisar.khola.ui.theme.KholaTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ScannerAppScreen() {
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                listOf(
                    Triple("Home", Icons.Default.Home, 0),
                    Triple("Profile", Icons.Default.Person, 1),
                    Triple("Files", Icons.Default.MailOutline, 2),
                    Triple("Menu", Icons.Default.Menu, 3)
                ).forEachIndexed { index, (title, icon, _) ->
                    NavigationBarItem(
                        icon = { Icon(icon, contentDescription = title) },
                        label = { Text(title) },
                        selected = selectedTab == index,
                        onClick = { selectedTab = index }
                    )
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Top Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Scan",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                ) {
                    Icon(
//                        Icons.Default.CameraAlt,
                        Icons.Default.Add,
                        contentDescription = "Camera",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // Quick Actions Grid
            QuickActionsGrid()

            // Recent Files Section
            RecentFiles()
        }
    }
}

@Composable
fun QuickActionsGrid() {
    val actions = listOf(
        Triple("Scan Card", R.drawable.ic_credit_card, MaterialTheme.colorScheme.primary),
        Triple("Protect PDF", R.drawable.ic_lock, MaterialTheme.colorScheme.primary),
        Triple("SVG to PDF", R.drawable.ic_picture_to_pdf, MaterialTheme.colorScheme.primary),
        Triple("Merge PDF", R.drawable.ic_merge, MaterialTheme.colorScheme.primary),
        Triple("Water mark", R.drawable.ic_watermark, MaterialTheme.colorScheme.primary),
        Triple("All tools", R.drawable.ic_apps, MaterialTheme.colorScheme.primary)
    )

    Column(modifier = Modifier.padding(vertical = 16.dp)) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(actions) { (title, drawableId, color) ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(color.copy(alpha = 0.1f))
                        .padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = drawableId),
                        contentDescription = title,
                        modifier = Modifier.size(24.dp),
                        contentScale = ContentScale.Fit,
                        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(color)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Composable
fun RecentFiles() {
    Column {
        Text(
            text = "Recent files",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(getSampleRecentFiles()) { file ->
                RecentFileItem(file)
            }
        }
    }
}

@Composable
fun RecentFileItem(file: RecentFile) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.pdf_preview),
                contentDescription = "Preview",
                modifier = Modifier.size(42.dp),
                contentScale = ContentScale.Fit,
//                colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = file.title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = file.date,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
        }
        IconButton(onClick = { }) {
            Icon(
                Icons.Default.MoreVert,
                contentDescription = "More options",
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}

data class RecentFile(
    val title: String,
    val date: String
)

fun getSampleRecentFiles() = listOf(
    RecentFile("As a senior UX UI designer", "16 August 2023"),
    RecentFile("User Documents", "16 August 2023"),
    RecentFile("Office leave vacation", "20 August 2023")
)

@Preview(showBackground = true)
@Composable
fun QuickActionsGridPreview() {
    KholaTheme {
        ScannerAppScreen()
    }
}