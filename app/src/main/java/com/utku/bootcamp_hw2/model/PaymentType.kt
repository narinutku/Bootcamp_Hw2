package com.utku.bootcamp_hw2.model

import kotlin.properties.Delegates

class PaymentType {
    var id by Delegates.notNull<Int>()
    var paymentPeriod:Period?=null
    lateinit var  title:String
    var paymentDayofPeriod:Int?=null
}