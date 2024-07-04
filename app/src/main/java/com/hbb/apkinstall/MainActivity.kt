package com.hbb.apkinstall

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hbb.apkinstall.ui.compose.CardAbout
import com.hbb.apkinstall.ui.compose.CardGuide
import com.hbb.apkinstall.ui.compose.CardPermission
import com.hbb.apkinstall.ui.compose.CardTitle
import com.hbb.apkinstall.ui.theme.ApkInstallTheme

class MainActivity : BaseActivity() {

    @Composable
    override fun InitLayout() {
        Home()
    }

    override fun initData() {
    }
}

@Composable
fun Home(modifier: Modifier = Modifier) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        item {CardTitle() }
        item {CardGuide() }
        item {CardPermission() }
        item {CardAbout() }

    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ApkInstallTheme {
        Home()
    }
}