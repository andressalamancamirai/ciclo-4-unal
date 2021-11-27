package com.example.myapplication

import android.app.Application

class SharedApp : Application() {

    companion object{
        lateinit var preferencias: Preferencias
    }

    override fun onCreate() {
        super.onCreate()
        preferencias = Preferencias(applicationContext)
    }
}