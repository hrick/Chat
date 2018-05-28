package br.com.oxxynet.chatoxxy

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.j256.ormlite.android.apptools.OpenHelperManager

class ChatOxxyApplication : MultiDexApplication() {

    override fun onTerminate() {
        OpenHelperManager.releaseHelper()
        super.onTerminate()
    }


    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}