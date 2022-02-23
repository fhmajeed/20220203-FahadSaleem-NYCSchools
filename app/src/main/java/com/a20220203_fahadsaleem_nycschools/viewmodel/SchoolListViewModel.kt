package com.a20220203_fahadsaleem_nycschools.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a20220203_fahadsaleem_nycschools.model.School
import com.a20220203_fahadsaleem_nycschools.repository.NycHighSchoolRepositoryImp
import com.a20220203_fahadsaleem_nycschools.repository.Result
import kotlinx.coroutines.launch

class SchoolListViewModel : ViewModel() {

    private var nycHighSchoolRepository = NycHighSchoolRepositoryImp()

    private val _listOfSchoolLiveData = MutableLiveData<List<School>?>()
    val listOfSchoolLiveData = _listOfSchoolLiveData

    private val _showProgress = MutableLiveData(false)
    val showProgress = _showProgress

    private val _errorMessage = MutableLiveData("")
    val errorMessage = _errorMessage

    init {
        getSchoolsList()
    }

    private fun getSchoolsList() = viewModelScope.launch {
        _showProgress.postValue(true)
        when (val result = nycHighSchoolRepository.getListOfNycSchoolsList()) {
            is Result.Success -> {
                _listOfSchoolLiveData.postValue(result.data)
            }
            is Result.Error -> {
                _errorMessage.postValue(result.exception.toString())
            }
        }
        _showProgress.postValue(false)
    }

}