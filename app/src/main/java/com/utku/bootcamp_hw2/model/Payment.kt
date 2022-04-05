package com.utku.bootcamp_hw2.model

import kotlin.properties.Delegates

class Payment {
    var id by Delegates.notNull<Int>()
    var paymentTypeId by Delegates.notNull<Int>()
    var date: String by Delegates.notNull()
    var amount by Delegates.notNull<Float>()
}