package com.example.rp3yf.applemusic.Database

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by rp3yf on 1/21/2018.
 */
class Configuration : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val myConfig = RealmConfiguration.Builder()
                .name("realm-music")
                .build()
        Realm.setDefaultConfiguration(myConfig)
    }
}