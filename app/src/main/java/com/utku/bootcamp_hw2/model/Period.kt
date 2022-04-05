package com.utku.bootcamp_hw2.model

enum class Period {
    HAFTALIK{
        
        override fun getLenghtofPeriod() = 7

    },
    AYLIK{
        override fun getLenghtofPeriod() = 30

    },
    YILLIK{
        override fun getLenghtofPeriod() = 365

    };
    abstract fun getLenghtofPeriod(): Int

}