package com.pablobarriosdevs.easytask.presentation.tasks_screen

import androidx.lifecycle.ViewModel
import com.pablobarriosdevs.easytask.domain.use_cases.wrapper.UseCasesWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskScreenViewModel @Inject constructor(
    private val useCasesWrapper: UseCasesWrapper
): ViewModel() {

}