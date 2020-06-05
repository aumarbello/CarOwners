package com.aumarbello.carowners.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aumarbello.carowners.utils.ViewModelProviderFactory
import com.aumarbello.carowners.viewmodels.FilterViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindsViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(FilterViewModel::class)
    abstract fun bindsFilterViewModel(viewModel: FilterViewModel): ViewModel
}