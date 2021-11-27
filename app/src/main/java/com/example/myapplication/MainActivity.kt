package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.myapplication.database.UserModel
import com.example.myapplication.database.UsersDBHelper
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var usersDBHelper: UsersDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        usersDBHelper = UsersDBHelper(this)

        binding.btnAddUser.setOnClickListener() {
            println("Agregar usuario")
            var userId = binding.etUserId.text.toString()
            var nombre = binding.etUserName.text.toString()
            var edad = binding.etUserAge.text.toString()

            var almacenar  = usersDBHelper.insertUser(UserModel(userId,nombre,edad))

            if(almacenar){
                binding.etUserAge.setText("")
                binding.etUserId.setText("")
                binding.etUserName.setText("")
                binding.twResult.text = "Usurio almacenado exitosamente"
            }else{
                binding.twResult.text = "Usurio no fue almacenado revisar error"
            }
            binding.allEntries.removeAllViews()
        }
        binding.btnDeleteUser.setOnClickListener() {
            var userId = binding.etUserId.text.toString()
            val resultado = usersDBHelper.deleteUser(userId)
            if(resultado){
                binding.twResult.text = "Usuario eliminado exitosamente"
            }else{
                binding.twResult.text = "El usuario no pudo ser eliminado"
            }
            binding.allEntries.removeAllViews()
        }

        binding.btnShowAll.setOnClickListener() {
            var users = usersDBHelper.readAllUsers()
            binding.allEntries.removeAllViews()

            println(users.size)

            users.forEach {
                var tvUser = TextView(this)
                tvUser.textSize = 30F
                tvUser.text = it.userId.toString()+ " - " + it.name.toString() + " - " +
                        it.age.toString()
                binding.allEntries.addView(tvUser)
            }
            binding.twResult.text = "Cantidad de usuarios: " + users.size
        }
    }
}