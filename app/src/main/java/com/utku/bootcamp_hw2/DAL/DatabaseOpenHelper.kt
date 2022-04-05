package com.utku.bootcamp_hw2.DAL

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseOpenHelper(context: Context, dbName:String, factory: SQLiteDatabase.CursorFactory?, version:Int):
    SQLiteOpenHelper(context,dbName, factory,version) {
    override fun onCreate(p0: SQLiteDatabase) {
        var sorgu="CREATE TABLE PaymentType(Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Title TEXT,PaymentPeriod TEXT,PaymentDayofPeriod INTEGER) "
        p0.execSQL(sorgu)
        sorgu="CREATE TABLE Payment(Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,PaymentId INTEGER NOT NULL,Date TEXT NOT NULL,Amount REAL NOT NULL, FOREIGN KEY(PaymentId) REFERENCES PaymentType(Id)) "
        p0.execSQL(sorgu)
    }

    override fun onUpgrade(p0: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }
}
