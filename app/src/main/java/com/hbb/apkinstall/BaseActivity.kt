package com.hbb.apkinstall

import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Build
import android.os.Bundle
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
import androidx.core.content.FileProvider
import com.hbb.apkinstall.ext.toast
import com.hbb.apkinstall.ui.theme.ApkInstallTheme
import com.hbb.apkinstall.utils.BarUtils
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

abstract class BaseActivity : ComponentActivity() {

    private lateinit var receiver: InstallReceiver
    lateinit var requestUnknownApp: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBar()
        registerInstallReceiver()
        initActivityResult()
        setContent {
            ApkInstallTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    InitLayout()
                }
            }
        }
        initData()
    }

    @Composable
    abstract fun InitLayout()

    abstract fun initData()

    private fun initActivityResult() {
        requestUnknownApp =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        if (packageManager.canRequestPackageInstalls()) {
                            handleIntent(intent)
                        } else {
                            toast("请点击”获取权限“打开开关", duration = Toast.LENGTH_LONG)
                        }
                    }
                }else if(result.resultCode == RESULT_CANCELED){
                    toast("请点击”获取权限“打开开关", duration = Toast.LENGTH_LONG)
                }
            }
    }


    private fun setStatusBar() {
        // 核心代码
        BarUtils.transparentStatusBar(this)
        BarUtils.setStatusBarLightMode(this, true)
//        BarUtils.setStatusBarVisibility(this,false)
    }


    /**
     * 注册安装广播
     */
    private fun registerInstallReceiver() {
        receiver = InstallReceiver()
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED)
        intentFilter.addDataScheme("package")
        registerReceiver(InstallReceiver(), intentFilter)
    }


     fun handleIntent(intent: Intent) {
        if (Intent.ACTION_VIEW == intent.action && "application/vnd.android.package-archive" == intent.type) {
            val apkUir = intent.data

            val filePath = apkUir?.encodedPath ?: return
            if (filePath.endsWith(".apk")) {
                install(intent)
            } else {
                copyFile(apkUir)
            }
        }
    }

    /**
     * copy后安装
     */
    private fun copyFile(uri: Uri) {
        try {
            val inputStream: InputStream =
                contentResolver.openInputStream(uri) ?: return
            val path = this.getExternalFilesDir("cache")!!.absolutePath + "/temp.apk"
            val outputStream: OutputStream = FileOutputStream(path)
            copyStream(inputStream, outputStream, path)
            inputStream.close()
            outputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
//    java.lang.IllegalArgumentException: Failed to find configured root that contains /data/data/com.hbb.apkinstall/cache/temp.apk

    private fun copyStream(input: InputStream, output: OutputStream, path: String) { //文件存储
        val bufferSize = 1024 * 2
        val buffer = ByteArray(bufferSize)
        val in0 = BufferedInputStream(input, bufferSize)
        val out = BufferedOutputStream(output, bufferSize)
        var count = 0
        var n = 0
        try {
            while (in0.read(buffer, 0, bufferSize).also { n = it } != -1) {
                out.write(buffer, 0, n)
                count += n
            }
            out.flush()
            out.close()
            in0.close()
            val id = application.packageName
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            val uri = FileProvider.getUriForFile(
                this,
                "$id.provider",
                File(path)
            )
            intent.setDataAndType(uri, "application/vnd.android.package-archive")
            install(intent)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun install(intent: Intent) {
        val dataUri = intent.data
        val mIntent = Intent(Intent.ACTION_INSTALL_PACKAGE).apply {
            data = dataUri
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_ACTIVITY_NEW_TASK
        }
//
        startActivity(mIntent)
        finish()
    }


    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }



}