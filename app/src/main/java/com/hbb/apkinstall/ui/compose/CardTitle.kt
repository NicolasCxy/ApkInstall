package com.hbb.apkinstall.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CardTitle(modifier: Modifier = Modifier){
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "APK.1 安装器",
            modifier = Modifier
                .paddingFromBaseline(top = 10.dp, bottom = 10.dp)
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.headlineMedium
        )
    }
}