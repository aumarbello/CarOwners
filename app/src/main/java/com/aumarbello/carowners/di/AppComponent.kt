package com.aumarbello.carowners.di

import com.aumarbello.carowners.ui.filter.FilterFragment
import com.aumarbello.carowners.ui.owners.CarOwnersFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(fragment: FilterFragment)
    fun inject(fragment: CarOwnersFragment)
}