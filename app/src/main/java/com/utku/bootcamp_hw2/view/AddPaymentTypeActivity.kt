package com.utku.bootcamp_hw2.view

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.utku.bootcamp_hw2.databinding.ActivityAddPaymentTypeBinding
import com.utku.bootcamp_hw2.logic.PaymentTypeLogic
import com.utku.bootcamp_hw2.model.PaymentType
import com.utku.bootcamp_hw2.model.Period

class AddPaymentTypeActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddPaymentTypeBinding
    private var fk: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView()
    }

    private fun setView() {
        binding = ActivityAddPaymentTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fk = intent.getIntExtra("fk", 0)
        if (fk != 0) {
            getData(fk)
            binding.btnDeletePaymentType.visibility = View.VISIBLE
        }

        val adapter = ArrayAdapter(
            this,
            R.layout.simple_spinner_item, arrayListOf(Period.AYLIK, Period.HAFTALIK, Period.YILLIK)
        )
        binding.spinner.adapter = adapter
        binding.btnAddPaymentType.setOnClickListener {
            if(intent.getBooleanExtra("isUpdate",false)){
                updatePaymentType()
            }
            else{
                addPaymentType()
            }

        }
        binding.imBtnBack.setOnClickListener { backToMainPage() }
        binding.btnDeletePaymentType.setOnClickListener {
            showAlert()
        }
    }

    private fun updatePaymentType() {

        if(binding.eTTitle.text.toString()==""){
            Toast.makeText(
                this,
                "Ödeme Başlığı alanı boş bırakılamaz",
                Toast.LENGTH_LONG
            ).show()

        }
        else{
            val paymentType = getPaymentType()
            paymentType.id=fk
            PaymentTypeLogic.updatePaymentType(this, paymentType,::backToMainPage)

        }
    }

    private fun getPaymentType(): PaymentType {
        val paymentType = PaymentType()
        paymentType.title = binding.eTTitle.text.toString()
        paymentType.paymentDayofPeriod = try { binding.eTDayofPeriod.text.toString().toInt() } catch (e: NumberFormatException) { null }

        paymentType.paymentPeriod = binding.spinner.selectedItem as Period?
        return paymentType
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Dikkat")
        builder.setMessage("Bu ödeme tipini silmek istediğinizden emin misiniz ?")

        builder.setPositiveButton("Sil") { dialog, which ->

            PaymentTypeLogic.removePaymentType(this, fk)
            backToMainPage()

        }

        builder.setNegativeButton("İptal") { dialog, which ->
            dialog.dismiss()
        }

        builder.show()
    }

    private fun addPaymentType() {

        if(binding.eTTitle.text.toString()==""){
            Toast.makeText(
                this,
                "Ödeme Başlığı alanı boş bırakılamaz",
                Toast.LENGTH_LONG
            ).show()

        }
        else{

            val paymentType = getPaymentType()
            val record = PaymentTypeLogic.addPaymentType(this, paymentType)
            isSucces(record)
        }
    }

    private fun isSucces(record: Long) {
        if (record > 0) {
            Toast.makeText(this, "İşlem başarılı", Toast.LENGTH_LONG).show()
            backToMainPage()
        } else if (record == -1L) {
            Toast.makeText(
                this,
                "Seçilen ödeme periyod günü periyot ile uyumsuzdur lütfen tekrar deneyiniz",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun backToMainPage() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun getData(fk: Int) {
        //tek veri döneceği için direk sıfırıncı index alındı
        val payment = PaymentTypeLogic.getPaymentType(this, fk)[0]
        binding.eTTitle.setText(payment.title)
        binding.eTDayofPeriod.setText(payment.paymentDayofPeriod.toString())

    }
}