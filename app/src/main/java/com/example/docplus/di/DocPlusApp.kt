package com.example.docplus.di

import android.app.Application

class DocPlusApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().bindContext(this).build()
    }
}