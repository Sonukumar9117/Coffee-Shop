package com.example.coffeshop.activity
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeshop.adapter.CartAdapter
import com.example.coffeshop.databinding.ActivityCartBinding
import com.example.coffeshop.helper.ChangeNumberItemsListener
import com.example.coffeshop.helper.ManagmentCart
import com.razorpay.Checkout
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener
import org.json.JSONObject

class CartActivity : BaseActivity() , PaymentResultWithDataListener {
    lateinit var binding: ActivityCartBinding
    lateinit var managment: ManagmentCart
    var tax=0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managment=ManagmentCart(this)
        calculateCart()
        setVariable()
        initCartList()
        binding.paymentBtn.setOnClickListener {
            initPayment()
        }
    }

    private fun initCartList() {
        with(binding){
            cartView.layoutManager=LinearLayoutManager(this@CartActivity,LinearLayoutManager.VERTICAL,false)
            cartView.adapter= CartAdapter(managment.getListCart(),this@CartActivity,object :
                ChangeNumberItemsListener {
                override fun onChanged() {
                    calculateCart()
                }

            })
        }
    }

    private fun setVariable() {
        binding.backBtnCart.setOnClickListener {
            finish()
        }
    }

    private fun calculateCart() {
        val percentTax=0.02
        val delivery=10
        tax= Math.round(managment.getTotalFee()*percentTax).toDouble()
        val total=Math.round(managment.getTotalFee()+tax+delivery).toDouble()
        val itemTotal=Math.round(managment.getTotalFee()*100)/100
        with(binding){
           totalFee.text="Rs $itemTotal"
            totalTax.text="Rs $tax"
            deliveryPrice.text="Rs $delivery"
            totalCost.text="Rs $total"
        }

    }
    private fun initPayment(){
        val activity: Activity = this
        Checkout.preload(applicationContext)
        val co=Checkout()
        co.setKeyID("rzp_test_Nqy8gmPWtyPySL")

        try {
            val options = JSONObject()
            options.put("name","Razorpay Corp")
            options.put("description","Demoing Charges")
            //You can omit the image option to fetch the image from the dashboard
            options.put("image","http://example.com/image/rzp.jpg")
            options.put("theme.color", "#3399cc");
            options.put("currency","INR");
//            options.put("order_id", "order_DBJOWzybf0sJbb");
            var payAmount = Math.round(managment.getTotalFee()+tax+10).toDouble()*100
            options.put("amount","$payAmount")//pass amount in currency subunits

            val retryObj =  JSONObject()
            retryObj.put("enabled", true)
            retryObj.put("max_count", 4)
            options.put("retry", retryObj)

            val prefill = JSONObject()
            prefill.put("email","krsonu1791@gmail.com")
            prefill.put("contact","9117773191")
            options.put("prefill",prefill)
            co.open(activity,options)
        }catch (e: Exception){
            Toast.makeText(activity,"Error in payment: "+ e.message,Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    override fun onPaymentSuccess(p0: String?, p1: PaymentData?) {
        Toast.makeText(this, "Success:${p1}", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this,MainActivity::class.java))
    }

    override fun onPaymentError(p0: Int, p1: String?, p2: PaymentData?) {
        Toast.makeText(this, "Erro:${p1}", Toast.LENGTH_SHORT).show()
    }
}