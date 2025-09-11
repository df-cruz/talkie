package com.dfcruz.talkie.feature.createconversation

data class CreateConversationUi(
    val contacts: List<ContactInformation> = emptyList()
)

data class ContactInformation(
    val avatarInitials: String,
    val name: String,
    val number: String
)