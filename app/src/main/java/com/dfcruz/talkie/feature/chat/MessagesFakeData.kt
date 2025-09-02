package com.dfcruz.talkie.feature.chat

val sampleMessages = listOf(
    // CurrentUser messages
    Message(
        id = "1",
        content = MessageContent.Text("Hey, how are you?"),
        createdAtLabel = "09:00",
        author = MessageAuthor.CurrentUser,
        groupPosition = MessageGroupPosition.First
    ),
    Message(
        id = "2",
        content = MessageContent.Text("Did you check the report?"),
        createdAtLabel = "09:01",
        author = MessageAuthor.CurrentUser,
        groupPosition = MessageGroupPosition.Middle(1)
    ),
    Message(
        id = "3",
        content = MessageContent.Emoji("👍"),
        createdAtLabel = "09:02",
        author = MessageAuthor.CurrentUser,
        groupPosition = MessageGroupPosition.Last(2)
    ),

    // External user messages
    Message(
        id = "4",
        content = MessageContent.Text("Hi! I'm good, thanks."),
        createdAtLabel = "09:03",
        author = MessageAuthor.External(
            details = Author(
                id = "user_1",
                name = "Alice",
                avatarInitials = "A",
                avatarUrl = null,
                status = UserStatus.ONLINE
            )
        ),
        groupPosition = MessageGroupPosition.First
    ),
    Message(
        id = "5",
        content = MessageContent.Text("Yes, I saw it."),
        createdAtLabel = "09:04",
        author = MessageAuthor.External(
            details = Author(
                id = "user_1",
                name = "Alice",
                avatarInitials = "A",
                avatarUrl = null,
                status = UserStatus.ONLINE
            )
        ),
        groupPosition = MessageGroupPosition.Middle(1)
    ),
    Message(
        id = "6",
        content = MessageContent.Emoji("😊"),
        createdAtLabel = "09:05",
        author = MessageAuthor.External(
            details = Author(
                id = "user_1",
                name = "Alice",
                avatarInitials = "A",
                avatarUrl = null,
                status = UserStatus.ONLINE
            )
        ),
        groupPosition = MessageGroupPosition.Last(2)
    ),

    // More CurrentUser messages
    Message(
        id = "8",
        content = MessageContent.Text("Let's meet at 10."),
        createdAtLabel = "09:07",
        author = MessageAuthor.CurrentUser,
        groupPosition = MessageGroupPosition.First
    ),
    Message(
        id = "9",
        content = MessageContent.Text("Does that work for you?"),
        createdAtLabel = "09:08",
        author = MessageAuthor.CurrentUser,
        groupPosition = MessageGroupPosition.Last(1)
    ),
    Message(
        id = "10",
        content = MessageContent.Emoji("✅"),
        createdAtLabel = "09:09",
        author = MessageAuthor.CurrentUser,
        groupPosition = MessageGroupPosition.First
    ),

    // More External messages
    Message(
        id = "11",
        content = MessageContent.Text("Sure, 10 works!"),
        createdAtLabel = "09:10",
        author = MessageAuthor.External(
            details = Author(
                id = "user_1",
                name = "Alice",
                avatarInitials = "A",
                avatarUrl = null,
                status = UserStatus.ONLINE
            )
        ),
        groupPosition = MessageGroupPosition.First
    ),
    Message(
        id = "12",
        content = MessageContent.Emoji("🙌"),
        createdAtLabel = "09:11",
        author = MessageAuthor.External(
            details = Author(
                id = "user_1",
                name = "Alice",
                avatarInitials = "A",
                avatarUrl = null,
                status = UserStatus.ONLINE
            )
        ),
        groupPosition = MessageGroupPosition.Last(1)
    ),

    // Mix some longer texts
    Message(
        id = "13",
        content = MessageContent.Text("Here is a longer message to test bubble wrapping in Compose. It should wrap correctly."),
        createdAtLabel = "09:12",
        author = MessageAuthor.CurrentUser,
        groupPosition = MessageGroupPosition.First
    ),
    Message(
        id = "14",
        content = MessageContent.Text("Following up with another long message from the same user to test middle bubble shapes."),
        createdAtLabel = "09:13",
        author = MessageAuthor.CurrentUser,
        groupPosition = MessageGroupPosition.Middle(13)
    ),
    Message(
        id = "15",
        content = MessageContent.Text("And this will be the last one from me in this group."),
        createdAtLabel = "09:14",
        author = MessageAuthor.CurrentUser,
        groupPosition = MessageGroupPosition.Last(14)
    ),

    // Some External user long messages
    Message(
        id = "16",
        content = MessageContent.Text("This is a long message from Alice to see how bubbles behave with wrapping."),
        createdAtLabel = "09:15",
        author = MessageAuthor.External(
            details = Author(
                id = "user_1",
                name = "Alice",
                avatarInitials = "A",
                avatarUrl = null,
                status = UserStatus.ONLINE
            )
        ),
        groupPosition = MessageGroupPosition.First
    ),
    Message(
        id = "17",
        content = MessageContent.Text("Adding a middle message to test middle bubble shape."),
        createdAtLabel = "09:16",
        author = MessageAuthor.External(
            details = Author(
                id = "user_1",
                name = "Alice",
                avatarInitials = "A",
                avatarUrl = null,
                status = UserStatus.ONLINE
            )
        ),
        groupPosition = MessageGroupPosition.Middle(16)
    ),
    Message(
        id = "18",
        content = MessageContent.Text("And finally, the last message from Alice in this group."),
        createdAtLabel = "09:17",
        author = MessageAuthor.External(
            details = Author(
                id = "user_1",
                name = "Alice",
                avatarInitials = "A",
                avatarUrl = null,
                status = UserStatus.ONLINE
            )
        ),
        groupPosition = MessageGroupPosition.Last(17)
    ),

    // Emoji-only message from CurrentUser
    Message(
        id = "20",
        content = MessageContent.Emoji("🎉"),
        createdAtLabel = "09:19",
        author = MessageAuthor.CurrentUser,
        groupPosition = MessageGroupPosition.First
    )
)
