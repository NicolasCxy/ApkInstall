package com.hbb.apkinstall.ui.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hbb.apkinstall.R

@Composable
fun CardGuide(modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.white)),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier
            .clip(shape = MaterialTheme.shapes.extraLarge)
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Column(
            modifier = modifier
                .padding(15.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "指引",
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.ExtraBold),
            )
            Spacer(modifier = Modifier.height(8.dp)) // 设置间距

            Text(
                text = "由于在QQ和微信中发送apk文件时，文件名会被添加后缀.1，此App可以省去重命名步骤，直接安装apk",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(0.dp, 15.dp, 0.dp, 0.dp)
            )

            Text(
                text = "点击文件 → 用其他应用打开 → Apk安装器 → 软件安装程序",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(10.dp),
                fontFamily = FontFamily.Monospace,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

}

@Preview
@Composable
fun PreCardGuide() {
    CardGuide()
}