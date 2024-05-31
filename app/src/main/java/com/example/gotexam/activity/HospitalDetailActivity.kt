package com.example.gotexam.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gotexam.databinding.ActivityHospitalDetailBinding

class HospitalDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHospitalDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHospitalDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val location = intent.getStringExtra("location")
        val tel = intent.getStringExtra("tel")
        val distance = intent.getStringExtra("distance")

        binding.back.setOnClickListener { finish() }
        binding.name.text = name
        binding.location.text = location
        binding.tel.text = tel
        binding.distance.text = distance
    }
}