package com.utku.bootcamp_hw2.logic

import android.content.Context
import com.utku.bootcamp_hw2.DAL.PaymentOperation
import com.utku.bootcamp_hw2.DAL.PaymentTypeOperation
import com.utku.bootcamp_hw2.model.Payment
import com.utku.bootcamp_hw2.model.PaymentType
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class PaymentLogic {
    companion object {
        var payment = Payment()
        fun addPayment(context: Context, payment: Payment): Long {
            val paymentOperation = PaymentOperation(context)
           this. payment=payment
            checkDate(payment.date)
            return paymentOperation.addPayment(this.payment)

        }

        private fun checkDate(date:String) {
            if (date == "" || date == null) {
                val currentDate: String =
                    SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date())
                payment.date = currentDate
            }
        }

        fun getPayments(context: Context, id: Int?, fk: Int?): ArrayList<Payment> {
            return PaymentOperation(context).getPaymentTypes(id, fk)
        }

        fun updatePayment(context: Context, payment: Payment) {
            val paymentOperation = PaymentOperation(context)
            paymentOperation.updatePaymentType(payment)
        }
        fun removePayment(context: Context,id: Int){
            PaymentOperation(context).deletePaymentType(id)
        }
    }

}