package com.pablobarriosdevs.easytask.common

import androidx.lifecycle.ViewModel

//formatting the parameter string to a new format accordingly to DayComponent
fun String.makeFormat(): String{
    return this.replaceFirstChar { it.uppercase() }
        .replace(".", "")
}


