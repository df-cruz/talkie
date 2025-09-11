package com.dfcruz.talkie.feature.createconversation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dfcruz.talkie.ui.component.TalkieTopAppBar
import com.dfcruz.talkie.ui.component.TalkieTopAppBarTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateConversationScreen(
    viewModel: CreateConversationViewModel = hiltViewModel<CreateConversationViewModel>(),
    navigateBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TalkieTopAppBar(
                title = { TalkieTopAppBarTitle("Create Conversation") },
                onNavigationIconPressed = navigateBack
            )
        }
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize(),
        ) {
            items(uiState.contacts) { contact ->
                ContactItem(
                    modifier = Modifier.clickable { },
                    avatarInitials = contact.avatarInitials,
                    name = contact.name,
                    contact = contact.number,
                )
            }
        }
    }
}
