package com.guidoperre.meli.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.guidoperre.meli.R
import com.guidoperre.meli.databinding.ActivityHomeBinding
import com.guidoperre.meli.databinding.ActivitySearchBinding
import com.guidoperre.meli.ui.search.SearchActivity
import com.guidoperre.meli.ui.search_preview.SearchPreviewActivity
import com.guidoperre.meli.ui.select_country.SelectCountryActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.lifecycleOwner = this
        binding.activity = this
    }

    fun goSearch(){
        val intent = Intent(this, SearchActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.transition.fade_in, R.transition.fade_out)
    }

    fun goSelectCountry(){
        val intent = Intent(this, SelectCountryActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.transition.fade_in, R.transition.fade_out)
    }

}