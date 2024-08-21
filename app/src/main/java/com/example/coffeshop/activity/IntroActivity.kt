package com.example.coffeshop.activity

import android.content.Intent
import android.os.Bundle
import com.example.coffeshop.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity(){
   private lateinit var binding: ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}