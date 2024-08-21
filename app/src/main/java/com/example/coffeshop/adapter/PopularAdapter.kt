package com.example.coffeshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeshop.databinding.ViewholderPopularBinding
import com.example.coffeshop.model.ItemsModel

class PopularAdapter(val items: MutableList<ItemsModel>) : RecyclerView.Adapter<PopularAdapter.ViewHolder>() {
    private var context:Context?=null
    class ViewHolder (val binding: ViewholderPopularBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularAdapter.ViewHolder {
        context=parent.context
        val binding=ViewholderPopularBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularAdapter.ViewHolder, position: Int) {
        val item=items[position]
        /*
        var title: String,
    var description: String,
    var picUrl:ArrayList<String> = ArrayList(),
    var price: Double=0.0,
    var rating: Double=0.0,
    var numberIntCart:Int=0,
    var extra: String =""
         */
        holder.binding.titleTxt.text=item.title
        holder.binding.priceTxt.text=item.price.toString()
        holder.binding.ratingBar.rating=item.rating.toFloat()
        holder.binding.extraTxt.text=item.extra
        Glide.with(holder.binding.popularImage).load(item.picUrl[0]).into(holder.binding.popularImage)
        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}