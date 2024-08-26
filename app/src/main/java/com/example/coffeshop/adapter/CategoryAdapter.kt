package com.example.coffeshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeshop.R
import com.example.coffeshop.databinding.ViewholderCategoryBinding
import com.example.coffeshop.model.CategoryModel

class CategoryAdapter (val items:MutableList<CategoryModel>): RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){
    private lateinit var context: Context
    private var selectedItemPosition: Int = -1
    private var lastItemSelectedPosition: Int = -1
    inner class ViewHolder (val binding:ViewholderCategoryBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
           context=parent.context
        val binding=ViewholderCategoryBinding.inflate(LayoutInflater.from(context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.titleCat.text = item.title
        holder.binding.root.setOnClickListener {
            lastItemSelectedPosition = selectedItemPosition
            selectedItemPosition =holder.adapterPosition
            notifyItemChanged(lastItemSelectedPosition)
            notifyItemChanged(selectedItemPosition)
            if(selectedItemPosition==position){
                holder.binding.titleCat.setBackgroundResource(R.drawable.orange_bg)
            }else{
                holder.binding.titleCat.setBackgroundResource(R.drawable.edit_text_style)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size

    }
}