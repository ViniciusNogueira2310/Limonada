package com.example.projectlemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectlemonade.ui.theme.ProjectLemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectLemonadeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeApp() {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }
    var requiredSqueezes by remember { mutableStateOf((2..4).random()) }

    val imageResource = when (currentStep) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        4 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_tree
    }

    val textResource = when (currentStep) {
        1 -> R.string.tap_tree
        2 -> R.string.keep_tapping
        3 -> R.string.tap_lemonade
        4 -> R.string.tap_glass
        else -> R.string.tap_tree
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = stringResource(id = textResource),
            modifier = Modifier
                .clickable {
                    when (currentStep) {
                        1 -> {
                            currentStep = 2
                            requiredSqueezes = (2..4).random()
                            squeezeCount = 0
                        }
                        2 -> {
                            squeezeCount++
                            if (squeezeCount >= requiredSqueezes) {
                                currentStep = 3
                            }
                        }
                        3 -> currentStep = 4
                        4 -> currentStep = 1
                    }
                }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(id = textResource),
            fontSize = 18.sp
        )
    }
}

@Preview
@Composable
fun PreviewLemonadeApp() {
    ProjectLemonadeTheme {
        LemonadeApp()
    }
}
