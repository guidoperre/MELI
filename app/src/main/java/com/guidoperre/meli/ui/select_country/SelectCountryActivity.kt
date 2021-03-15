package com.guidoperre.meli.ui.select_country

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guidoperre.meli.R
import com.guidoperre.meli.databinding.ActivitySelectCountryBinding
import com.guidoperre.meli.entities.sites.Site
import com.guidoperre.meli.ui.home.HomeActivity
import com.guidoperre.meli.ui.select_country.adapter.SiteAdapter
import com.guidoperre.meli.ui.select_country.adapter.SiteViewHolder
import com.guidoperre.meli.utils.MyItemClickListener
import org.koin.android.viewmodel.ext.android.viewModel

class SelectCountryActivity : AppCompatActivity(), MyItemClickListener {

    private lateinit var binding: ActivitySelectCountryBinding
    private val model: SelectCountryViewModel by viewModel()

    private val adapter = SiteAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_country)
        binding.lifecycleOwner = this
        binding.model = model
        setActionBar()
        initList()
        setObservables()
    }

    private fun initList(){
        val list = binding.rvSites
        list.layoutManager = LinearLayoutManager(
                this,
                RecyclerView.VERTICAL,
                false
        )
        list.adapter = adapter
        list.setHasFixedSize(true)
    }

    private fun setObservables(){
        model.sitesHandler.observe(this, {
            binding.pbLoading.visibility = View.GONE
            if (it != null)
                adapter.setSites(it)
            else
                Toast.makeText(
                        this,
                        "Ocurrio un error, intente de nuevo mas tarde",
                        Toast.LENGTH_SHORT
                ).show()
        })

        model.actualSiteHandler.observe(this, {
            if (it?.id != null) {
                SiteViewHolder.selectedSiteId = it.id
                adapter.notifyDataSetChanged()
            } else
                Toast.makeText(
                        this,
                        "Ocurrio un error, intente de nuevo mas tarde",
                        Toast.LENGTH_SHORT
                ).show()
        })

        model.commitTrigger.observe(this, {
            onBackPressed()
        })

        model.noSelectionTrigger.observe(this, {
            Toast.makeText(
                    this,
                    "Debe seleccionar una region para poder confirmar",
                    Toast.LENGTH_SHORT
            ).show()
        })

        model.getSites()
        model.getActualSite()
        binding.pbLoading.visibility = View.VISIBLE
    }

    private fun setActionBar(){
        val myToolbar: Toolbar = binding.tbSelectCountry
        setSupportActionBar(myToolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayUseLogoEnabled(false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return true
    }

    override fun onItemClick(item: Any, position: Int) {
        val site = item as Site
        if (site.id != SiteViewHolder.selectedSiteId) {
            SiteViewHolder.selectedSiteId = site.id
            adapter.notifyDataSetChanged()
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.transition.fade_in, R.transition.fade_out)
        finish()
    }

}