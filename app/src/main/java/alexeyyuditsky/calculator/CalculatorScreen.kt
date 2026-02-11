package alexeyyuditsky.calculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorScreen(
    input: String,
    result: String,
    actions: CalculatorActions,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = input,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            fontSize = 34.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .testTag("input")
        )
        Text(
            text = result,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            fontSize = 34.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .testTag("result")
        )

        Row {
            CalculatorButton(
                text = "0",
                testTag = "zero",
                onClick = actions::clickZero
            )
            CalculatorButton(
                text = "1",
                testTag = "one",
                onClick = actions::clickOne
            )
            CalculatorButton(
                text = "2",
                testTag = "two",
                onClick = actions::clickTwo
            )
        }
        Row {
            CalculatorButton(
                text = "+",
                testTag = "plus",
                onClick = actions::clickPlus
            )
            CalculatorButton(
                text = "=",
                testTag = "equals",
                onClick = actions::clickEquals
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCalculatorScreen() =
    CalculatorScreen(
        input = "1+2",
        result = "3",
        actions = object : CalculatorActions {
            override fun clickZero() {}
            override fun clickOne() {}
            override fun clickTwo() {}
            override fun clickPlus() {}
            override fun clickEquals() {}
        }
    )

@Composable
private fun CalculatorButton(
    text: String,
    testTag: String,
    onClick: () -> Unit,
) = Button(
    onClick = onClick,
    modifier = Modifier
        .testTag(testTag)
        .padding(8.dp)
) {
    Text(
        text = text,
        fontSize = 24.sp
    )
}