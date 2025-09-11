package com.dfcruz.talkie.feature.createconversation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CreateConversationViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState: MutableStateFlow<CreateConversationUi> =
        MutableStateFlow(fakeCreateConversationUi)
    val uiState: StateFlow<CreateConversationUi> = _uiState.asStateFlow()

}


val fakeCreateConversationUi = CreateConversationUi(
    contacts = listOf(
        ContactInformation(
            avatarInitials = "AB",
            name = "Alice Brown",
            number = "+1 555 123 4567"
        ),
        ContactInformation(
            avatarInitials = "AM",
            name = "Alex Morgan",
            number = "+1 555 234 5678"
        ),
        ContactInformation(
            avatarInitials = "AR",
            name = "Anita Rao",
            number = "+91 98765 43210"
        ),

        ContactInformation(
            avatarInitials = "JD",
            name = "John Doe",
            number = "+1 555 987 6543"
        ),
        ContactInformation(
            avatarInitials = "JS",
            name = "Jane Smith",
            number = "+44 7700 111222"
        ),
        ContactInformation(
            avatarInitials = "JL",
            name = "James Lee",
            number = "+82 10 5555 6666"
        ),

        ContactInformation(
            avatarInitials = "MS",
            name = "Maria Sanchez",
            number = "+34 600 111 222"
        ),
        ContactInformation(
            avatarInitials = "MM",
            name = "Michael Miller",
            number = "+1 555 333 7777"
        ),
        ContactInformation(
            avatarInitials = "MR",
            name = "Mei Rong",
            number = "+86 139 8765 4321"
        ),

        ContactInformation(
            avatarInitials = "SK",
            name = "Sara Khan",
            number = "+971 50 123 4567"
        ),
        ContactInformation(
            avatarInitials = "SD",
            name = "Samir Das",
            number = "+91 99887 77665"
        ),
        ContactInformation(
            avatarInitials = "ST",
            name = "Sophia Taylor",
            number = "+61 400 555 999"
        )
    )
)