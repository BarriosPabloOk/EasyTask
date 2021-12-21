package com.pablobarriosdevs.easytask.presentation.new_task_screen

sealed class UIEvents{
    data class ShowSnackBar(val message : String) : UIEvents()
    object SaveTask : UIEvents()
}
