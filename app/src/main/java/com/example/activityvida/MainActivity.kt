package com.example.activityvida

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    var USERNAME = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var valorObtenido = savedInstanceState?.get(USERNAME)

       // var boton :Button = findViewById<Button>(R.id.buttonMain)

        //boton.setOnClickListener {
        //    val intent = Intent(this, SegundaActivity::class.java)
        //    startActivity(intent)
        //}
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putString(USERNAME , "andres.salamanca")
        super.onSaveInstanceState(savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        println("On Start")
    }

    override fun onResume() {
        super.onResume()
        println("On resume")
    }

    override fun onPause() {
        super.onPause()
        println("On pause")
    }

    override fun onStop() {
        super.onStop()
        println("On stop")
    }

    override fun onRestart() {
        super.onRestart()
        println("On Restrart")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("On destroy")
    }
}