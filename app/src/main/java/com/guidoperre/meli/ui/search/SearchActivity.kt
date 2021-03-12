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
import com.guidoperre.meli.entities.search.Search
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
        setObservables()
        initList()
        getExtras()
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
        model.getHistory()
    }

    private fun setObservables() {
        model.suggestsHandler.observe(this, {
            if (binding.etSearch.text.toString() != "")
                setSuggests(it)
            else{
                setSuggests(model.historyHandler)
            }
        })
    }

    private fun queryListener(){
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
                    model.suggestsHandler.value = model.historyHandler
                    binding.ivCruz.visibility = View.INVISIBLE
                }
            }

        })
    }

    private fun getExtras(){
        val extraQuery = intent.getStringExtra("query")
        if (extraQuery != null) {
            binding.etSearch.setText(extraQuery)
            model.getSuggests(extraQuery)
            binding.ivCruz.visibility = View.VISIBLE
        }
    }

    private fun setSuggests(suggests: List<Search>){
        val suggestsFinal = ArrayList<Search>()
        if (binding.etSearch.text.toString() != "")
            suggestsFinal.add(Search(
                0L,
                false,
                binding.etSearch.text.toString()
            ))
        suggestsFinal.addAll(suggests)
        adapter.setSuggests(suggestsFinal)
    }

    fun eraseText(){
        binding.etSearch.setText("")
        binding.ivCruz.visibility = View.INVISIBLE
    }

    override fun onItemClick(item: Any, position: Int) {
        model.insertSearch(item as Search)
        val intent = Intent(this, SearchPreviewActivity::class.java)
        intent.putExtra("query",item.name)
        startActivity(intent)
        overridePendingTransition(R.transition.fade_in, R.transition.fade_out)
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.transition.fade_in, R.transition.fade_out)
        finish()
    }

}