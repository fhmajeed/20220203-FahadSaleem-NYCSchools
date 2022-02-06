package com.a20220203_fahadsaleem_nycschools.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a20220203_fahadsaleem_nycschools.model.School
import com.a20220203_fahadsaleem_nycschools.repository.NycHighSchoolRepository
import com.a20220203_fahadsaleem_nycschools.repository.Result
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * MainActivityViewModel is a kotlin base class on which I demonstrated implementation of Coroutine, Flows, ViewModel and MVVM pattern.
 */
class MainActivityViewModel(
    private val nycHighSchoolRepository: NycHighSchoolRepository
) : ViewModel() {

    private val _listOfSchool = Channel<List<School>>(Channel.CONFLATED)
    val listOfSchool = _listOfSchool.receiveAsFlow()

    private val _showProgress = MutableStateFlow(false)
    val showProgress : Flow<Boolean> = _showProgress

    private val _errorMessage = MutableStateFlow("")
    val errorMessage : Flow<String> = _errorMessage

    fun getSchoolsList( ) = viewModelScope.launch {
        _showProgress.emit(true)
        when(val result = nycHighSchoolRepository.getListOfNycSchoolsList()){
            is Result.Success -> {
                _listOfSchool.send(result.data)
            }
            is Result.Error -> {
                _errorMessage.emit(result.exception.toString())
            }
        }
        _showProgress.emit(false)
    }
}
