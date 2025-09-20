package com.dfcruz.talkie.util.compose

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.dfcruz.talkie.ui.theme.TalkieTheme

/**
 * A lightweight wrapper composable for creating consistent design-time previews.
 *
 * Example:
 * ```
 * @Preview
 * @Composable
 * fun BoxPreview() {
 *     PreviewBox {
 *         Text("Hello Preview")
 *     }
 * }
 * ```
 */
@Composable
fun PreviewBox(
    modifier: Modifier = Modifier,
    backGroundColor: @Composable () -> Color = { TalkieTheme.colors.surface },
    padding: PaddingValues = PaddingValues(4.dp),
    content: @Composable () -> Unit,
) {
    TalkieTheme {
        Box(
            modifier

                .background(backGroundColor())
                .padding(padding),
        ) {
            content()
        }
    }
}

/**
 * A themed container composable for previewing vertical layouts.
 *
 * Example:
 * ```
 * @Preview
 * @Composable
 * fun ColumnPreview() {
 *     PreviewColumn {
 *         Text("Item 1")
 *         Text("Item 2")
 *     }
 * }
 * ```
 */
@Composable
fun PreviewColumn(
    modifier: Modifier = Modifier,
    backGroundColor: @Composable () -> Color = { TalkieTheme.colors.surface },
    layoutDirection: LayoutDirection = LayoutDirection.Ltr,
    padding: PaddingValues = PaddingValues(4.dp),
    content: @Composable ColumnScope.() -> Unit,
) {
    CompositionLocalProvider(
        LocalLayoutDirection provides layoutDirection
    ) {
        TalkieTheme {
            Column(
                modifier
                    .background(backGroundColor())
                    .padding(padding),
            ) {
                content()
            }
        }
    }
}

/**
 * A composable that allows previewing the content of a [Dialog] inside
 * Jetpack Compose previews.
 *
 * Instead of actually showing a system dialog, this function builds the dialog,
 * extracts its content view, and renders it inline using [AndroidView].
 * This makes it possible to design and iterate on dialog layouts directly
 * in Android Studio's Compose Preview.
 *
 *
 * Example:
 * ```
 * @Preview
 * @Composable
 * fun MyDialogPreview() {
 *     PreviewDialog(
 *         modifier = Modifier.size(300.dp),
 *         factory = { context ->
 *             AlertDialog.Builder(context)
 *                 .setTitle("Hello")
 *                 .setMessage("This is a preview")
 *                 .setPositiveButton("OK", null)
 *                 .create()
 *         }
 *     )
 * }
 * ```
 *
 * ### Credits
 * Inspired by ideas from István Juhos’s talk
 * *“Practical Tips and Tricks to Improve Your Compose Previews”*
 * (Droidcon Berlin 2024, [speakerdeck.com](https://speakerdeck.com/stewemetal/practical-tips-and-tricks-to-improve-your-compose-previews-droidcon-berlin-2024)).
 */
@Composable
fun PreviewDialog(
    modifier: Modifier = Modifier,
    factory: (context: Context) -> Dialog
) {
    PreviewBox(
        padding = PaddingValues()
    ) {
        AndroidView(
            modifier = modifier,
            factory = { context ->
                val dialog = factory(context)
                dialog.create()

                val view = dialog.findViewById<View>(android.R.id.content)
                (view.parent as ViewGroup).removeView(view)
                view
            }
        )
    }
}
