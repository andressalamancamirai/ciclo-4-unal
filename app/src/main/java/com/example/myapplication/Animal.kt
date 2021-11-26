package com.example.myapplication

import java.io.Serializable

data class Animal(
    var name:String,
    var description:String,
    var image:Int
) : Serializable
