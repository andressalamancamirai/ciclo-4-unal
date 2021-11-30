package com.example.myapplication

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileInputStream

private val TAG = "ExternalStorageEjemplo"
private val RECORD_REQUEST_CODE = 101

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupPermisos()
    }

    private fun setupPermisos() {
        val permiso = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        print(permiso)
        if(permiso != PackageManager.PERMISSION_GRANTED){
            print("El permiso no está aceptado")
        }else{
            readWriteExternalStorage()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == RECORD_REQUEST_CODE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                print("Tengo permiso")
                readWriteExternalStorage()
            }else {
                print("NO tengo permisos")
                makeRequest()
            }
        }
    }

    fun makeRequest(){
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            RECORD_REQUEST_CODE)
    }

    fun isExternalStorageReadble():Boolean{
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    fun isExternalStorageWritable(): Boolean{
        return  Environment.getExternalStorageState() in setOf(Environment.MEDIA_MOUNTED,Environment.MEDIA_MOUNTED_READ_ONLY)
    }

    fun getPrivateStorage(fileName: String): File? {
        return File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),fileName)
    }

    private fun readWriteExternalStorage() {
        if(isExternalStorageReadble() && isExternalStorageWritable()){
            val textfile = getPrivateStorage("archivo.txt")
            textfile?.run{
                appendText("Linea 1")
                appendText("Linea 2")
                appendText("Ultima linea")
            }
            val inputAsString = FileInputStream(textfile).bufferedReader().use{ it.readText()}
            print (inputAsString)
        }
    }
}