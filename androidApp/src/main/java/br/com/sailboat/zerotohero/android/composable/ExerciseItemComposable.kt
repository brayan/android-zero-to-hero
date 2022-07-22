package br.com.sailboat.zerotohero.android.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.sailboat.zerotohero.android.uimodel.ExerciseUi

@Composable
fun ExerciseItemComposable(exerciseUi: ExerciseUi, onClickExercise: (id: Long) -> Unit) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClickExercise(exerciseUi.id) }
    ) {
        Text(text = exerciseUi.name)
        Text(text = "$exerciseUi.sets x $exerciseUi.repetitions")
    }
}
