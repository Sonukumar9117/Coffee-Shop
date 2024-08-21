package com.example.coffeshop.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeshop.adapter.CategoryAdapter
import com.example.coffeshop.adapter.OffersAdapter
import com.example.coffeshop.adapter.PopularAdapter
import com.example.coffeshop.databinding.ActivityMainBinding
import com.example.coffeshop.model.MainViewModel

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel:MainViewModel=MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initCategory()
        initPopular()
        initOffer()
    }

    private fun initOffer() {
        binding.progressBarOffer.visibility= View.VISIBLE
        viewModel.loadOffer()
        viewModel.offer.observe(this, Observer{
            binding.recyclerViewOffer.layoutManager=LinearLayoutManager(
                this@MainActivity,LinearLayoutManager.HORIZONTAL,false
            )
            binding.recyclerViewOffer.adapter= OffersAdapter(it)
            binding.progressBarOffer.visibility=View.GONE
        })
        viewModel.loadOffer()
    }

    private fun initPopular() {
        binding.progressBarPopular.visibility= View.VISIBLE
      viewModel.popular.observe(this, Observer{
          binding.recyclerViewPopular.layoutManager=LinearLayoutManager(
              this@MainActivity,LinearLayoutManager.HORIZONTAL,false
          )
          binding.recyclerViewPopular.adapter= PopularAdapter(it)
          binding.progressBarPopular.visibility=View.GONE
      })
        viewModel.loadPopular()
    }

    private fun initCategory(){
        binding.progressBarCategory.visibility= View.VISIBLE
        viewModel.loadCategory()
        viewModel.category.observe(this, Observer{
            binding.recyclerViewCategory.layoutManager=LinearLayoutManager(
                this@MainActivity,LinearLayoutManager.HORIZONTAL,false
            )
            binding.recyclerViewCategory.adapter=CategoryAdapter(it)
            binding.progressBarCategory.visibility=View.GONE
        })
        viewModel.loadCategory()
    }

}