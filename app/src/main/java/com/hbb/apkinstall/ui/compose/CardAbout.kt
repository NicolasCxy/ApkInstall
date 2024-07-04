package com.hbb.apkinstall.ui.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hbb.apkinstall.R

@Composable
fun CardAbout(modifier: Modifier = Modifier) {
    val uriHandler = LocalUriHandler.current

    Card(
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.white)),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier
            .clip(shape = MaterialTheme.shapes.extraLarge)
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            Text(text = "关于",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.clickable(
                    onClick = {
                    },
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false),
                )
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(0.dp, 10.dp, 0.dp, 0.dp)
                    .clickable(
                        onClick = {
                            uriHandler.openUri("https://github.com/NicolasCxy/ApkInstall")
                        },
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                    )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_github_fill),
                    contentDescription = "about"
                )

                Column {
                    Text(
                        text = "Github",
                        modifier = Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp)
                    )
                    Text(
                        text = "Ium-NicolasCxy/ApkInstall",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreCarAbout() {
    CardAbout()
}