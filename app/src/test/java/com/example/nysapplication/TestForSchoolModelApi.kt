package com.example.nysapplication

import com.example.nysapplication.main.respository.NYCRepository
import com.example.nysapplication.main.viewmodel.schoolList.GetSchoolCases
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class TestForSchoolModelApi {

    private lateinit var getSchoolsUseCase: GetSchoolCases
    private lateinit var mockApi: MockApi
    private val mockRepository = mockk<NYCRepository>(relaxed = true)


    @Before
    fun setUp() {
        //val mockapi = mockk<NYCRepository>()
        //getSchoolsUseCase = GetSchoolCases(mockapi)
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get schools use case returns a value`() {

        val schoolsList = getSchoolsUseCase.invoke()
        //assertThat(schoolsList).isNotNull()
    }

    @Test
    fun `get schools use case doesn't return a value`() {

        val schoolsList = getSchoolsUseCase.invoke()
        //mockRepository.getSchools()
    }

}