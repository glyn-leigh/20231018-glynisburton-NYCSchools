package com.example.nysapplication.main.viewmodel.schoolDetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nysapplication.main.constants.ResponseState
import com.example.nysapplication.main.constants.Constants
import com.example.nysapplication.main.model.SchoolsModel
import com.example.nysapplication.main.model.SchoolsSATModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SchoolSATListViewModel @Inject constructor(
    private val getScoresUseCase: GetSchoolSatScores,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(SchoolSATState())
    val state: State<SchoolSATState> = _state
    private var school: SchoolsModel = SchoolsModel()

    init {
        savedStateHandle.get<String>("name")?.let {
            school.school_name = it

        }
        savedStateHandle.get<String>("desc")?.let {
            school.overview_paragraph = it

        }
        savedStateHandle.get<String>("website")?.let {
            school.website = it
        }
        savedStateHandle.get<String>(Constants.PARAM_SCHOOL_DBN)?.let { dbn ->
            getScores(dbn)
        }
    }

    private fun getScores(dbn: String) {
        getScoresUseCase(dbn).onEach { result->
            when(result){
                is ResponseState.Success -> {
                    result.data?.let { convertFromListToObj(it) }
                    _state.value.currentSchool = school
                }
                is ResponseState.Error -> {
                    _state.value = SchoolSATState(error = result.message ?: "An unexpected error occurred")
                }
                is ResponseState.Loading -> {
                    _state.value = SchoolSATState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)


    }

    private fun convertFromListToObj(list:List<SchoolsSATModel>){
        if (list.isNotEmpty()){
            val satObj = list[0]
            _state.value = SchoolSATState(scores = satObj)
        }else{
            _state.value = SchoolSATState(error = Constants.SAT_VALUE_EMPTY)
        }



    }
}