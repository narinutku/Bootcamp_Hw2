package com.utku.bootcamp_hw2.DAL

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.utku.bootcamp_hw2.model.Payment
import com.utku.bootcamp_hw2.model.Period

class PaymentOperation(context: Context) {
    var paymentDatabase: SQLiteDatabase? = null

    private val PAYMENTTYPEID = "PaymentId"
    private val AMOUNT = "Amount"
    private val DATE = "Date"
    private val TABLENAME = "Payment"
    var dbOpenHelper: DatabaseOpenHelper

    init {
        dbOpenHelper = DatabaseOpenHelper(context, "PaymentApp", null, 1)
    }

    private fun open() {

        paymentDatabase = dbOpenHelper.writableDatabase
    }

    private fun close() {
        if (paymentDatabase != null && paymentDatabase!!.isOpen) {
            paymentDatabase!!.close()
        }
    }

    fun addPayment(payment: Payment): Long {
        val contentValues = ContentValues()
        contentValues.put(AMOUNT, payment.amount)
        contentValues.put(DATE, payment.date)
        contentValues.put(PAYMENTTYPEID, payment.paymentTypeId)
        open()
        val record = paymentDatabase!!.insert(TABLENAME, null, contentValues)
        close()
        return record

    }

    fun updatePaymentType(payment: Payment) {

        val contentValues = ContentValues()
        contentValues.put(AMOUNT, payment.amount)
        contentValues.put(DATE, payment.date)

        open()
        paymentDatabase!!.update(
            TABLENAME,
            contentValues,
            "Id = ?",
            arrayOf(payment.id.toString())
        )
        close()

    }

    fun deletePaymentType(id: Int) {

        open()
        paymentDatabase!!.delete(TABLENAME, "$PAYMENTTYPEID = ?", arrayOf(id.toString()))
        close()
    }

    private fun getAllPaymentTypes(): Cursor {
        val sorgu = "Select * from $TABLENAME"
        return paymentDatabase!!.rawQuery(sorgu, null)
    }

    private fun getPaymentTypeById(id: Int): Cursor {
        val sorgu = "Select * from $TABLENAME where Id=?"
        return paymentDatabase!!.rawQuery(sorgu, arrayOf(id.toString()))
    }

    private fun getPaymentTypeByFK(id: Int): Cursor {
        val sorgu = "Select * from $TABLENAME where PaymentId = ?"
        return paymentDatabase!!.rawQuery(sorgu, arrayOf(id.toString()))
    }

    @SuppressLint("Range")
    fun getPaymentTypes(id: Int?, fk: Int?): ArrayList<Payment> {
        open()
        val paymentList = ArrayList<Payment>()
        var payment: Payment
        val cursor: Cursor = getCursor(fk, id)


        if (cursor.moveToFirst()) {
            do {
                payment = Payment()
                payment.id = cursor.getInt(0)
                payment.amount = cursor.getFloat(cursor.getColumnIndex(AMOUNT))
                payment.date = cursor.getString(cursor.getColumnIndex(DATE))
                payment.paymentTypeId = cursor.getInt(cursor.getColumnIndex(PAYMENTTYPEID))
                paymentList.add(payment)
            } while (cursor.moveToNext())

        }
        close()
        return paymentList
    }

    private fun getCursor(fk: Int?, id: Int?): Cursor {
        return when {
            fk != null -> {
                getPaymentTypeByFK(fk)
            }
            id != null -> {
                getPaymentTypeById(id)
            }
            else -> {
                getAllPaymentTypes()
            }
        }
    }
}