package com.aumarbello.carowners.ui.owners

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.aumarbello.carowners.R
import com.aumarbello.carowners.databinding.FragmentCarOwnersBinding
import com.aumarbello.carowners.di.DaggerAppComponent
import com.aumarbello.carowners.utils.*
import com.aumarbello.carowners.viewmodels.CarOwnersViewModel
import java.io.File
import javax.inject.Inject

class CarOwnersFragment: Fragment(R.layout.fragment_car_owners) {
    @Inject
    lateinit var factory: ViewModelProviderFactory

    private val ownersArg by navArgs<CarOwnersFragmentArgs>()
    private val viewModel by viewModels<CarOwnersViewModel> { factory }
    private lateinit var binding: FragmentCarOwnersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerAppComponent.create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentCarOwnersBinding.bind(view)
        setTitle(R.string.label_car_owners, true)

        val download = "${requireContext().filesDir.absolutePath}/decagon"
        val parent = File(download).also {
            if (!it.exists()) {
                it.mkdirs()
            }
        }

        binding.carOwnerList.addItemDecoration(SpacingDecorator())

        val csvFile = File(parent, Constants.FILE_NAME)
        viewModel.filterOwners(ownersArg.filter, csvFile)

        viewModel.loader.observe(viewLifecycleOwner, Observer {
            binding.loader.root.isVisible = it
        })

        viewModel.error.observe(viewLifecycleOwner, EventObserver {
            showSnackBar(it)
        })

        viewModel.response.observe(viewLifecycleOwner, Observer {
            binding.emptyState.root.isVisible = it.isEmpty()
            binding.emptyState.message.text = getString(R.string.label_empty_car_owners)

            binding.carOwnerList.adapter = CarOwnersAdapter(it)
        })
    }
}