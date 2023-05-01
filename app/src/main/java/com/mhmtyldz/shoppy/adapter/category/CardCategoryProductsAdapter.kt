package com.mhmtyldz.shoppy.adapter.category

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.mhmtyldz.shoppy.R
import com.mhmtyldz.shoppy.databinding.CardCategoryBinding

/**
created by Mehmet E. Yıldız
 **/
class CardCategoryProductsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var _list = ArrayList<String>()
    val list get() = _list.toList()

    private lateinit var context: Context
    private var selectedCategoryName = ""

    fun setList(newList: ArrayList<String>) {
        selectedCategoryName = ""
        _list.clear()
        _list.addAll(newList)

        notifyDataSetChanged()
    }

    class CardCategoryViewHolder(val binding: CardCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return CardCategoryViewHolder(
            CardCategoryBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bindCardCategoryViewHolder(holder as CardCategoryViewHolder, position)
    }

    private fun bindCardCategoryViewHolder(
        holder: CardCategoryViewHolder,
        position: Int
    ) {
        holder.binding.apply {
            val item = list[position]
            tvCategoryName.text = item

            processSelection(cardCategory, tvCategoryName, item)

            cardCategory.setOnClickListener {
                onClickListenerCustom?.let { listener ->
                    if (selectedCategoryName == item) {
                        selectedCategoryName = ""
                        Toast.makeText(context,selectedCategoryName, Toast.LENGTH_SHORT).show()
                        listener("")
                        notifyDataSetChanged()
                    } else {
                        selectedCategoryName = item
                        Toast.makeText(context,selectedCategoryName, Toast.LENGTH_SHORT).show()
                        listener(item)
                        notifyDataSetChanged()
                    }
                }
            }
        }
    }

    private fun processSelection(
        cardView: MaterialCardView,
        tvCategoryName: TextView,
        categoryName: String
    ) {
        if (selectedCategoryName == categoryName) {
            // select
            cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.s_black))
            tvCategoryName.setTextColor(ContextCompat.getColor(context, R.color.white))
        } else {
            // unselect
            cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.white))
            tvCategoryName.setTextColor(ContextCompat.getColor(context, R.color.s_black))

        }
    }


    private var onClickListenerCustom: ((categoryName: String) -> Unit)? = null
    fun setOnClickListenerCustom(f: ((categoryName: String) -> Unit)) {
        onClickListenerCustom = f
    }
}