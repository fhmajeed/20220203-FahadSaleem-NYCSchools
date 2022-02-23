package com.a20220203_fahadsaleem_nycschools.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.a20220203_fahadsaleem_nycschools.viewmodel.SchoolDetailActivityViewModel
import com.a20220203_fahadsaleem_nycschools.viewmodel.SchoolDetailViewModel

class ViewModelFactory(var mParams: Any) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass === SchoolDetailViewModel::class.java -> {
                SchoolDetailViewModel(mParams as String) as T
            }
            else -> {
                super.create(modelClass)
            }
        }
    }
}