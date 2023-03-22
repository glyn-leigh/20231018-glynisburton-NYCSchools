package com.example.nysapplication.main.respository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nysapplication.main.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NYCRepository {
    var db = NYCDatabase

    var schoolClient: SchoolsApiInterfaceBase = getBaseList().create(SchoolsApiInterfaceBase::class.java)
    var satClient: SchoolsSATInterface = getBaseList().create(SchoolsSATInterface::class.java)

    fun getMainSchoolList():LiveData<List<SchoolsModel>>{
        val liveData = MutableLiveData<List<SchoolsModel>>()

        schoolClient.getSchools().enqueue(object: Callback<List<SchoolsModel>>{

            override fun onResponse(
                call: Call<List<SchoolsModel>>,
                response: Response<List<SchoolsModel>>
            ) {
                if(response.isSuccessful){
                    //We'll order this list so it's nicer to browse
                    //Don't usually use double bangs, but it's the quickest, most straightforward solution currently
                    val sortedList = response.body()!!.sortedBy { it.school_name }
                    liveData.value = sortedList
                }
            }

            override fun onFailure(call: Call<List<SchoolsModel>>, t: Throwable) {

            }

        })

        return liveData

    }

    fun getSATDetails(dbn:String):LiveData<SchoolsSATModel>{
        val liveData = MutableLiveData<SchoolsSATModel>()
        satClient.getSAT(dbn).enqueue(object:Callback<List<SchoolsSATModel>>{

            override fun onResponse(
                call: Call<List<SchoolsSATModel>>,
                response: Response<List<SchoolsSATModel>>
            ) {
                if(response.isSuccessful){
                    /* When data is available, populate LiveData
                    BUT some schools dont have any data available:
                    like this: https://data.cityofnewyork.us/resource/f9bf-2cp4.json?dbn=14K614
                    Instead of failing or erroring out, it just..tells us ok
                    so if that happens, we need to tell the user that SAT data is not available and fall back
                    on the data that is available from the school info call.
                    */
                    if (response.body()?.isEmpty() == true){
                        //we will be sending a SAT Model, that uses the school name from the original call

                        //this is to make sure the name is in there, most likely a more elegant way to do this
                        //schools model will always have the name available
                        db.satModel.school_name = db.schoolsModel.school_name

                        liveData.value = db.satModel
                    }else{
                        //The happy path
                        liveData.value = response.body()!![0]
                    }

                }
            }

            override fun onFailure(call: Call<List<SchoolsSATModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

        return liveData
        }


    fun getMainSchoolListZip(zip:String):LiveData<List<SchoolsModel>>{
        val liveData = MutableLiveData<List<SchoolsModel>>()

        schoolClient.getSchoolByZip(zip).enqueue(object: Callback<List<SchoolsModel>>{

            override fun onResponse(
                call: Call<List<SchoolsModel>>,
                response: Response<List<SchoolsModel>>
            ) {
                if(response.isSuccessful){
                    //We'll order this list so it's nicer to browse
                    //Don't usually use double bangs, but it's the quickest, most straightforward solution currently
                    val sortedList = response.body()!!.sortedBy { it.school_name }
                    liveData.value = sortedList
                }
            }

            override fun onFailure(call: Call<List<SchoolsModel>>, t: Throwable) {

            }

        })

        return liveData

    }

    //Left in for possible future impl
    /*fun getMainSchoolListBoro(boro:String):LiveData<List<SchoolsModel>>{
        val liveData = MutableLiveData<List<SchoolsModel>>()

        schoolClient.getSchoolByBorough(boro).enqueue(object: Callback<List<SchoolsModel>>{

            override fun onResponse(
                call: Call<List<SchoolsModel>>,
                response: Response<List<SchoolsModel>>
            ) {
                if(response.isSuccessful){
                    //We'll order this list so it's nicer to browse
                    //Don't usually use double bangs, but it's the quickest, most straightforward solution currently
                    val sortedList = response.body()!!.sortedBy { it.school_name }
                    liveData.value = sortedList
                }
            }

            override fun onFailure(call: Call<List<SchoolsModel>>, t: Throwable) {

            }

        })

        return liveData

    }*/

    fun getMainSchoolListDbn(dbn:String):LiveData<List<SchoolsModel>>{
        val liveData = MutableLiveData<List<SchoolsModel>>()

        schoolClient.getSchoolByDBN(dbn).enqueue(object: Callback<List<SchoolsModel>>{

            override fun onResponse(
                call: Call<List<SchoolsModel>>,
                response: Response<List<SchoolsModel>>
            ) {
                if(response.isSuccessful){
                    //We'll order this list so it's nicer to browse
                    //Don't usually use double bangs, but it's the quickest, most straightforward solution currently
                    val sortedList = response.body()!!.sortedBy { it.school_name }
                    liveData.value = sortedList
                }
            }

            override fun onFailure(call: Call<List<SchoolsModel>>, t: Throwable) {

            }

        })

        return liveData

    }


    }


