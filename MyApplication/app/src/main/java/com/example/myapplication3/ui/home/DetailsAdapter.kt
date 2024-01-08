package com.example.myapplication3.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication3.R


class DetailsAdapter(private val ingredients: List<Pair<String, String>>) :
    RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ingredient, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (ingredient, measure) = ingredients[position]
        holder.bind(ingredient, measure)
    }

    override fun getItemCount(): Int = ingredients.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(ingredient: String, measure: String) {
            itemView.findViewById<TextView>(R.id.ingredientTextView).text = ingredient
            itemView.findViewById<TextView>(R.id.measureTextView).text = measure
        }
    }
}
