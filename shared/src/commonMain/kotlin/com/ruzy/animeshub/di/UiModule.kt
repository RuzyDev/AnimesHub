package com.ruzy.animeshub.di

import com.ruzy.animeshub.presentation.HomeViewModel
import org.koin.dsl.module

val uiModule = module {
    factory { HomeViewModel() }
}