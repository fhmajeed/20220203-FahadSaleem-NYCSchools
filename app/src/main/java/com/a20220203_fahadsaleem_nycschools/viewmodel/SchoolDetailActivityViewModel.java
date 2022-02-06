package com.a20220203_fahadsaleem_nycschools.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.a20220203_fahadsaleem_nycschools.model.SchoolDetail;
import com.a20220203_fahadsaleem_nycschools.repository.NycHighSchoolRepository;
import com.a20220203_fahadsaleem_nycschools.repository.Result;

/**
 * SchoolDetailActivityViewModel is used to provoke MVVM principle because its java base class it has limitation,
 * Demonstrated android/java threading and making IO thread call and post the result via live data.
 */
public class SchoolDetailActivityViewModel extends ViewModel {

    private final NycHighSchoolRepository nycHighSchoolRepository;

    public MutableLiveData<SchoolDetail> schoolDetailMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> progressBar = new MutableLiveData<>();
    public MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public SchoolDetailActivityViewModel(NycHighSchoolRepository nycHighSchoolRepository){
        this.nycHighSchoolRepository = nycHighSchoolRepository;
    }

    public void getSchoolDetail(String dbn) {
        progressBar.postValue(true);
        Thread thread = new Thread(() -> {
            Result<SchoolDetail> schoolDetailResult = nycHighSchoolRepository.makeSchoolDetailRequest(dbn);
            if(schoolDetailResult instanceof Result.Success){
                schoolDetailMutableLiveData.postValue( ((Result.Success<SchoolDetail>) schoolDetailResult).getData());
            }else{
                errorMessage.postValue( ((Result.Error) schoolDetailResult).getException().getMessage());
            }
            progressBar.postValue(false);
        });
        thread.start();
    }
}
