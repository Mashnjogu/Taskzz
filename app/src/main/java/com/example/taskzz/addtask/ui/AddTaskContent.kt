package com.example.taskzz.addtask.ui

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.taskzz.addtask.domain.model.TaskInput
import com.example.taskzz.core.ui.components.PrimaryButton
import com.example.taskzz.core.ui.components.TaskzTextField
import com.example.taskzz.core.ui.components.UiText
import com.example.taskzz.ui.theme.TaskzzTheme
import java.time.LocalDate
import com.example.taskzz.R
import com.example.taskzz.core.ui.components.MaterialCircularProgressIndicator
import com.example.taskzz.core.ui.components.TaskzzDatePicker
import com.example.taskzz.core.ui.components.getString
import com.example.taskzz.destinations.Destination
import com.google.android.material.datepicker.MaterialDatePicker


//remember to change the fonts from title large to headline large
@Composable
fun AddTaskContent(
    viewState: AddTaskViewState,
    onTaskDescriptionChanged: (String) -> Unit,
    onTaskScheduledDateChanged: (LocalDate) -> Unit,
    onSubmitClicked: () -> Unit,
    modifier: Modifier = Modifier
){

        Box(modifier = modifier){
            AddTasksInputColumn(
                viewState = viewState,
                onTaskDescriptionChanged = onTaskDescriptionChanged,
                onTaskScheduledDateChanged = onTaskScheduledDateChanged,
                onSubmitClicked = onSubmitClicked,
                modifier = Modifier.fillMaxWidth()
            )



            if(viewState is AddTaskViewState.Submitting){

//                Text(
//                    modifier = Modifier.align(Alignment.Center),
//                    text = "Loading"
//                )

//                CircularProgressIndicator(
//                    modifier = Modifier
//                    .wrapContentSize()
//                    .align(Alignment.Center)
//                )
            MaterialCircularProgressIndicator(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
            )
            }

        }
}

@Composable
private fun AddTasksInputColumn(
    viewState: AddTaskViewState,
    onTaskDescriptionChanged: (String) -> Unit,
    onTaskScheduledDateChanged: (LocalDate) -> Unit,
    onSubmitClicked: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.form_spacing)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TaskDescriptionLabel()

        TaskDescriptionInput(
            viewState.taskInput.description,
            onTaskDescriptionChanged,
            enabled = viewState.inputEnabled
        )

        TaskDateLabel()

        TaskDateInput(
            value = viewState.taskInput.scheduledDate,
            onValueChanged = onTaskScheduledDateChanged ,
            enabled = viewState.inputEnabled
        )

        if (viewState is AddTaskViewState.SubmisssionError) {

            Text(
                text = viewState.errorMessage.getString(),
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 12.dp)
            )


        }

        Spacer(Modifier.height(dimensionResource(id = R.dimen.form_spacing)))
        SubmitButton(
            onSubmitClicked,
            enabled = viewState.inputEnabled
        )
    }
}

@Composable
private fun SubmitButton(
    onClick: () -> Unit,
    enabled: Boolean
) {
    PrimaryButton(
        text = "Submit",
        onClick = onClick,
        enabled = enabled
    )
}



@Composable
private fun TaskDateLabel() {
    Text(
        text = "When would you like to do it?",
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun TaskDateInput(
    value: LocalDate,
    onValueChanged: (LocalDate) -> Unit,
    enabled: Boolean
) {
    TaskzzDatePicker(
        value = value,
        onValueChanged = onValueChanged,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun TaskDescriptionInput(
    text: String,
    onTextChanged: (String) -> Unit,
    enabled: Boolean
) {
    TaskzTextField(
        text = text,
        onTextChanged = onTextChanged,
        labelText = "",
        enabled = enabled
    )
}

@Composable
private fun TaskDescriptionLabel() {
    Text(
        text = "What would you like to accomplish?",
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center
    )
}



@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun AddTaskContentPreview(
    @PreviewParameter(AddTaskViewStateProvider::class)
    addTaskViewState: AddTaskViewState
){
    TaskzzTheme {
        Surface {
            AddTaskContent(
                viewState = addTaskViewState,
                onTaskDescriptionChanged = {},
                onTaskScheduledDateChanged = {},
                onSubmitClicked = {},
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(id = R.dimen.form_spacing))
            )
        }

    }

}

class AddTaskViewStateProvider: PreviewParameterProvider<AddTaskViewState> {

    override val values: Sequence<AddTaskViewState>
        get() {
            val activeInput = TaskInput(
                description = "Buy an NFT",
                scheduledDate = LocalDate.now(),
            )

            return sequenceOf(
                AddTaskViewState.Initial,
                AddTaskViewState.Active(
                    taskInput = activeInput,
                ),
                AddTaskViewState.Submitting(
                    taskInput = activeInput,
                ),
                AddTaskViewState.SubmisssionError(
                    taskInput = activeInput,
                    errorMessage = UiText.StringText("Don't buy NFTs."),
                ),
                AddTaskViewState.Completed,
            )
        }
}


