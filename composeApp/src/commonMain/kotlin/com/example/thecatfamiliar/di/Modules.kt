package com.example.thecatfamiliar.di

import com.example.thecatfamiliar.character.presentation.home.HomeViewModel
import com.example.thecatfamiliar.character.presentation.overview.OverviewViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {

    viewModelOf(::OverviewViewModel)
    viewModelOf(::HomeViewModel)
}