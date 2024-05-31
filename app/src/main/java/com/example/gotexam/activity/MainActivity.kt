package com.example.gotexam.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gotexam.adapter.HospitalAdapter
import com.example.gotexam.databinding.ActivityMainBinding
import com.example.gotexam.model.CurrentLocationModel
import com.example.gotexam.model.HospitalDesModel
import com.example.gotexam.model.LocationsModel
import com.example.gotexam.viewmodel.HospitalViewModel
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var hospitalViewmodel: HospitalViewModel
    private val newList = ArrayList<HospitalDesModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hospitalViewmodel = ViewModelProvider(this)[HospitalViewModel::class.java]

        intiData()
    }

    private fun intiData() {
        hospitalViewmodel.getHospitalData()
        hospitalViewmodel.hospitalData.observe(this) { hospital ->
            val currentLocation = CurrentLocationModel(13.723884, 100.529435)

            hospital.list.forEach { data ->
                val splitData = data.gps.split(",").toTypedArray()
                val latitude = splitData[0].toDouble()
                val longitude = splitData[1].filterNot { it.isWhitespace() }.toDouble()
                newList.add(HospitalDesModel(data.name, data.location, data.tel, data.gps, LocationsModel(latitude, longitude)))
            }

            newList.forEach {
                val result = calcDistance(it, currentLocation)
                it.distance = result
            }

            newList.sortBy { it.distance }

            val adapter = HospitalAdapter(this, newList)
            binding.recyclerHospital.adapter = adapter
            binding.recyclerHospital.layoutManager = LinearLayoutManager(this)

        }
    }

    private fun calcDistance(hospital: HospitalDesModel, current: CurrentLocationModel): Double {
        val r = 637100.0
        val d2r = Math.PI / 180.0
        val rLat1 = hospital.newLocation.latitude * d2r
        val rLat2 = current.latitude * d2r
        val dLat = (current.latitude - hospital.newLocation.latitude) * d2r
        val dLon = (current.longitude - hospital.newLocation.longitude) * d2r
        val a = (sin(dLat / 2) * sin(dLat / 2)) + (cos(rLat1) *
                cos(rLat2) * (sin(dLon / 2) * sin(dLon / 2)))
        return 2 * r * atan2(sqrt(a), sqrt(1 - a))
    }
}