package com.example.gotexam.repository

import com.example.gotexam.model.HospitalModel
import com.example.gotexam.network.ApiInstance

class ApiRepository {
    private val apiService = ApiInstance.apiService

    suspend fun getHospitalData(): HospitalModel {
        return apiService.getHospitalData()
    }
}