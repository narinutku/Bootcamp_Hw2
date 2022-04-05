package com.utku.bootcamp_hw2.view

import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.utku.bootcamp_hw2.R
import com.utku.bootcamp_hw2.model.PaymentType
import kotlinx.android.synthetic.main.rcv_payments.view.*


class PaymentTypeViewHolder(
    itemView: View,
    itemClickDetail: (position: Int) -> Unit,

    itemClickAddPayment: (position: Int) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    var btnAddPayment: Button
    var categoryId=0

    init {

        btnAddPayment = itemView.findViewById(R.id.btnAddPayment)


   btnAddPayment.setOnClickListener {
       itemClickAddPayment(absoluteAdapterPosition)
   }
        itemView.setOnClickListener { itemClickDetail(absoluteAdapterPosition) }
    }
    fun bindData( paymentType: PaymentType){
        itemView.tvPaymentTitle.text="Ödeme Başlığı: "+paymentType.title
        itemView.tvPaymentDayOfPeriod.text="Ödeme Periyod Günü: "+paymentType.paymentDayofPeriod.toString()
        itemView.tvPaymentPeriod.text="Ödeme Periyodu: "+paymentType.paymentPeriod.toString()
    }
}