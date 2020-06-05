package com.aumarbello.carowners.ui.filter

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.aumarbello.carowners.R
import com.aumarbello.carowners.databinding.FragmentFilterBinding
import com.aumarbello.carowners.di.DaggerAppComponent
import com.aumarbello.carowners.utils.EventObserver
import com.aumarbello.carowners.utils.ViewModelProviderFactory
import com.aumarbello.carowners.utils.setTitle
import com.aumarbello.carowners.utils.showSnackBar
import com.aumarbello.carowners.viewmodels.FilterViewModel
import javax.inject.Inject

class FilterFragment: Fragment(R.layout.fragment_filter) {
    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var binding: FragmentFilterBinding
    private val viewModel by viewModels<FilterViewModel> { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerAppComponent.create().inject(this)
        viewModel.loadFilters()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentFilterBinding.bind(view)
        setTitle(R.string.label_filter)

        viewModel.response.observe(viewLifecycleOwner, Observer {
            binding.emptyState.root.isVisible = it.isEmpty()

            binding.filterList.adapter = FilterAdapter(it)
        })

        viewModel.loader.observe(viewLifecycleOwner, Observer {
            binding.loader.root.isVisible = it
        })

        viewModel.error.observe(viewLifecycleOwner, EventObserver {
            showSnackBar(it)
        })
    }
}