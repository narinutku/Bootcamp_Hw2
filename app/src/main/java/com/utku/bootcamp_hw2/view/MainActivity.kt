package com.utku.bootcamp_hw2.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.utku.bootcamp_hw2.databinding.ActivityMainBinding
import com.utku.bootcamp_hw2.logic.PaymentLogic
import com.utku.bootcamp_hw2.logic.PaymentTypeLogic
import com.utku.bootcamp_hw2.model.PaymentType
import com.utku.bootcamp_hw2.model.Period

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var list = ArrayList<PaymentType>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView()

    }

    private fun setView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAdapter()
        binding.btnAddNewPaymentType.setOnClickListener {
            val intent = Intent(this, AddPaymentTypeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setAdapter() {
        val lm = LinearLayoutManager(this)
        lm.orientation = LinearLayoutManager.VERTICAL
        binding.rcvPaymentType.layoutManager = lm
        getPaymentTypeList()
        binding.rcvPaymentType.adapter =
            PaymentTypeAdapter(this, list, ::itemClickDetail, ::itemClickAddPayment)
    }

    private fun getPaymentTypeList() {
        list = PaymentTypeLogic.getPaymentType(this, null)
    }

    private fun itemClickAddPayment(position: Int) {
        val intent = Intent(this, AddPaymentActivity::class.java)
        intent.putExtra("fk",list.get(position).id)
        startActivity(intent)

        finish()

    }

    private fun itemClickDetail(position: Int) {
        val intent = Intent(this, PaymentTypeDetailActivity::class.java)
        intent.putExtra("fk",list.get(position).id)
        startActivity(intent)

        finish()
    }

}