package com.guidoperre.meli.ui.product_page.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.guidoperre.meli.databinding.ItemProductTagBinding
import com.guidoperre.meli.databinding.ItemQuestionBinding
import com.guidoperre.meli.entities.product.result.ProductResult
import com.guidoperre.meli.entities.product.result.Question

class QuestionAdapter: RecyclerView.Adapter<QuestionViewHolder>() {

    private var list = ArrayList<Question>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemQuestionBinding.inflate(inflater,parent,false)
        return QuestionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setQuestions(questions: List<Question>) {
        list = questions as ArrayList<Question>
        notifyDataSetChanged()
    }


}