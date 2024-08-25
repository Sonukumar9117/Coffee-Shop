package com.example.coffeshop.activity

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.example.coffeshop.databinding.ActivityDetailedBinding
import com.example.coffeshop.helper.ManagmentCart
import com.example.coffeshop.model.ItemsModel

class DetailedActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailedBinding
    private var item:ItemsModel?=null
    lateinit var managmentCart: ManagmentCart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bundle()
    }

    private fun bundle() {
        binding.apply{
            item=intent.getParcelableExtra("item")
            titleTxt.text=item?.title
            descText.text=item?.description
            priceText.text=item?.price.toString()
            ratingBar.rating= item?.rating?.toFloat() ?: 0.0f
            addToCart.setOnClickListener {

                item?.numberInCart=Integer.valueOf(
                    numberItemText.text.toString().trim()
                )
                managmentCart.insertItems(item)
            }
            backBtn.setOnClickListener{
                startActivity(Intent(this@DetailedActivity,MainActivity::class.java))
            }
        }
    }
}