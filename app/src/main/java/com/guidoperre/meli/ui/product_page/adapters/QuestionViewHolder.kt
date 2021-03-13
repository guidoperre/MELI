package com.guidoperre.meli.ui.product_page.adapters

import androidx.recyclerview.widget.RecyclerView
import com.guidoperre.meli.databinding.ItemQuestionBinding
import com.guidoperre.meli.entities.product.result.Question


class QuestionViewHolder(
    private val binding: ItemQuestionBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(question: Question) {
        binding.tvPregunta.text = question.text
        binding.tvRespuesta.text = question.answer?.text
    }

}

