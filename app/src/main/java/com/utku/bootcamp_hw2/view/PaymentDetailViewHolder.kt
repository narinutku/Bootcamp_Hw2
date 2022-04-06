package com.utku.bootcamp_hw2.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.utku.bootcamp_hw2.R
import com.utku.bootcamp_hw2.model.Payment
import kotlinx.android.synthetic.main.rcv_payment_detail.view.*

class PaymentDetailViewHolder  (itemView: View,
                                itemClickDetail: (position: Int) -> Unit,
) : RecyclerView.ViewHolder(itemView) {
    var tvDate:TextView
    var tvAmount:TextView
    init {
        tvDate=itemView.findViewById(R.id.tvPaymentDay)
        tvAmount=itemView.findViewById(R.id.tvPaymentAmount)
        itemView.setOnClickListener {
            itemClickDetail(absoluteAdapterPosition)
        }
    }
    fun bindData(payment: Payment){
        itemView.tvPaymentDay.text="Ödeme tarihi: "+payment.date
        itemView.tvPaymentAmount.text="Ödenen Miktar: "+ payment.amount.toString()
    }

}