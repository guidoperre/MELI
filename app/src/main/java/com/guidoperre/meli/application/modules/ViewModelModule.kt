package com.guidoperre.meli.application.modules

import com.guidoperre.meli.ui.search.SearchViewModel
import com.guidoperre.meli.ui.search_preview.SearchPreviewViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { SearchViewModel(get()) }
    viewModel { SearchPreviewViewModel(get()) }

}