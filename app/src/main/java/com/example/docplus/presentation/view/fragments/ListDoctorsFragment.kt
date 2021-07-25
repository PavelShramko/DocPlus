package com.example.docplus.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.docplus.R
import com.example.docplus.domain.Doctor
import com.example.docplus.databinding.FragmentListDoctorBinding
import com.example.docplus.presentation.adapters.DoctorsAdapter
import com.example.docplus.presentation.adapters.OnInteractionListener
import com.example.docplus.presentation.viewmodel.ListDoctorViewModel

class ListDoctorsFragment : Fragment() {

    private val viewModel: ListDoctorViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentListDoctorBinding.inflate(
            inflater,
            container,
            false
        )

        val adapter = DoctorsAdapter(object : OnInteractionListener {
            override fun onClick(doctor: Doctor) {
                viewModel.click(doctor)
            }
        })

        binding.rvDoctors.adapter = adapter
        viewModel.listOfDoctor.observe(viewLifecycleOwner, { doctors ->
            adapter.submitList(doctors)
        })

        binding.addDoctorButton.setOnClickListener {
            findNavController().navigate(R.id.action_listDoctorsFragment_to_addDoctorFragment)
        }

        return binding.root
    }
}