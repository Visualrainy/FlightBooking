package com.tw.booking.profile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tw.booking.databinding.ConsumptionsFragmentBinding
import com.tw.booking.profile.viewmodel.ConsumptionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConsumptionsFragment : Fragment() {

    private val viewModel: ConsumptionViewModel by viewModels()
    private var binding: ConsumptionsFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ConsumptionsFragmentBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initData()
    }

    private fun initData() {
        viewModel.fetchConsumptions("1234")
    }

    private fun initViewModel() {
        binding?.lifecycleOwner = viewLifecycleOwner
        binding?.viewmodel = viewModel
        viewModel.consumptions.observe(this) {
        }
    }
}