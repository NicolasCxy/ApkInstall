package com.hbb.apkinstall

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.hbb.apkinstall.ext.toast
import java.io.File

class InstallReceiver :BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if(intent.action == Intent.ACTION_PACKAGE_ADDED){
            val path = context.getExternalFilesDir("cache")!!.absolutePath+ "/temp.apk"
            val file = File(path)
            file.delete()
        }
    }
}