package com.dfcruz.talkie.ui.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dfcruz.talkie.ui.theme.TalkieTheme

@Composable
fun TalkieMessageComposer(
    modifier: Modifier = Modifier,
    onSendMessage: (String) -> Unit
) {
    var value by remember { mutableStateOf("") }

    Surface(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(Modifier.width(16.dp))

            TalkieTextField(
                modifier = modifier
                    .weight(1f)
                    .animateContentSize(),
                value = value,
                onValueChange = { value = it },
                placeholder = {
                    TalkieText(
                        text = "Write a message",
                        style = TalkieTheme.typography.bodyLarge,
                        color = TalkieTheme.colors.onSurfaceVariant
                    )
                },
            )

            if (!value.isEmpty()) {
                Spacer(Modifier.size(8.dp))
                SendMessageButton {
                    onSendMessage(value)
                }
                Spacer(Modifier.size(16.dp))
            } else {
                Spacer(Modifier.size(16.dp))
            }
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

@Preview(showBackground = true)
@Composable
fun TalkieMessageComposerPreview() {
    TalkieTheme {
        TalkieMessageComposer {}
    }
}