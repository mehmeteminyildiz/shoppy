package com.mhmtyldz.shoppy.adapter.cart

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mhmtyldz.shoppy.databinding.CardCartItemBinding
import com.mhmtyldz.shoppy.model.cart.Products


/**
created by Mehmet E. Yıldız
 **/
class CardCartItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var _list = ArrayList<Products>()
    val list get() = _list.toList()

    private lateinit var context: Context

    fun setList(newList: ArrayList<Products>) {
        _list.clear()
        _list.addAll(newList)

        notifyDataSetChanged()
    }

    class CardCartItemViewHolder(val binding: CardCartItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return CardCartItemViewHolder(
            CardCartItemBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bindCardCartItemViewHolder(holder as CardCartItemViewHolder, position)
    }

    private fun bindCardCartItemViewHolder(
        holder: CardCartItemViewHolder,
        position: Int
    ) {
        holder.binding.apply {
            val item = list[position]
            Glide.with(context)
                .load("https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/iphone-card-40-iphone14pro-202209_FMT_WHH?wid=508&hei=472&fmt=p-jpg&qlt=95&.v=1663611329204")
                .into(imgProduct)
            item.title?.let {
                tvName.text = it
            }

            item.price?.let {
                tvPrice.text = "$$it"
                tvPrice.paintFlags = tvPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }

            item.discountedPrice?.let {
                tvDiscountedPrice.text = "$$it"
            }

            item.quantity?.let {
                tvQuantity.text = it.toString()
            }

            cardMinus.setOnClickListener {
                onMinusClickListenerCustom?.let { listener ->
                    item.id?.let { id ->
                        val quantity = tvQuantity.text.toString().toInt() - 1
                        if (quantity == 0) {
                            // sil
                            removeAt(position)
                            tvQuantity.text = "0"
                            listener(id, true)
                        } else {
                            // azalt
                            tvQuantity.text = quantity.toString()
                            listener(id, false)
                        }
                    }
                }
            }
            cardPlus.setOnClickListener {
                onPlusClickListenerCustom?.let { listener ->
                    item.id?.let { id ->
                        tvQuantity.text = ((tvQuantity.text.toString().toInt()) + 1).toString()
                        listener(id)
                    }
                }
            }
        }
    }

    private fun removeAt(position: Int) {
        _list.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, _list.size)
    }

    private var onMinusClickListenerCustom: ((productId: Int, isDelete: Boolean) -> Unit)? = null
    fun setOnMinusClickListenerCustom(f: ((productId: Int, isDelete: Boolean) -> Unit)) {
        onMinusClickListenerCustom = f
    }

    private var onPlusClickListenerCustom: ((productId: Int) -> Unit)? = null
    fun setOnPlusClickListenerCustom(f: ((productId: Int) -> Unit)) {
        onPlusClickListenerCustom = f
    }
}









