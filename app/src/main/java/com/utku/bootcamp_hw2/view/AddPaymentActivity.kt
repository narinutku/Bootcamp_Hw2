package com.utku.bootcamp_hw2.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Toast
import com.utku.bootcamp_hw2.databinding.ActivityAddPaymentBinding
import com.utku.bootcamp_hw2.logic.PaymentLogic

import com.utku.bootcamp_hw2.model.Payment
import java.text.SimpleDateFormat


import java.util.*

class AddPaymentActivity : AppCompatActivity() {
    var calendar = Calendar.getInstance()
    var date = String()

    lateinit var binding: ActivityAddPaymentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView()
    }

    private fun setView() {
        binding = ActivityAddPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setDatePicker()
        binding.btnPay.setOnClickListener {

            addPayment()
        }
        binding.imBtnBack.setOnClickListener { backToMainPage() }
    }

    private fun addPayment() {
        if (binding.eTPaymentAmount.text == null) {
            Toast.makeText(this, "Ödeme Alanı boş bırakılamaz", Toast.LENGTH_LONG).show()
        } else {
            val payment = Payment()
            payment.paymentTypeId = intent.getIntExtra("fk", 0)
            payment.date = date


            payment.amount = binding.eTPaymentAmount.text.toString().toFloat()
            val record = PaymentLogic.addPayment(this, payment)
            if (record > 0) {
                Toast.makeText(this, "İşlem başarılı", Toast.LENGTH_LONG).show()
                backToMainPage()
            }
        }
    }



    private fun setDatePicker() {
        binding.datePicker.init(
            calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ) { view, year, month, day ->
            val month = month + 1
            date = formatDate(day.toString(), month.toString(), year.toString())
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)

        }
        binding.datePicker.maxDate = System.currentTimeMillis()
    }

    fun formatDate(day: String, month: String, year: String): String {
        return "$day.$month.$year"
    }

    private fun backToMainPage() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}