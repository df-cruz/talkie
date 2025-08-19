package com.dfcruz.talkie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.dfcruz.talkie.feature.chat.ChatScreen
import com.dfcruz.talkie.ui.theme.TalkieTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TalkieTheme {
                ChatScreen { }
            }
        }
    }
}