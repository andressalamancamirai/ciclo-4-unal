package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class AnimalsAdapter : RecyclerView.Adapter<AnimalsAdapter.ViewHolder>() {

    val animals = arrayListOf<Animal>(
        Animal("Pez payaso","Descripción de pez",R.drawable.ic_launcher_foreground),
        Animal("Delfin", "Descripcion del delfin",R.drawable.ic_launcher_background),
        Animal("Perro","Descripción del perro",R.drawable.ic_launcher_background)
    )


    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        var animal = animals[position]
        holder.itemName.text = animal.name
        holder.itemDetalles.text = animal.description
        holder.itemImage.setImageResource(animal.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.card_animal,parent,false))
    }

    override fun getItemCount(): Int {
        return animals.size
    }


    inner class ViewHolder (view: View) : RecyclerView.ViewHolder(view){

        var itemImage : ImageView
        var itemName : TextView
        var itemDetalles : TextView

        init {
            itemImage = itemView.findViewById(R.id.imageBackground)
            itemName = itemView.findViewById(R.id.textName)
            itemDetalles = itemView.findViewById(R.id.textDescription)
        }
    }


}