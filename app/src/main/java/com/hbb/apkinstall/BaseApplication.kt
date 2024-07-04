package com.hbb.apkinstall

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class BaseApplication :Application() {


    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        context = base
        application = this
    }

    companion object{

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        lateinit var application: Application
    }

}