package com.example.nysapplication.main.dependencyinjection

import com.example.nysapplication.main.constants.Constants
import com.example.nysapplication.main.model.api.SchoolsApi
import com.example.nysapplication.main.respository.NYCRepository
import com.example.nysapplication.main.respository.NYCSchoolImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SchoolsModule {

    @Provides
    @Singleton
    fun schoolsApiProvider(): SchoolsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SchoolsApi::class.java)
    }

    @Provides
    @Singleton
    fun schoolsRepoProvider(api: SchoolsApi): NYCRepository {
        return NYCSchoolImpl(api)
    }
}