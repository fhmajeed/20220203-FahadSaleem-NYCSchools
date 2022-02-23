package com.a20220203_fahadsaleem_nycschools.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.a20220203_fahadsaleem_nycschools.model.SchoolDetail
import com.a20220203_fahadsaleem_nycschools.repository.NycHighSchoolRepository
import com.a20220203_fahadsaleem_nycschools.repository.NycHighSchoolRepositoryImp
import com.a20220203_fahadsaleem_nycschools.repository.Result

/**
 * SchoolDetailActivityViewModel is used to provoke MVVM principle because its java base class it has limitation,
 * Demonstrated android/java threading and making IO thread call and post the result via live data.
 */
class SchoolDetailViewModel(dbn: String) : ViewModel() {

    private val nycHighSchoolRepository: NycHighSchoolRepository = NycHighSchoolRepositoryImp()
    var schoolDetailMutableLiveData = MutableLiveData<SchoolDetail?>()
    var progressBar = MutableLiveData<Boolean>()
    var errorMessage = MutableLiveData<String?>()

    init {
        getSchoolDetail(dbn)
    }

    private fun getSchoolDetail(dbn: String) {
        progressBar.postValue(true)
        val thread = Thread {
            val schoolDetailResult =
                nycHighSchoolRepository.makeSchoolDetailRequest(dbn)
            if (schoolDetailResult is Result.Success<*>) {
                schoolDetailMutableLiveData.postValue((schoolDetailResult as Result.Success<SchoolDetail>).data)
            } else {
                errorMessage.postValue((schoolDetailResult as Result.Error).exception.message)
            }
            progressBar.postValue(false)
        }
        thread.start()
    }}