package com.hbb.apkinstall.ui.compose

import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hbb.apkinstall.R
import com.hbb.apkinstall.ext.toast

@Composable
fun CardPermission(modifier: Modifier = Modifier) {

    val context = LocalContext.current

    var showDialog by remember { mutableStateOf(false) }

    var request by remember { mutableStateOf(false) }

    if (showDialog) {
        if(if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                !context.packageManager.canRequestPackageInstalls()
            } else {
                TODO("VERSION.SDK_INT < O")
            }
        ){
            ShowDialog(
                onDismiss = { showDialog = false },
                onConfirm = { request = true })
        }else{
            toast("权限已获取,可直接操作!")
        }
    }

    if(request){
        request = false
        val intent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES)
        context.startActivity(intent)
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.white)),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier
            .clip(shape = MaterialTheme.shapes.extraLarge)
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(20.dp, 20.dp, 20.dp, 0.dp)
    ) {

        Row(modifier = Modifier
            .clickable {
                showDialog = true
            }
            .padding(20.dp)) {

            Text(text = "获取权限", modifier = Modifier.weight(1f, true))

            Icon(Icons.Outlined.KeyboardArrowRight, contentDescription = "get permissions")
        }

    }
}


@Composable
fun CardReadSDFile(modifier: Modifier = Modifier) {
    var showDialog by remember { mutableStateOf(false) }
    Card(
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.white)),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier
            .clip(shape = MaterialTheme.shapes.extraLarge)
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(20.dp, 15.dp, 20.dp, 10.dp)
    ) {

        Row(modifier = Modifier
            .clickable {
                showDialog = true
            }
            .padding(20.dp)) {

            Text(text = "读取文件夹", modifier = Modifier.weight(1f, true))

            Icon(Icons.Outlined.KeyboardArrowRight, contentDescription = "read folder")
        }

    }
}



@Composable
fun ShowDialog(modifier: Modifier = Modifier, onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        title = {
            Text(
                text = "获取权限",
                style = MaterialTheme.typography.titleLarge
            )
        },
        text = {
            Text(
                text = "点击确认按钮后，手机将跳转到系统设置页面，请为APK安装器打开开关。",
                style = MaterialTheme.typography.bodyMedium
            )
        },
        dismissButton = {
            TextButton(onClick = {
                onDismiss()
            }) {
                Text(text = "取消")
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onDismiss()
                onConfirm()
            }) {
                Text(text = "确认")
            }
        }
    )
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreCardPermission() {
    CardPermission()
}