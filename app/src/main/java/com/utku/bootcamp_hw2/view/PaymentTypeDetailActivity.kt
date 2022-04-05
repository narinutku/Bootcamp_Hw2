package com.utku.bootcamp_hw2.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

import com.utku.bootcamp_hw2.databinding.ActivityPaymentTypeDetailBinding
import com.utku.bootcamp_hw2.logic.PaymentLogic
import com.utku.bootcamp_hw2.logic.PaymentTypeLogic
import com.utku.bootcamp_hw2.model.Payment
import com.utku.bootcamp_hw2.model.PaymentType

class PaymentTypeDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityPaymentTypeDetailBinding
    var fk: Int = 0
    var list = ArrayList<Payment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView()
    }

    private fun setView() {
        binding = ActivityPaymentTypeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fk = intent.getIntExtra("fk",0)
        binding.imBtnBack.setOnClickListener { backToMainPage() }
        setAdapter()
        binding.btnUpdatePaymentType.setOnClickListener {
            val intent = Intent(this, AddPaymentTypeActivity::class.java)
            intent.putExtra("fk", fk)
            startActivity(intent)
            finish()
        }

    }

    private fun setAdapter() {
        val lm = LinearLayoutManager(this)
        lm.orientation = LinearLayoutManager.VERTICAL
        binding.rcvPaymentType.layoutManager = lm
        getPaymentList()
        binding.rcvPaymentType.adapter =
            PaymentDetailAdapter(this, list, ::itemClickRemove)
    }

    private fun getPaymentList() {

        list = PaymentLogic.getPayments(this, null, fk)
    }

    fun itemClickRemove(id: Int) {

    }

    private fun backToMainPage() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}