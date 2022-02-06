package com.a20220203_fahadsaleem_nycschools.api


import com.a20220203_fahadsaleem_nycschools.model.School
import retrofit2.http.GET

interface ApiService {
    @GET("s3k6-pzi2.json")
    suspend fun getSchoolData(): List<School>
}