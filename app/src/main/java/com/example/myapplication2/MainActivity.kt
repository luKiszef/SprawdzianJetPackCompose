package com.example.myapplication2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Commit
import androidx.compose.material.icons.filled.MergeType
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.vector.ImageVector

data class ActivityData(val icon: ImageVector, val activityName: String, val activityCount: Int)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitStatisticsScreen(
                userName = "Łukasz Lewański",
                activityList = listOf(
                    ActivityData(Icons.Filled.Commit, "Committed changes", 22),
                    ActivityData(Icons.Filled.Comment, "Comment count", 15),
                    ActivityData(Icons.Filled.MergeType, "Merged pull requests", 8),
                    ActivityData(Icons.Filled.Close, "Closed pull requests", 3)
                )
            ) {
                Toast.makeText(this, "Well done!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun GitStatisticsScreen(
    userName: String,
    activityList: List<ActivityData>,
    onButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Comment,
                contentDescription = "User Icon",
                modifier = Modifier.size(64.dp),
                tint = Color(0xFF6A1B9A)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = userName, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text(text = "Git statistics", color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Divider(thickness = 1.dp, color = Color.Gray)
        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Recent Activities", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(items = activityList) { activity ->
                ActivityRow(activity = activity)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Divider(thickness = 1.dp, color = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onButtonClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A1B9A))
        ) {
            Text(text = "Display message", color = Color.White)
        }
    }
}

@Composable
fun ActivityRow(activity: ActivityData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Gray, shape = RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = activity.icon,
                contentDescription = activity.activityName,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = activity.activityName,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = activity.activityCount.toString(),
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGitStatisticsScreen() {
    GitStatisticsScreen(
        userName = "Łukasz Lewański",
        activityList = listOf(
            ActivityData(Icons.Filled.Commit, "Committed changes", 22),
            ActivityData(Icons.Filled.Comment, "Comment count", 15),
            ActivityData(Icons.Filled.MergeType, "Merged pull requests", 8),
            ActivityData(Icons.Filled.Close, "Closed pull requests", 3)
        )
    ) {}
}
