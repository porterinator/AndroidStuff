package com.example.nista.di.component

import android.content.Context
import com.example.nista.di.component.ActivityComponent
import com.example.nista.di.modules.FragmentModule
import com.example.nista.di.scopes.PerFragment
import com.leroymerlin.cds.di.modules.ViewModelModule

import dagger.Component


@PerFragment
@Component(dependencies = [ActivityComponent::class], modules = arrayOf(FragmentModule::class, ViewModelModule::class))
interface FragmentComponent {

    fun context() : Context



}