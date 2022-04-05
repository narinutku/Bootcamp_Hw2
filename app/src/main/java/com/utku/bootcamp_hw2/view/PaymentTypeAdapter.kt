package com.utku.bootcamp_hw2.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utku.bootcamp_hw2.R
import com.utku.bootcamp_hw2.model.PaymentType

class PaymentTypeAdapter(
    val context: Context,
    var list: ArrayList<PaymentType>,
    val itemClickDetail: (position: Int) -> Unit,
    val itemClickAddPayment: (position: Int) -> Unit
) : RecyclerView.Adapter<PaymentTypeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentTypeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rcv_payments, parent, false)

        return PaymentTypeViewHolder(view, itemClickDetail,itemClickAddPayment)
    }

    override fun onBindViewHolder(holder: PaymentTypeViewHolder, position: Int) {
        holder.bindData(list.get(position))
    }

    override fun getItemCount(): Int {
        return list.size
    }
}