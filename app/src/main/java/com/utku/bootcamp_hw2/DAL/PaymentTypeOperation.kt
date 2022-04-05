package com.utku.bootcamp_hw2.DAL

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.utku.bootcamp_hw2.model.PaymentType
import com.utku.bootcamp_hw2.model.Period

class PaymentTypeOperation(context: Context) {
    var paymentTypeDatabase: SQLiteDatabase? = null
    private val PAYMENTPERIOD = "PaymentPeriod"
    private val PAYMENTDAYOFPERIOD = "PaymentDayofPeriod"
    private val TITLE = "Title"
    private val TABLENAME = "PaymentType"
    var dbOpenHelper: DatabaseOpenHelper

    init {
        dbOpenHelper = DatabaseOpenHelper(context, "PaymentApp", null, 1)
    }

    private fun open() {

        paymentTypeDatabase = dbOpenHelper.writableDatabase
    }

    private fun close() {
        if (paymentTypeDatabase != null && paymentTypeDatabase!!.isOpen) {
            paymentTypeDatabase!!.close()
        }
    }

    fun addPaymentType(paymentType: PaymentType): Long {
        val contentValues = ContentValues()
        contentValues.put(PAYMENTPERIOD, paymentType.paymentPeriod.toString())
        contentValues.put(PAYMENTDAYOFPERIOD, paymentType.paymentDayofPeriod)
        contentValues.put(TITLE, paymentType.title)
        open()
        val record = paymentTypeDatabase!!.insert(TABLENAME, null, contentValues)
        close()
        return record

    }

    fun updatePaymentType(paymentType: PaymentType) {

        val contentValues = ContentValues()
        contentValues.put(PAYMENTPERIOD, paymentType.paymentPeriod.toString())
        contentValues.put(PAYMENTDAYOFPERIOD, paymentType.paymentDayofPeriod)
        contentValues.put(TITLE, paymentType.title)
        open()
        paymentTypeDatabase!!.update(
            TABLENAME,
            contentValues,
            "Id = ?",
            arrayOf(paymentType.id.toString())
        )
        close()

    }

    fun deletePaymentType(id: Int) {

        open()
        paymentTypeDatabase!!.delete(TABLENAME, "Id = ?", arrayOf(id.toString()))
        close()
    }

    private fun getAllPaymentTypes(): Cursor {
        val sorgu = "Select * from PaymentType"
        return paymentTypeDatabase!!.rawQuery(sorgu, null)
    }

    private fun getPaymentTypeById(id: Int): Cursor {
        val sorgu = "Select * from PaymentType where Id=?"
        return paymentTypeDatabase!!.rawQuery(sorgu, arrayOf(id.toString()))
    }


    @SuppressLint("Range")
    fun getPaymentTypes(id: Int?): ArrayList<PaymentType> {
        open()
        val paymentTypeList = ArrayList<PaymentType>()
        var paymentType: PaymentType
        val cursor: Cursor = if (id != null) {
            getPaymentTypeById(id)
        } else {
            getAllPaymentTypes()
        }


        if (cursor.moveToFirst()) {
            do {
                paymentType = PaymentType()
                paymentType.id = cursor.getInt(0)

                paymentType.paymentPeriod =
                    Period.valueOf(cursor.getString(cursor.getColumnIndex(PAYMENTPERIOD)))
                paymentType.paymentDayofPeriod =
                    cursor.getInt(cursor.getColumnIndex(PAYMENTDAYOFPERIOD))
                paymentType.title = cursor.getString(cursor.getColumnIndex(TITLE))
                paymentTypeList.add(paymentType)
            } while (cursor.moveToNext())

        }
        close()
        return paymentTypeList
    }

}