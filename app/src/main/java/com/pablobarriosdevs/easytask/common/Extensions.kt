package com.pablobarriosdevs.easytask.common

//formatting the parameter string to a new format accordingly to DayComponent
fun String.makeFormat(): String{
    return this.replaceFirstChar { it.uppercase() }
        .replace(".", "")
}