package com.mhmtyldz.shoppy.adapter.product

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mhmtyldz.shoppy.databinding.CardProductBinding
import com.mhmtyldz.shoppy.databinding.CardProductSearchBinding
import com.mhmtyldz.shoppy.model.products.Product

/**
created by Mehmet E. Yıldız
 **/
class CardProductSearchAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var _list = ArrayList<Product>()
    val list get() = _list.toList()

    private lateinit var context: Context

    fun setList(newList: ArrayList<Product>) {
        _list.clear()
        _list.addAll(newList)

        notifyDataSetChanged()
    }

    class CardProductSearchViewHolder(val binding: CardProductSearchBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return CardProductSearchViewHolder(
            CardProductSearchBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bindCardProductSearchViewHolder(holder as CardProductSearchViewHolder, position)
    }

    private fun bindCardProductSearchViewHolder(
        holder: CardProductSearchViewHolder,
        position: Int
    ) {
        holder.binding.apply {
            val item = list[position]

            item.thumbnail?.let {
                Glide.with(context).load(it).into(imgProduct)
            }
            item.title?.let {
                tvName.text = it
            }
            item.price?.let {
                tvPrice.text = "$$it"
            }



            cardProduct.setOnClickListener {
                onClickListenerCustom?.let { listener ->
                    item.id?.let { itemId ->
                        listener(itemId)
                    }
                }
            }

        }
    }

    private var onClickListenerCustom: ((productId: Int) -> Unit)? = null
    fun setOnClickListenerCustom(f: ((productId: Int) -> Unit)) {
        onClickListenerCustom = f
    }
}