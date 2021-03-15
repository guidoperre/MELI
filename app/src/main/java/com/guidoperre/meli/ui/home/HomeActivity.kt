package com.guidoperre.meli.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.guidoperre.meli.R
import com.guidoperre.meli.databinding.ActivityHomeBinding
import com.guidoperre.meli.ui.home.dialog.AboutMeFragment
import com.guidoperre.meli.ui.search.SearchActivity
import com.guidoperre.meli.ui.select_country.SelectCountryActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val model: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.lifecycleOwner = this
        binding.activity = this
        binding.model = model
        setObservables()
    }

    private fun setObservables(){
        model.aboutMeTrigger.observe(this, {
            openAboutMe()
        })
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

    private fun openAboutMe(){
        val dialog = supportFragmentManager
        val newFragment = AboutMeFragment()
        newFragment.show(dialog, "ABOUT_ME")
    }

}