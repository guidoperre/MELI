package com.guidoperre.meli.ui.home.dialog

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.guidoperre.meli.R
import com.guidoperre.meli.databinding.DialogAboutMeBinding
import com.guidoperre.meli.entities.sites.Site
import com.guidoperre.meli.ui.home.HomeViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel
import java.lang.Exception

class AboutMeFragment: DialogFragment() {

    private lateinit var mView: View
    private lateinit var binding: DialogAboutMeBinding

    private val model: HomeViewModel by sharedViewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val binding:DialogAboutMeBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.dialog_about_me,
                container,
                false
        )
        val view = binding.root

        this.binding = binding
        this.mView = view

        return view
    }

    override fun onStart() {
        super.onStart()
        setObservables()
        binding.fragment = this
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun setObservables() {
        model.siteHandler.observe(this, {
            if (it != null)
                setCountry(it)
            else
                Toast.makeText(
                        mView.context,
                        "Ocurrio un error, intente de nuevo mas tarde",
                        Toast.LENGTH_SHORT
                ).show()
        })

        model.getSite()
    }

    private fun setCountry(site: Site) {
        when(site.id) {
            "MPE" -> binding.ivSelectedCountry.setImageResource(R.mipmap.ic_peru_foreground)
            "MLM" -> binding.ivSelectedCountry.setImageResource(R.mipmap.ic_mexico_foreground)
            "MRD" -> binding.ivSelectedCountry.setImageResource(R.mipmap.ic_dominicana_foreground)
            "MLB" -> binding.ivSelectedCountry.setImageResource(R.mipmap.ic_brasil_foreground)
            "MCR" -> binding.ivSelectedCountry.setImageResource(R.mipmap.ic_costa_rica_foreground)
            "MCO" -> binding.ivSelectedCountry.setImageResource(R.mipmap.ic_colombia_foreground)
            "MLC" -> binding.ivSelectedCountry.setImageResource(R.mipmap.ic_chile_foreground)
            "MCU" -> binding.ivSelectedCountry.setImageResource(R.mipmap.ic_cuba_foreground)
            "MHN" -> binding.ivSelectedCountry.setImageResource(R.mipmap.ic_honduras_foreground)
            "MPA" -> binding.ivSelectedCountry.setImageResource(R.mipmap.ic_panama_foreground)
            "MGT" -> binding.ivSelectedCountry.setImageResource(R.mipmap.ic_guatemala_foreground)
            "MLA" -> binding.ivSelectedCountry.setImageResource(R.mipmap.ic_argentina_foreground)
            "MPY" -> binding.ivSelectedCountry.setImageResource(R.mipmap.ic_paraguay_foreground)
            "MBO" -> binding.ivSelectedCountry.setImageResource(R.mipmap.ic_bolivia_foreground)
            "MSV" -> binding.ivSelectedCountry.setImageResource(R.mipmap.ic_salvador_foreground)
            "MEC" -> binding.ivSelectedCountry.setImageResource(R.mipmap.ic_ecuador_foreground)
            "MNI" -> binding.ivSelectedCountry.setImageResource(R.mipmap.ic_nicaragua_foreground)
            "MLV" -> binding.ivSelectedCountry.setImageResource(R.mipmap.ic_venezuela_foreground)
            "MLU" -> binding.ivSelectedCountry.setImageResource(R.mipmap.ic_uruguay_foreground)
            else -> binding.ivSelectedCountry.setImageResource(0)
        }
    }

    fun openGithub(){
        try {
            val uri = Uri.parse("https://github.com/guidoperre")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            mView.context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(
                    mView.context,
                    "Ocurrio un error, intente de nuevo mas tarde",
                    Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun openLinkedIn(){
        try {
            val uri = Uri.parse("https://www.linkedin.com/in/guido-perre-a4285417b/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            mView.context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(
                    mView.context,
                    "Ocurrio un error, intente de nuevo mas tarde",
                    Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun openYouArrive(){
        try {
            val uri = Uri.parse(
        "https://play.google.com/store/apps/details?id=com.guidoperre.youarrive&hl=es-419"
            )
            val intent = Intent(Intent.ACTION_VIEW, uri)
            mView.context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(
                    mView.context,
                    "Ocurrio un error, intente de nuevo mas tarde",
                    Toast.LENGTH_SHORT
            ).show()
        }
    }

}