package com.example.nysapplication.main.viewmodel.schoolList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nysapplication.main.constants.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SchoolListViewModel @Inject constructor(
    private val getSchoolsUseCase: GetSchoolCases
): ViewModel() {

    private val _state = mutableStateOf(SchoolStateList())
    val state: State<SchoolStateList> = _state



    init {
        getSchools()
    }

    private fun getSchools() {
        getSchoolsUseCase().onEach { result->
            when(result){
                is ResponseState.Success -> {
                    _state.value = SchoolStateList(schools = result.data ?: emptyList())

                }
                is ResponseState.Error -> {
                    _state.value = SchoolStateList(error = result.message ?: "An unexpected error occured")
                }
                is ResponseState.Loading -> {
                    _state.value = SchoolStateList(isLoading = true)
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }

}
