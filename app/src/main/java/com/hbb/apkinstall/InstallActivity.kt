package com.hbb.apkinstall

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageInstaller
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.content.FileProvider
import com.hbb.apkinstall.ext.toast
import com.hbb.apkinstall.ui.theme.ApkInstallTheme
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class InstallActivity : BaseActivity() {


    @Composable
    override fun InitLayout() {
        Home()
    }

    override fun initData() {
        //处理用户选中的文件，进行校验 - 拷贝 - 安装 - 删除临时文件
        requestUnknownApp(intent)
    }


    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        requestUnknownApp(intent)
    }

    private fun requestUnknownApp(apkIntent: Intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (!packageManager.canRequestPackageInstalls()) {
                toast("请打开开关，即可安装!", duration = Toast.LENGTH_LONG)
                val intent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES)
                intent.setData(Uri.parse("package:$packageName"))
                requestUnknownApp.launch(intent)
            } else {
                // 用户已经允许了安装未知来源的应用
                handleIntent(apkIntent)
            }
        }
    }

}