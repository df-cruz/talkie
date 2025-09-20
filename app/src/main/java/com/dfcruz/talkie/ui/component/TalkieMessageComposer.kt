package com.dfcruz.talkie.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dfcruz.talkie.ui.theme.TalkieTheme
import com.dfcruz.talkie.util.compose.PreviewColumn

@Composable
fun TalkieMessageComposer(
    modifier: Modifier = Modifier,
    text: String,
    onSendMessage: (String) -> Unit,
) {
    var value by remember(text) { mutableStateOf(text) }

    Surface(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .animateContentSize()
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            Spacer(Modifier.width(16.dp))

            val textStyle = TalkieTheme.typography.bodyMedium.copy(lineHeight = 20.sp)
            TalkieTextField(
                modifier = modifier
                    .weight(1f)
                    .animateContentSize(),
                value = value,
                textStyle = textStyle,
                innerPadding = PaddingValues(
                    start = 16.dp,
                    top = 6.dp,
                    end = 6.dp,
                    bottom = 6.dp,
                ),
                onValueChange = { value = it },
                placeholder = {
                    TalkieText(
                        text = "Write a message",
                        style = textStyle,
                        color = TalkieTheme.colors.onSurfaceVariant,
                    )
                },
                trailingContent = {
                    Row(
                        modifier = Modifier.height(36.dp),
                    ) {
                        AnimatedVisibility(
                            visible = !value.isEmpty(),
                            enter = fadeIn(animationSpec = tween(durationMillis = 150)),
                            exit = fadeOut(animationSpec = tween(durationMillis = 150))
                        ) {
                            SendMessageButton {
                                onSendMessage(value)
                                value = ""
                            }
                        }
                    }
                }
            )

            Spacer(Modifier.size(16.dp))
        }
    }
}

@Composable
private fun SendMessageButton(onClick: () -> Unit) {
    Box(
        Modifier
            .clip(CircleShape)
            .background(TalkieTheme.colors.primaryContainer)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .padding(8.dp)
                .size(20.dp),
            imageVector = Icons.AutoMirrored.Default.Send,
            contentDescription = null,
            tint = TalkieTheme.colors.onPrimaryContainer
        )
    }
}

@Preview
@Composable
fun TalkieMessageComposerPreview() {
    var value by remember { mutableStateOf("") }
    PreviewColumn {
        TalkieMessageComposer(text = value, modifier = Modifier.background(Color.Red)) {}
        TalkieMessageComposer(text = "3") {}
    }
}