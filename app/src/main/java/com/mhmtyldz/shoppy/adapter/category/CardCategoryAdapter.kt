package com.mhmtyldz.shoppy.adapter.category

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mhmtyldz.shoppy.databinding.CardCategoryBinding

/**
created by Mehmet E. Yıldız
 **/
class CardCategoryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var _list = ArrayList<String>()
    val list get() = _list.toList()

    private lateinit var context: Context

    fun setList(newList: ArrayList<String>) {
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

            cardCategory.setOnClickListener {
                onClickListenerCustom?.let { listener ->
                    listener(item)
                }
            }
        }
    }


    private var onClickListenerCustom: ((categoryName: String) -> Unit)? = null
    fun setOnClickListenerCustom(f: ((categoryName: String) -> Unit)) {
        onClickListenerCustom = f
    }
}