package com.tw.booking.order.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tw.booking.R
import com.tw.booking.databinding.OrderListFragmentBinding
import com.tw.booking.order.model.RefundTicketStatus
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
        viewModel._refundTicketStatus.observe(this) {
            when (it) {
                RefundTicketStatus.FAILURE -> Toast.makeText(
                    requireContext(),
                    getString(R.string.refund_fail_tips),
                    Toast.LENGTH_SHORT
                ).show()
                RefundTicketStatus.PARAM_INVALID -> Toast.makeText(
                    requireContext(),
                    getString(R.string.refund_invalid),
                    Toast.LENGTH_SHORT
                ).show()
                RefundTicketStatus.REPEAT_REFUND -> Toast.makeText(
                    requireContext(),
                    getString(R.string.repeat_refund),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
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