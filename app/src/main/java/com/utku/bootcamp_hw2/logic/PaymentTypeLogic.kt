package com.utku.bootcamp_hw2.logic

import android.content.Context
import android.widget.Toast
import com.utku.bootcamp_hw2.DAL.PaymentOperation
import com.utku.bootcamp_hw2.DAL.PaymentTypeOperation
import com.utku.bootcamp_hw2.model.PaymentType

class PaymentTypeLogic {
    companion object{
        fun addPaymentType(context: Context, paymentType: PaymentType):Long{
            val paymentTypeOperation= PaymentTypeOperation(context)
            if(paymentType.paymentDayofPeriod!!>paymentType.paymentPeriod!!.getLenghtofPeriod()){
                return -1L
            }
           return paymentTypeOperation.addPaymentType(paymentType)

        }
        fun getPaymentType(context: Context,id:Int?):ArrayList<PaymentType>{
            return PaymentTypeOperation(context).getPaymentTypes(id)
        }
        fun updatePaymentType(context: Context,paymentType: PaymentType){
            val paymentTypeOperation= PaymentTypeOperation(context)
            paymentTypeOperation.updatePaymentType(paymentType)
        }
        fun removePaymentType(context: Context,id:Int){
            PaymentOperation(context).deletePaymentType(id)
            PaymentTypeOperation(context).deletePaymentType(id)
        }
    }
}