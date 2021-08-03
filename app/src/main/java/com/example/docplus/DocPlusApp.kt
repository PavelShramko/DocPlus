package com.example.docplus

import android.app.Application
import com.example.docplus.di.AppComponent
import com.example.docplus.di.DaggerAppComponent

class DocPlusApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().bindContext(this).build()
    }
}