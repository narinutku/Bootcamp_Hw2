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
            return if (paymentType.paymentDayofPeriod==null){
                paymentTypeOperation.addPaymentType(paymentType)

            } else if(!periodIsOkey(paymentType)){
                -1L
            } else{
                paymentTypeOperation.addPaymentType(paymentType)

            }


        }
        fun getPaymentType(context: Context,id:Int?):ArrayList<PaymentType>{
            return PaymentTypeOperation(context).getPaymentTypes(id)
        }
        fun updatePaymentType(context: Context, paymentType: PaymentType, backToMenu: () -> Unit){


            if(periodIsOkey(paymentType)){
                val paymentTypeOperation= PaymentTypeOperation(context)
                paymentTypeOperation.updatePaymentType(paymentType)
                backToMenu()
            }
            else{
                Toast.makeText(
                    context,
                    "Seçilen ödeme periyod günü periyot ile uyumsuzdur lütfen tekrar deneyiniz",
                    Toast.LENGTH_LONG
                ).show()
            }

        }
        fun removePaymentType(context: Context,id:Int){
            PaymentOperation(context).deletePaymentType(id)
            PaymentTypeOperation(context).deletePaymentType(id)
        }
        fun periodIsOkey(paymentType: PaymentType):Boolean{
            return !(paymentType.paymentDayofPeriod!!>paymentType.paymentPeriod!!.getLenghtofPeriod()|| paymentType.paymentDayofPeriod!! <1)

        }
    }
}