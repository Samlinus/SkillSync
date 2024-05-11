package com.example.skillsyncvcontrolled

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private var name: List<String>, private var skills: List<String>, private var images: List<Int>):
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val itemName: TextView = itemView.findViewById(R.id.tv_name)
        val itemSkills: TextView = itemView.findViewById(R.id.tv_skills)
        val itemImages: ImageView = itemView.findViewById(R.id.iv_image)

        init {
            itemView.setOnClickListener{
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "You clicked on item # ${position+1}",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.itemlayout,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return name.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemName.text = name[position]
        holder.itemSkills.text = skills[position]
        holder.itemImages.setImageResource(images[position])
    }
}