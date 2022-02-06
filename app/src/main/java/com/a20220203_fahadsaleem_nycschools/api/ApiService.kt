package com.a20220203_fahadsaleem_nycschools.api


import com.a20220203_fahadsaleem_nycschools.model.School
import com.a20220203_fahadsaleem_nycschools.model.SchoolDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Using retrofit API Service
 * All the service call should go here
 */
interface ApiService {
    @GET("s3k6-pzi2.json")
    suspend fun getSchoolData(): List<School>

    @GET("f9bf-2cp4.json")
    fun getSchoolDetail(@Query("dbn") dbn: String) : Call<List<SchoolDetail>>
}