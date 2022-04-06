package com.utku.bootcamp_hw2.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager

import com.utku.bootcamp_hw2.databinding.ActivityPaymentTypeDetailBinding
import com.utku.bootcamp_hw2.logic.PaymentLogic
import com.utku.bootcamp_hw2.model.Payment

class PaymentTypeDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityPaymentTypeDetailBinding
    var fk: Int = 0
    var list = ArrayList<Payment>()
    lateinit var adapter: PaymentDetailAdapter
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
            intent.putExtra("isUpdate",true)
            startActivity(intent)
            finish()
        }
        binding.btnAddPayment.setOnClickListener {
            val intent = Intent(this, AddPaymentActivity::class.java)
            intent.putExtra("fk",fk)
            startActivity(intent)

            finish()
        }

    }

    private fun setAdapter() {
        val lm = LinearLayoutManager(this)
        lm.orientation = LinearLayoutManager.VERTICAL

        getPaymentList()
        adapter =
            PaymentDetailAdapter(this, list,::removeRecord)
        binding.rcvPaymentType.layoutManager = lm
        binding.rcvPaymentType.adapter=adapter
    }

    private fun getPaymentList() {

        list = PaymentLogic.getPayments(this, null, fk)
    }
    private fun removeRecord(position:Int){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Dikkat")
        builder.setMessage("Bu ödemeyi istediğinizden emin misiniz ?")

        builder.setPositiveButton("Sil") { dialog, which ->

            PaymentLogic.removePayment(this,list.get(position).id)
            getPaymentList()
            adapter.list=list
            adapter.notifyDataSetChanged()

        }

        builder.setNegativeButton("İptal") { dialog, which ->
            dialog.dismiss()
        }

        builder.show()

    }



    private fun backToMainPage() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}