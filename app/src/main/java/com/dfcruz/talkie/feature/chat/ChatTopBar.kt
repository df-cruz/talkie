package com.dfcruz.talkie.feature.chat

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dfcruz.talkie.ui.component.TalkieAvatar
import com.dfcruz.talkie.ui.component.TalkieIconButton
import com.dfcruz.talkie.ui.component.TalkieText
import com.dfcruz.talkie.ui.theme.TalkieTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTopBar(avatarImage: String?, title: String?, onBackPressed: () -> Unit) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                TalkieAvatar(size = 40.dp)

                Spacer(Modifier.width(8.dp))

                title?.let {
                    TalkieText(text = title, style = TalkieTheme.typography.titleMedium)
                }
            }
        },
        navigationIcon = {
            TalkieIconButton(icon = Icons.AutoMirrored.Default.ArrowBack) {
                onBackPressed()
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ChatTopBarPreview() {
    TalkieTheme {
        ChatTopBar(avatarImage = null, title = "John Doe") {}
    }
}