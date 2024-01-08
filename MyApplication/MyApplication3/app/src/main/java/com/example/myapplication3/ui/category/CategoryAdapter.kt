package com.example.myapplication3.ui.category

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication3.R

class CategoryAdapter(
    private val context: Context,
    private var originalNames: Array<String>,
) : RecyclerView.Adapter<CategoryAdapter.MyViewClass>() {

    private var filteredNames: MutableList<String> = mutableListOf()

    init {
        filteredNames.addAll(originalNames)
    }


    class MyViewClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtnom: TextView = itemView.findViewById(R.id.textdashboard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewClass {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.template_dashboard, parent, false)
        return MyViewClass(itemView)
    }

    override fun onBindViewHolder(holder: MyViewClass, position: Int) {
        holder.txtnom.text = filteredNames[position].toString()

        holder.itemView.setOnClickListener {
            val intent = Intent(context, FilterCategoryActivity::class.java)
            intent.putExtra("category", filteredNames[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return filteredNames.size
    }

    fun setData(newData: Array<String>) {
        originalNames = newData
        filteredNames.clear()
        filteredNames.addAll(newData)
        notifyDataSetChanged()
    }}
