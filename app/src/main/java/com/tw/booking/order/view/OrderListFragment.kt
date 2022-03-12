package com.tw.booking.order.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tw.booking.R
import com.tw.booking.databinding.OrderListFragmentBinding
import com.tw.booking.order.viewmodel.OrderListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderListFragment : Fragment() {
    private val viewModel: OrderListViewModel by viewModels()
    private var binding: OrderListFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.order_list_fragment, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initView()
    }

    private fun initViewModel() {
        binding?.lifecycleOwner = viewLifecycleOwner
        binding?.viewModel = viewModel
    }

    private fun initView() {
        binding?.refund?.setOnClickListener {
            viewModel.refundTicket(0)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}