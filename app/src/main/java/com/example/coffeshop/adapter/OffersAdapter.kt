package com.example.coffeshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeshop.databinding.ViewholderOfferBinding
import com.example.coffeshop.model.ItemsModel

class OffersAdapter(val items: MutableList<ItemsModel>): RecyclerView.Adapter<OffersAdapter.ViewHolder>() {
    private var context: Context?=null
    class ViewHolder(val binding: ViewholderOfferBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersAdapter.ViewHolder {
        context=parent.context
        val binding=ViewholderOfferBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OffersAdapter.ViewHolder, position: Int) {
        val item=items[position]
        holder.binding.titleText.text=item.title
        holder.binding.priceText.text=item.price.toString()
        Glide.with(holder.itemView.context).load(item.picUrl[0]).into(holder.binding.pic)
        holder.itemView.setOnClickListener {
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}