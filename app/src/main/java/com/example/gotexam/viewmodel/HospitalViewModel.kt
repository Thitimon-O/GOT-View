package com.example.gotexam.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gotexam.model.HospitalModel
import com.example.gotexam.repository.ApiRepository
import kotlinx.coroutines.launch

class HospitalViewModel : ViewModel() {

    private val repository = ApiRepository()

    private val _hospitalData = MutableLiveData<HospitalModel>()
    val hospitalData: LiveData<HospitalModel> get() = _hospitalData

    fun getHospitalData() {
        viewModelScope.launch {
            try {
                val response = repository.getHospitalData()
                _hospitalData.value = response
            } catch (e: Exception) {
                Log.d("getHospitalData", "api error : ${e.message}")
            }
        }
    }

}