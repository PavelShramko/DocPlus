package com.example.docplus.presentation.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.docplus.databinding.FragmentNewDoctorBinding
import com.example.docplus.di.DocPlusApp
import com.example.docplus.domain.useCase.UseCaseSaveAndEditDoctor
import com.example.docplus.presentation.viewmodel.ListDoctorViewModel
import com.example.docplus.utils.StringArgs

class AddDoctorFragment : Fragment() {

    companion object {
        var Bundle.textArg: String? by StringArgs
    }

    /*val viewModel: ListDoctorViewModel by viewModels {
        ListDoctorViewModelFactory(useCaseSaveAndEditDoctor)
    }*/

    private val viewModel: ListDoctorViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val appComponent = (requireActivity().application as DocPlusApp).appComponent
        appComponent.inject(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNewDoctorBinding.inflate(
            inflater,
            container,
            false
        )

        binding.addDoctorButton.setOnClickListener {
            viewModel.changeContent(
                binding.enterDoctorType.text.toString(),
                binding.enterNameDoctor.text.toString(),
                binding.enterData.text.toString()
            )
            viewModel.save()
            findNavController().navigateUp()
        }
        return binding.root
    }
}