package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences

class Preferencias (context:Context) {

    val PREF_NAME = "com.example.myapplication"
    val SHARED_NAME = "shared_name"
    val preferencias: SharedPreferences = context.getSharedPreferences(PREF_NAME,0)

    var nombre: String
        get() = preferencias.getString(SHARED_NAME,"")
        set(value) = preferencias.edit().putString(SHARED_NAME,value).apply()

}