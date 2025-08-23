package com.dfcruz.talkie.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.dfcruz.talkie.ui.theme.TalkieTheme

/**
 * A customizable text field component styled with the Talkie design system.
 *
 * @param value The current text value to be displayed in the text field.
 * @param onValueChange Callback invoked when the text changes. Receives the updated string.
 * @param modifier [Modifier] to be applied to the text field container.
 * @param enabled Whether the text field is enabled for user input. Defaults to `true`.
 * @param maxLines Maximum number of lines for the text field. Defaults to [Int.MAX_VALUE].
 * @param innerPadding Padding applied inside the text field container. Defaults to `16.dp` horizontal and `8.dp` vertical.
 * @param keyboardOptions Configuration for the software keyboard, such as capitalization and input type.
 *                        Defaults to capitalizing sentences ([KeyboardCapitalization.Sentences]).
 * @param placeholder Optional composable content displayed when the text field is empty.
 * @param leadingContent Optional composable content displayed before the text input area.
 * @param trailingContent Optional composable content displayed after the text input area.
 */
@Composable
fun TalkieTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    textStyle: TextStyle = TalkieTheme.typography.bodyMedium,
    innerPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    keyboardOptions: KeyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
    placeholder: @Composable (RowScope.() -> Unit)? = null,
    leadingContent: @Composable (RowScope.() -> Unit)? = null,
    trailingContent: @Composable (RowScope.() -> Unit)? = null,
) {
    var textState by remember { mutableStateOf(TextFieldValue(text = value)) }

    BasicTextField(
        modifier = modifier
            .clip(shape = TalkieTheme.shapes.extraLarge)
            .background(TalkieTheme.colors.surfaceContainerHigh)
            .padding(innerPadding),
        value = textState,
        onValueChange = {
            textState = it
            if (value != it.text) {
                onValueChange(it.text)
            }
        },
        textStyle = textStyle,
        cursorBrush = SolidColor(TalkieTheme.colors.primary),
        decorationBox = { innerTextField ->
            DecorationBox(
                textState.text,
                innerTextField,
                placeholder,
                leadingContent,
                trailingContent,
            )
        },
        maxLines = maxLines,
        singleLine = maxLines == 1,
        enabled = enabled,
        keyboardOptions = keyboardOptions,
    )
}

@Composable
private fun DecorationBox(
    value: String,
    innerTextField: @Composable () -> Unit,
    placeholder: @Composable (RowScope.() -> Unit)? = null,
    leadingContent: @Composable (RowScope.() -> Unit)? = null,
    trailingContent: @Composable (RowScope.() -> Unit)? = null,
) {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        leadingContent?.let {
            leadingContent()
            Spacer(Modifier.width(8.dp))
        }

        Box(modifier = Modifier.weight(1f)) {
            innerTextField()
            if (value.isEmpty() && placeholder != null) {
                this@Row.placeholder()
            }
        }

        trailingContent?.let {
            Spacer(Modifier.width(8.dp))
            trailingContent()
        }
    }
}