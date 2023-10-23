package com.example.nysapplication.main.viewmodel.schoolDetails

import com.example.nysapplication.main.constants.ResponseState
import com.example.nysapplication.main.model.SchoolsSATModel
import com.example.nysapplication.main.respository.NYCRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSchoolSatScores @Inject constructor(

        private val repository: NYCRepository
    ) {

        operator fun invoke(dbn: String): Flow<ResponseState<List<SchoolsSATModel>>> = flow {
            try {
                emit(ResponseState.Loading())
                val scores = repository.getScores(dbn = dbn)
                if(scores.isNullOrEmpty()){
                    emit(ResponseState.Error( "This school hasn't submitted any scores."))
                }
                emit(ResponseState.Success(scores))
            } catch ( e: HttpException){
                emit(ResponseState.Error(e.localizedMessage ?: "An unexpected error"))
            } catch (e : IOException) {
                emit(ResponseState.Error("Check internet connection"))
            }
        }
    }