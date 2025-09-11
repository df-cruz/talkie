package com.dfcruz.talkie.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dfcruz.talkie.ui.theme.TalkieTheme
import com.dfcruz.talkie.util.compose.PreviewColumn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TalkieTopAppBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = { TalkieTopAppBarTitle("") },
    showNavigationIcon: Boolean = true,
    onNavigationIconPressed: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    TopAppBar(
        modifier = modifier,
        title = title,
        navigationIcon = {
            if (showNavigationIcon) {
                TalkieTopAppBarNavigationIcon(onClick = onNavigationIconPressed)
            }
        },
        actions = actions,
        colors = colors,
        scrollBehavior = scrollBehavior
    )
}

@Composable
fun TalkieTopAppBarTitle(
    text: String,
    modifier: Modifier = Modifier
) {
    TalkieText(
        text = text,
        style = TalkieTheme.typography.titleMedium,
        modifier = modifier
    )
}

@Composable
fun TalkieTopAppBarNavigationIcon(
    onClick: () -> Unit
) {
    TalkieIconButton(
        icon = Icons.AutoMirrored.Default.ArrowBack,
        onClick = onClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TalkieTopAppBarPreview() {
    PreviewColumn(
        backGroundColor = { TalkieTheme.colors.inverseSurface }
    ) {
        TalkieTopAppBar(
            title = { TalkieTopAppBarTitle("Chat") }
        )

        Spacer(Modifier.height(8.dp))

        TalkieTopAppBar(
            title = { TalkieTopAppBarTitle("Profile") },
            showNavigationIcon = false
        )

        Spacer(Modifier.height(8.dp))

        TalkieTopAppBar(
            title = { TalkieTopAppBarTitle("Settings") },
            actions = {
                TalkieIconButton(icon = Icons.Default.Search) {}
                TalkieIconButton(icon = Icons.Default.MoreVert) {}
            }
        )

        Spacer(Modifier.height(8.dp))

        TalkieTopAppBar(
            title = {
                Column {
                    TalkieTopAppBarTitle("Alice")
                    TalkieText("online", style = TalkieTheme.typography.bodySmall)
                }
            },
            actions = {
                TalkieIconButton(icon = Icons.Default.Call) {}
            }
        )
    }
}