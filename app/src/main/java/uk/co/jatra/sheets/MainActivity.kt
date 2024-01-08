package uk.co.jatra.sheets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import uk.co.jatra.sheets.ui.theme.SheetsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val sheetVisible = remember { mutableStateOf(false) }

            SheetsTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    mainContent(sheetVisible, name = "BottomSheet play")
                    bottomSheet(
                        sheetVisible = sheetVisible,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                    )
                }
            }
        }
    }


    @Composable
    private fun bottomSheet(sheetVisible: MutableState<Boolean>, modifier: Modifier) {
        AnimatedVisibility(
            visible = sheetVisible.value,
            enter = slideInVertically(
                animationSpec = tween(500),
                initialOffsetY = { it },
            ),
            exit = slideOutVertically(animationSpec = tween(500),
                targetOffsetY = { it }
            ),
            modifier = modifier,
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .offset(y = ((-300).dp).coerceAtLeast(0.dp)) // Anchor to bottom

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                    modifier = modifier
                        .fillMaxSize()
                        .background(color = Color.Red)
                ) {
                    Text(text = "Hello, I'm a bottom sheet")
                    Button(onClick = {
                        sheetVisible.value = !sheetVisible.value
                    }) {
                        Text("Hide")
                    }
                }
            }
        }
    }

    @Composable
    private fun mainContent(sheetVisible: MutableState<Boolean>,
                            name: String,
                            modifier: Modifier = Modifier) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.secondary
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Hello $name!",
                    modifier = modifier
                )
                Button(onClick = {
                    sheetVisible.value = !sheetVisible.value
                }) {
                    Text(if (sheetVisible.value) "Hide" else "Show")
                }
            }
        }
    }
}