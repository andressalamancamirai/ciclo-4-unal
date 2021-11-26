package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class AnimalsAdapter(
    private val animals:List<Animal>) : RecyclerView.Adapter<AnimalsAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val item = animals[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.card_animal,parent,false))
    }

    override fun getItemCount(): Int {
        return animals.size
    }


    class ViewHolder (view: View) : RecyclerView.ViewHolder(view){
        fun bind(animal:Animal){
            itemView.textName.text = animal.name
            itemView.textDescription.text = animal.description
            itemView.imageBackground.setImageResource(animal.image)
        }
    }


}