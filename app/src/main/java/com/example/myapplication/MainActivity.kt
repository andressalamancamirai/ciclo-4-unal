package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.io.File
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nombreArchivo = "test.txt"
        val file = File(filesDir,nombreArchivo)

        val fos = openFileOutput(nombreArchivo, Context.MODE_PRIVATE)
        fos.write("Primera linea".toByteArray())
        fos.write("Primera linea".toByteArray())
        fos.write("Primera linea".toByteArray())
        fos.write("Primera linea".toByteArray())
        fos.write("Primera linea".toByteArray())
        fos.close()


        val inputStream: InputStream = file.inputStream()
        val contenido = inputStream.bufferedReader().use{
            it.readText()
        }

        println(contenido)

        file.delete()
    }

}