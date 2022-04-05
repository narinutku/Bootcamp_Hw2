package com.utku.bootcamp_hw2.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utku.bootcamp_hw2.R
import com.utku.bootcamp_hw2.model.Payment
import com.utku.bootcamp_hw2.model.PaymentType

class PaymentDetailAdapter(val context: Context,
                           var list: ArrayList<Payment>,
                           val itemClickDetail: (position: Int) -> Unit,

) : RecyclerView.Adapter<PaymentDetailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentDetailViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rcv_payment_detail, parent, false)

        return PaymentDetailViewHolder(view, itemClickDetail)
    }

    override fun onBindViewHolder(holder: PaymentDetailViewHolder, position: Int) {
      holder.bindData(list.get(position))
    }

    override fun getItemCount(): Int {
    return list.size
    }
}