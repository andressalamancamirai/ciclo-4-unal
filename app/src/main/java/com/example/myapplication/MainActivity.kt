package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configView()

        var botonGuardar = findViewById <Button>(R.id.btnGuardar) as Button
        var botonBorrar = findViewById <Button>(R.id.btnBorrar) as Button

        var tvNombre = findViewById <TextView> (R.id.tvNombre) as TextView
        var etNOmbre = findViewById <EditText> (R.id.etNombre) as EditText

       botonGuardar.setOnClickListener{
            SharedApp.preferencias.nombre = etNOmbre.text.toString()
            configView()
        }
        botonBorrar.setOnClickListener {
            SharedApp.preferencias.nombre = ""
            configView()
        }


    }

    fun configView(){
        if (isSavedName())
            showProfileName()
        else
            showGuest()
    }

    fun isSavedName():Boolean{
        val nombreAlmacenado = SharedApp.preferencias.nombre
        return nombreAlmacenado != ""
    }

    fun showProfileName(){
        var botonGuardar = findViewById <Button>(R.id.btnGuardar) as Button
        var botonBorrar = findViewById <Button>(R.id.btnBorrar) as Button

        var tvNombre = findViewById <TextView> (R.id.tvNombre) as TextView
        var etNOmbre = findViewById <EditText> (R.id.etNombre) as EditText

        tvNombre.visibility = View.VISIBLE
        tvNombre.text = "Hola, ${SharedApp.preferencias.nombre}"
        botonBorrar.visibility = View.VISIBLE

        etNOmbre.visibility = View.INVISIBLE
        botonGuardar.visibility = View.INVISIBLE
    }

    fun showGuest(){
        var botonGuardar = findViewById <Button>(R.id.btnGuardar) as Button
        var botonBorrar = findViewById <Button>(R.id.btnBorrar) as Button

        var tvNombre = findViewById <TextView> (R.id.tvNombre) as TextView
        var etNOmbre = findViewById <EditText> (R.id.etNombre) as EditText

        tvNombre.visibility = View.INVISIBLE
        botonBorrar.visibility = View.INVISIBLE

        etNOmbre.visibility = View.VISIBLE
        botonGuardar.visibility = View.VISIBLE
    }
}