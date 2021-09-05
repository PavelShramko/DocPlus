package com.example.docplus.presentation.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.docplus.databinding.FragmentNewDoctorBinding
import com.example.docplus.DocPlusApp
import com.example.docplus.presentation.viewmodel.ListDoctorViewModel
import com.example.docplus.utils.StringArgs

class AddDoctorFragment : Fragment() {

    companion object {
        var Bundle.textArg: String? by StringArgs
    }

    private val args by navArgs<AddDoctorFragmentArgs>()
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
    ): View {
        val binding = FragmentNewDoctorBinding.inflate(
            inflater,
            container,
            false
        )

        binding.enterDoctorType.setText(args.type)
        binding.enterNameDoctor.setText(args.name)
        binding.enterData.setText(args.data)

        binding.addDoctorButton.setOnClickListener {
            viewModel.changeContent(
                binding.enterDoctorType.text.toString(),
                binding.enterNameDoctor.text.toString(),
                binding.enterData.text.toString()
            )
            viewModel.save()

            viewModel.edit(
                args.id.toLong(),
                binding.enterDoctorType.toString(),
                binding.enterNameDoctor.toString(),
                binding.enterData.toString()
            )
            // навигация и датабиндинг
            findNavController().navigateUp()
        }
        return binding.root
    }
}