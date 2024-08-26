package com.example.coffeshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeshop.R
import com.example.coffeshop.databinding.ViewholderSizeBinding

class SizeAdapter (private val items:MutableList<String>) : RecyclerView.Adapter<SizeAdapter.ViewHolder>(){
    private var selectedPosition=-1
    private var lastSelectedPosition=-1
    lateinit var context: Context
   inner class ViewHolder (val binding: ViewholderSizeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context=parent.context
        val binding=ViewholderSizeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.root.setOnClickListener {
            lastSelectedPosition=selectedPosition
            selectedPosition=holder.adapterPosition
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }
        if(selectedPosition==position){
            holder.binding.pic.setBackgroundResource(R.drawable.orange_bg)

        }else{
            holder.binding.pic.setBackgroundResource(R.drawable.size_bg)
        }
        val imageSize= when(position){
            0->45.dpToPx(context)
            1->50.dpToPx(context)
            2->55.dpToPx(context)
            3->65.dpToPx(context)
            else -> {70.dpToPx(context)}
        }
        val layoutParams=holder.binding.pic.layoutParams
        layoutParams.height=imageSize
        layoutParams.width=imageSize
        holder.binding.pic.layoutParams=layoutParams
    }
    private fun Int.dpToPx(context: Context): Int {
        return (this * context.resources.displayMetrics.density).toInt()
    }
}