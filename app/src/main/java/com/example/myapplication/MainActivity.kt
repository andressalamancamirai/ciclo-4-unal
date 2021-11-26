package com.example.myapplication

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var boton : Button = findViewById(R.id.button) as Button

        boton.setOnClickListener {

            llamarMensaje()
            getDialog().show()
            showDialog()
        }
    }

    fun getDialog():AlertDialog{
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Hola a tods").setTitle("Titulo")
        val dialog:AlertDialog = builder.create()
        return dialog
    }

    fun showDialog(){
        AlertDialog.Builder(this)
            .setMessage("HOla a todos dos")
            .setTitle("Titulo dos")
            .setPositiveButton("Positivo", { _ , _-> showAcceptFun() })
            .setNegativeButton("Negativo" , { _,_-> showNoFun()})
            .setCancelable(true)
            .create()
            .show()

        AlertDialog.Builder(this).setView(layoutInflater.inflate(R.layout.dialogo_personal,null))
            .setCancelable(true)
            .create()
            .show()
    }

    fun showAcceptFun(){
        println("Positivo")
    }
    fun showNoFun(){

    }
    fun showNeutral(){

    }

    private fun llamarMensaje() {
        Toast.makeText(this,"Click" , Toast.LENGTH_LONG).show()
    }
}

