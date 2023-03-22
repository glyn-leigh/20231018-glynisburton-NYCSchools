package com.example.nysapplication.main.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nysapplication.main.model.*
import com.example.nysapplication.main.respository.NYCRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class NYCViewModel @Inject constructor(): ViewModel() {
    val rh = NYCDatabase
    val repository = NYCRepository()

     private val scope =
        CoroutineScope(Dispatchers.Main + Job())

    override fun onCleared() {
        scope.coroutineContext.cancelChildren()
    }

    fun getSchoolList():LiveData<List<SchoolsModel>>{
        return repository.getMainSchoolList()
    }

    fun getSATDetails(dbn:String):LiveData<SchoolsSATModel>{
        return repository.getSATDetails(dbn)
    }
    fun getSchoolByZip(zip:String):LiveData<List<SchoolsModel>>{
        return repository.getMainSchoolListZip(zip)
    }
    fun getSchoolByBoro(boro:String):LiveData<List<SchoolsModel>>{
        return repository.getMainSchoolListBoro(boro)
    }
    fun getSchoolByDbn(dbn:String):LiveData<List<SchoolsModel>>{
        return repository.getMainSchoolListDbn(dbn)
    }


    private fun CoroutineScope.safeLaunch(block: suspend CoroutineScope.() -> Unit): Job {
        return this.async {
            try {
                block()
            } catch (ce: CancellationException) {
                // You can ignore or log this exception
            } catch (e: Exception) {
                // Here it's better to at least log the exception
                Log.e("TAG","Coroutine error", e)
            }
        }
    }


}