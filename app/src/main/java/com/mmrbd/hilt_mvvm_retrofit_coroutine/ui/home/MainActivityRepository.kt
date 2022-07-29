package com.mmrbd.hilt_mvvm_retrofit_coroutine.ui.home

import androidx.lifecycle.MutableLiveData
import com.mmrbd.hilt_mvvm_retrofit_coroutine.data.models.User
import com.mmrbd.hilt_mvvm_retrofit_coroutine.data.network.RetroInterface
import javax.inject.Inject


class MainActivityRepository @Inject constructor(private val retroInterface: RetroInterface) {

//    Without coroutine
//    suspend fun getLiveDataList(query: String, liveData: MutableLiveData<List<Datum>>) {
//        retroInterface.getSearchData(query).enqueue(object : Callback<DataList> {
//            override fun onResponse(call: Call<DataList>, response: Response<DataList>) {
//                liveData.postValue(response.body()?.items!!)
//            }
//
//            override fun onFailure(call: Call<DataList>, t: Throwable) {
//                liveData.postValue(null)
//            }
//        })
//
//    }


    suspend fun getUserDataList(query: String, liveData: MutableLiveData<List<User>>) {
        liveData.postValue(retroInterface.getSearchData(query).body()?.items!!)
    }

}