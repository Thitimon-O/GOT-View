package com.example.gotexam.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gotexam.activity.HospitalDetailActivity
import com.example.gotexam.databinding.HospitalItemBinding
import com.example.gotexam.model.HospitalDesModel

class HospitalAdapter(private val context: Context, private val list: ArrayList<HospitalDesModel>): RecyclerView.Adapter<HospitalAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalAdapter.ViewHolder {
        val binding = HospitalItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HospitalAdapter.ViewHolder, position: Int) {
        val hospital = list[position]
        val holders = holder.binding
        holders.name.text = hospital.name

        val converted: String
        if (hospital.distance!! >= 1000) {
            converted = ("%.1f".format(hospital.distance!! / 1000)) + " km"
            holders.distance.text = converted
        } else {
            converted = (hospital.distance!!.toInt().toString() + " m")
            holders.distance.text = converted
        }
        holders.root.setOnClickListener {
            val intent = Intent(context, HospitalDetailActivity::class.java)
            intent.putExtra("name", hospital.name)
            intent.putExtra("location", hospital.location)
            intent.putExtra("tel", hospital.tel)
            intent.putExtra("distance", converted)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(val binding: HospitalItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.executePendingBindings()
        }
    }
}