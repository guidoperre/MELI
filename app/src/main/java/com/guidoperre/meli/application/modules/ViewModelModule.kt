package com.guidoperre.meli.application.modules

import com.guidoperre.meli.ui.home.HomeViewModel
import com.guidoperre.meli.ui.product_page.ProductPageViewModel
import com.guidoperre.meli.ui.search.SearchViewModel
import com.guidoperre.meli.ui.search_preview.SearchPreviewViewModel
import com.guidoperre.meli.ui.select_country.SelectCountryViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { HomeViewModel(get()) }
    viewModel { SearchViewModel(get(),get()) }
    viewModel { SearchPreviewViewModel(get()) }
    viewModel { ProductPageViewModel(get()) }
    viewModel { SelectCountryViewModel(get(),get()) }

}