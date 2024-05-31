package com.example.gotexam.network

import com.example.gotexam.model.HospitalModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("hospitals")
    suspend fun getHospitalData(@Query("token") token: String = ApiInstance.API_KEY): HospitalModel
}