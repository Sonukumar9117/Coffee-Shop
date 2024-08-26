package com.example.coffeshop.activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.coffeshop.adapter.SizeAdapter
import com.example.coffeshop.databinding.ActivityDetailedBinding
import com.example.coffeshop.helper.ManagmentCart
import com.example.coffeshop.model.ItemsModel

class DetailedActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailedBinding
    private var item: ItemsModel? = null
    private lateinit var managmentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        managmentCart = ManagmentCart(this)
        bundle()
        initSizeList()
        setupCartButtons()
        binding.addToCart.setOnClickListener {

            startActivity(Intent(this, CartActivity::class.java))
            finish()
        }
    }
    private fun initSizeList() {
        val sizeList = arrayListOf("1", "2", "3", "4")
        binding.sizeList.adapter = SizeAdapter(sizeList)
        binding.sizeList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val colorList = arrayListOf<String>()
        item?.picUrl?.let { picUrls ->
            for (imageUrl in picUrls) {
                colorList.add(imageUrl)
            }
            Glide.with(this).load(colorList[0])
                .apply(RequestOptions.bitmapTransform(RoundedCorners(100)))
                .into(binding.shapeableImageView)
        }
    }
    private fun bundle() {
        binding.apply {
            item = intent.getParcelableExtra("object")
            titleTxt.text = item?.title
            descText.text = item?.description
            priceText.text = item?.price.toString()
            ratingBar.rating = item?.rating?.toFloat() ?: 0.0f
            addToCart.setOnClickListener {
                item?.numberInCart = numberItemText.text.toString().trim().toInt()
                managmentCart.insertItems(item)
            }
            backBtn.setOnClickListener {
                startActivity(Intent(this@DetailedActivity, MainActivity::class.java))
                finish()
            }
        }
    }
    private fun setupCartButtons() {
        binding.apply {
            plusCart.setOnClickListener {
                item?.let {
                    it.numberInCart = (it.numberInCart ?: 0) + 1
                    numberItemText.text = it.numberInCart.toString()
                    // Optionally update the cart
                    managmentCart.insertItems(it)
                }
            }
            minusCart.setOnClickListener {
                item?.let {
                    if (it.numberInCart ?: 0 > 1) {
                        it.numberInCart = (it.numberInCart ?: 0) - 1
                        numberItemText.text = it.numberInCart.toString()
                        // Optionally update the cart
                        managmentCart.insertItems(it)
                    }
                }
            }
        }
    }
}
