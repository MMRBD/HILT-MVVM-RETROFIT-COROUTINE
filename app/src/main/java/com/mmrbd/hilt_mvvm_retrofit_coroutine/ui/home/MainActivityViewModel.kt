package com.mmrbd.hilt_mvvm_retrofit_coroutine.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmrbd.hilt_mvvm_retrofit_coroutine.data.models.User
import com.mmrbd.hilt_mvvm_retrofit_coroutine.ui.home.MainActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: MainActivityRepository) :
    ViewModel() {

    var liveDataList: MutableLiveData<List<User>> = MutableLiveData()

    fun liveDataListObserver(): MutableLiveData<List<User>> {
        return liveDataList
    }

     fun loadLiveUserDataList(query: String) {
        viewModelScope.launch {
            repository.getUserDataList(query, liveDataList)
        }
    }
}