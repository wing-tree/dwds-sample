package com.wing.tree.dwds.smaple

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wing.tree.dwds.smaple.ui.theme.DwdsSampleTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DwdsSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var n by remember {
                        mutableStateOf(0)
                    }

                    LaunchedEffect(Unit) {
                        repeat(10) {
                            delay(500L)
                            n++
                        }

                        repeat(10) {
                            delay(600L)
                            n--
                        }
                    }

                    RollingText(n)
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DwdsSampleTheme {
        Greeting("Android")
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RollingText(number: Int) {

    var prevNumber by remember {
        mutableStateOf(number)
    }

    AnimatedContent(
        targetState = number,
        modifier = Modifier.size(40.dp),
        transitionSpec = {
            if (number < prevNumber) { // 감소시,
                slideInVertically {
                    -120
                } + fadeIn() with slideOutVertically {
                    120
                }+ fadeOut()
            } else { // 증가시.
                slideInVertically {
                    120
                } + fadeIn() with slideOutVertically {
                    -120
                } + fadeOut()
            }
        }
    ) {
        Text(
            text = it.toString(),
            modifier = Modifier.size(40.dp)
        )

        prevNumber = it
    }
}