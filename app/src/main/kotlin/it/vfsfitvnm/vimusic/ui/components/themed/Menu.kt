package it.vfsfitvnm.vimusic.ui.components.themed

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import it.vfsfitvnm.vimusic.R
import it.vfsfitvnm.vimusic.ui.styling.LocalAppearance
import it.vfsfitvnm.vimusic.utils.medium
import it.vfsfitvnm.vimusic.utils.secondary

@Composable
inline fun Menu(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    val (colorPalette) = LocalAppearance.current

    Column(
        modifier = modifier
            .padding(top = 48.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .background(colorPalette.background1)
            .padding(top = 2.dp)
            .padding(vertical = 8.dp)
            .navigationBarsPadding(),
        content = content
    )
}

@Composable
fun MenuEntry(
    @DrawableRes icon: Int,
    text: String,
    onClick: () -> Unit,
    secondaryText: String? = null,
    isEnabled: Boolean = true,
) {
    val (colorPalette, typography) = LocalAppearance.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier
            .clickable(
                indication = rememberRipple(bounded = true),
                interactionSource = remember { MutableInteractionSource() },
                enabled = isEnabled,
                onClick = onClick
            )
            .fillMaxWidth()
            .alpha(if (isEnabled) 1f else 0.4f)
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            colorFilter = ColorFilter.tint(colorPalette.text),
            modifier = Modifier
                .size(14.dp)
        )

        Column {
            BasicText(
                text = text,
                style = typography.xs.medium
            )

            secondaryText?.let { secondaryText ->
                BasicText(
                    text = secondaryText,
                    style = typography.xxs.medium.secondary
                )
            }
        }
    }
}

@Composable
fun MenuIconButton(
    @DrawableRes icon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val (colorPalette) = LocalAppearance.current

    Box(
        modifier = modifier
            .padding(horizontal = 14.dp)
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            colorFilter = ColorFilter.tint(colorPalette.text),
            modifier = Modifier
                .clickable(onClick = onClick)
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .size(20.dp)
        )
    }
}

@Composable
fun MenuBackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    MenuIconButton(
        icon = R.drawable.chevron_back,
        onClick = onClick,
        modifier = modifier
    )
}
