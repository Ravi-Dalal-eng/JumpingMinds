package com.messaging.assignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.messaging.assignment.R
import com.messaging.assignment.databinding.QuoteItemBinding
import com.messaging.assignment.models.Quote

class QuoteAdapter (private val quoteList:List<Quote>):
    RecyclerView.Adapter<QuoteAdapter.ViewHolder>() {
    val colour= arrayOf(R.drawable.orange_background,R.drawable.blue_background
        ,R.drawable.green_background,R.drawable.purple_background,R.drawable.red_background)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteAdapter.ViewHolder {
        val binding = QuoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quote = quoteList[position]

        holder.itemBinding.apply {
            autherName.setText(quote.author)
            autherQuote.setText(quote.content)
            itemBackground.setBackgroundResource(colour[position%5])
        }

    }


    override fun getItemCount() = quoteList.size

    class ViewHolder(val itemBinding: QuoteItemBinding) : RecyclerView.ViewHolder(itemBinding.root)

}