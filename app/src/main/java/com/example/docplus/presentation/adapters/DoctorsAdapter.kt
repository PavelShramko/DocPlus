package com.example.docplus.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.docplus.domain.Doctor
import com.example.docplus.databinding.DoctorHolderBinding

interface OnInteractionListener {
    fun onClick(doctor: Doctor) {}
}

class DoctorsAdapter(
    private val onInteractionListener: OnInteractionListener
): ListAdapter<Doctor, DoctorViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val binding = DoctorHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DoctorViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor = getItem(position)
        holder.bind(doctor)
    }
}

class DoctorViewHolder(
    private val binding: DoctorHolderBinding,
    private val onInteractionListener: OnInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(doctor: Doctor) {
        binding.apply {
            typeDoctor.text = doctor.type
            nameDoctor.text = doctor.name
            lastVisit.text = doctor.time

            containerHolder.setOnClickListener {
                onInteractionListener.onClick(doctor)
            }
        }
    }
}

class DiffCallback: DiffUtil.ItemCallback<Doctor>() {
    override fun areItemsTheSame(oldItem: Doctor, newItem: Doctor): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Doctor, newItem: Doctor): Boolean {
        return oldItem == newItem
    }
}
