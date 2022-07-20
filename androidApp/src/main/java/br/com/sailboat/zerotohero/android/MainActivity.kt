package br.com.sailboat.zerotohero.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.sailboat.zerotohero.Greeting
import br.com.sailboat.zerotohero.android.composable.ExerciseItem
import br.com.sailboat.zerotohero.android.theme.MyComposeAppTheme

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(
                topBar = {
                    TopAppBar {
                        Text(
                            text = "Exercises",
                            modifier = Modifier.padding(16.dp),
                        )
                    }
                }
            ) {
                ExerciseItem(
                    name = "Kettlebell Swings",
                    sets = 1,
                    repetitions = 60,
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyComposeAppTheme {
        ExerciseItem(
            name = "Kettlebell Swings",
            sets = 1,
            repetitions = 60,
        )
    }
}
