package br.com.sailboat.zerotohero.android.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExerciseItem(name: String, sets: Int, repetitions: Int) {
    Column(
        modifier = Modifier.padding(
            horizontal = 16.dp,
            vertical = 8.dp,
        )
    ) {
        Text(text = name)
        Text(text = "$sets x $repetitions")
    }
}
