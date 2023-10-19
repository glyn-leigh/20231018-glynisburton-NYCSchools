package com.example.nysapplication

import com.example.nysapplication.main.model.*
import com.example.nysapplication.main.respository.NYCRepository
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import retrofit2.Callback


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(ExperimentalCoroutinesApi::class)
class UnitTests {

    private lateinit var testRepo: NYCRepository
    private val mockServiceApi = mockk<NYCViewModel>(relaxed = true)
    private val mockSatApi = mockk<SchoolsSATInterface>(relaxed = true)
    val dispatcher = Dispatchers.Unconfined
    private val testViewModel = mockk<NYCViewModel>()

    val mockSchoolItem = mockk<List<SchoolsModel>>(relaxed = true)
    private val mockSatItem = mockk<List<SchoolsSATModel>>(relaxed = true)

    private val mockCallback = mockk<Callback<List<SchoolsModel>>>()

    @Test
    fun CallFullListOfSchools(){


    }
    @Test
    fun CallSchoolSatByDBN(){

    }

    @Test
    fun CallSchoolByZip(){



    }



}