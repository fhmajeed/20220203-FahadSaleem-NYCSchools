package com.a20220203_fahadsaleem_nycschools.repository

import com.a20220203_fahadsaleem_nycschools.model.School
import com.a20220203_fahadsaleem_nycschools.model.SchoolDetail
import com.a20220203_fahadsaleem_nycschools.singleton.RetrofitBuilder.apiService


/**
 * NycHighSchoolRepositoryImp is the implementation of the API signatures from NycHighSchoolRepository
 * API Call handling & Exception handling should also be done here
 */
class NycHighSchoolRepositoryImp : NycHighSchoolRepository {

    /**
     * Get list of NYC Schools
     */
    override suspend fun getListOfNycSchoolsList(): Result<List<School>> {
        return try {
            val list : List<School> = apiService.getSchoolData()
            Result.Success(list)
        }catch (e:Exception){
            Result.Error(Exception("Cannot open HttpURLConnection"))
        }
    }

    /**
     * Get NYC School detail by dbn
     */
    override fun makeSchoolDetailRequest(dbn: String): Result<SchoolDetail> {
        return try {
            val response = apiService.getSchoolDetail(dbn).execute()
            if(response.isSuccessful){
                if(response.body() != null){
                    Result.Success(response.body()!![0])
                }else{
                    throw Exception("Empty Body Found")
                }
            }else{
                throw Exception("")
            }
        }catch (e:Exception){
            Result.Error(Exception("Cannot open HttpURLConnection"))
        }
    }
}

/**
 * Interface contain signature of the API Call related implementation
 */
interface NycHighSchoolRepository{
    suspend fun getListOfNycSchoolsList() : Result<List<School>>
    /* only using this method without suspend for java demonstration */
    fun makeSchoolDetailRequest(dbn: String) : Result<SchoolDetail>
}

/**
 * Generic sealed classes for generic response handling at view
 */
sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}