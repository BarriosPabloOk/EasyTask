package com.pablobarriosdevs.easytask.presentation.new_task_screen

import androidx.lifecycle.ViewModel
import com.pablobarriosdevs.easytask.domain.use_cases.wrapper.UseCasesWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewTaskViewModel @Inject constructor(
    private val useCasesWrapper: UseCasesWrapper
): ViewModel() {

}
