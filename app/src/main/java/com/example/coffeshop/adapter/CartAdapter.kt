package com.example.coffeshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.coffeshop.databinding.ViewholderCartBinding
import com.example.coffeshop.helper.ChangeNumberItemsListener
import com.example.coffeshop.helper.ManagmentCart
import com.example.coffeshop.model.ItemsModel

class CartAdapter(
    private val listItemSelected: ArrayList<ItemsModel>,
    context: Context, var changeNumberItemsListener: ChangeNumberItemsListener?=null
) : RecyclerView.Adapter<CartAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: ViewholderCartBinding):RecyclerView.ViewHolder(binding.root)

    private val managmentCart= ManagmentCart(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {
       val binding=ViewholderCartBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listItemSelected.size
    }

    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        val item=listItemSelected[position]
        holder.binding.titleCartList.text=item.title
        holder.binding.priceEachItem.text="Rs ${item.price.toString()}"
        holder.binding.totalEachItem.text="Rs ${Math.round(item.numberInCart*item.price)}"
        holder.binding.countItemInCart.text=item.numberInCart.toString()
        Glide.with(holder.itemView.context)
            .load(item.picUrl[0]).apply (
                RequestOptions().transform(CenterCrop())
            )
            .into(holder.binding.cartPic)
        holder.binding.cartViewholderPlus.setOnClickListener {
            managmentCart.plusItem(listItemSelected,position,object :ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }
            })
        }
        holder.binding.cartViewholderMinus.setOnClickListener {
            managmentCart.minusItem(listItemSelected,position, object :ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }
            })
        }
    }
}