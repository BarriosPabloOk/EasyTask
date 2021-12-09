package com.pablobarriosdevs.easytask.domain.util

sealed class OrderType{
    object Descending : OrderType()
    object Ascending : OrderType()
}
