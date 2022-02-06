package com.a20220203_fahadsaleem_nycschools.repository

import com.a20220203_fahadsaleem_nycschools.model.School
import com.a20220203_fahadsaleem_nycschools.singleton.RetrofitBuilder.apiService

class NycHighSchoolRepositoryImp : NycHighSchoolRepository {

    override suspend fun getListOfNycSchoolsList(): Result<List<School>> {
        return try {
            val list : List<School> = apiService.getSchoolData()
            Result.Success(list)
        }catch (e:Exception){
            Result.Error(Exception("Cannot open HttpURLConnection"))
        }
    }
}

interface NycHighSchoolRepository{
    suspend fun getListOfNycSchoolsList() : Result<List<School>>
}

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}