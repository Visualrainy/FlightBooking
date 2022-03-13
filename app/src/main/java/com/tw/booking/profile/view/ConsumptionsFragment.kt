package com.tw.booking.profile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
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
        initView()
        initData()
    }

    private fun initViewModel() {
        binding?.lifecycleOwner = viewLifecycleOwner
        binding?.viewmodel = viewModel
        viewModel.consumptions.observe(this) {
            binding?.recycler?.adapter = ConsumptionsAdapter(it.second)
        }
    }

    private fun initView() {
        binding?.recycler?.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initData() {
        viewModel.fetchConsumptions("123456")
    }
}