package com.guidoperre.meli.ui.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guidoperre.meli.R
import com.guidoperre.meli.databinding.ActivitySearchBinding
import com.guidoperre.meli.ui.search.adapter.SuggestAdapter
import com.guidoperre.meli.ui.search_preview.SearchPreviewActivity
import com.guidoperre.meli.utils.MyItemClickListener
import org.koin.android.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity(), MyItemClickListener {

    private lateinit var binding: ActivitySearchBinding
    private val model: SearchViewModel by viewModel()

    private val adapter = SuggestAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        binding.lifecycleOwner = this
        binding.activity = this
        initList()
        setObservables()
        queryListener()
    }

    private fun initList(){
        val list = binding.rvSearch
        list.layoutManager = LinearLayoutManager(
                this,
                RecyclerView.VERTICAL,
                false
        )
        list.adapter = adapter
        list.setHasFixedSize(true)
    }

    private fun setObservables() {
        model.suggestsHandler.observe(this, {
            if (it != null && binding.etSearch.text.toString() != "")
                setSuggests(it)
            else
                setSuggests(ArrayList())
        })
    }

    private fun queryListener(){
        if (intent.getStringExtra("query") != null)
            binding.etSearch.setText(intent.getStringExtra("query"))
        binding.etSearch.requestFocus()
        binding.etSearch.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                val query = s.toString()
                if (query.isNotEmpty()) {
                    model.getSuggests(query)
                    binding.ivCruz.visibility = View.VISIBLE
                } else {
                    model.suggestsHandler.value = ArrayList()
                    binding.ivCruz.visibility = View.INVISIBLE
                }
            }

        })
    }

    private fun setSuggests(suggests: List<String>){
        val suggestsFinal = ArrayList<String>()
        if (binding.etSearch.text.toString() != "")
            suggestsFinal.add(binding.etSearch.text.toString())
        suggestsFinal.addAll(suggests)
        adapter.setSuggests(suggestsFinal)
    }

    fun eraseText(){
        binding.etSearch.setText("")
        binding.ivCruz.visibility = View.INVISIBLE
    }

    override fun onItemClick(item: Any, position: Int) {
        val intent = Intent(this, SearchPreviewActivity::class.java)
        intent.putExtra("query",item as String)
        startActivity(intent)
        overridePendingTransition(R.transition.fade_in, R.transition.fade_out)
        finish()
    }

}