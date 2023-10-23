package com.example.nysapplication.main.viewmodel.schoolList

import com.example.nysapplication.main.constants.ResponseState
import com.example.nysapplication.main.model.SchoolsModel
import com.example.nysapplication.main.respository.NYCRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSchoolCases @Inject constructor(
    private val repository: NYCRepository
) {

    operator fun invoke(): Flow<ResponseState<List<SchoolsModel>>> = flow {
        try {
            emit(ResponseState.Loading())
            val schools = repository.getSchools()
            emit(ResponseState.Success(schools))

        } catch ( e: HttpException){
            emit(ResponseState.Error(e.localizedMessage ?: "An unexpected error occurred. Please try again later."))

        } catch (e : IOException) {
            emit(ResponseState.Error("Please check your internet connection."))
        }
    }
}