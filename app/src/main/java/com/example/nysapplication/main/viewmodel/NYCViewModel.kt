package com.example.nysapplication.main.viewmodel

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
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
    /*fun getSchoolByBoro(boro:String):LiveData<List<SchoolsModel>>{
        return repository.getMainSchoolListBoro(boro)
    }*/
    fun getSchoolByDbn(dbn:String):LiveData<List<SchoolsModel>>{
        return repository.getMainSchoolListDbn(dbn)
    }

    fun determineType(input:String):String{
        val numberMatcher  = "-?[0-9]+(\\.[0-9]+)?".toRegex()
        val letterMatcher = "^[a-zA-Z]*\$".toRegex()

        if(input.matches(numberMatcher)){
            return "zip"
        }
        /*if(input.matches(letterMatcher)){
            return "boro"
        }*/
        if(input == ""){
            return "refresh"
        }
        return "dbn"

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