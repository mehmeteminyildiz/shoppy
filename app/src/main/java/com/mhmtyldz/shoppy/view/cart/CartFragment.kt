package com.mhmtyldz.shoppy.view.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mhmtyldz.shoppy.R
import com.mhmtyldz.shoppy.adapter.cart.CardCartItemAdapter
import com.mhmtyldz.shoppy.databinding.FragmentCartBinding
import com.mhmtyldz.shoppy.model.cart.CartResponse
import com.mhmtyldz.shoppy.model.cart.Products

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding: FragmentCartBinding get() = _binding!!

    private val cartAdapter = CardCartItemAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleClickEvents()
        getCart()
    }

    private fun getCart() {
        val productList = getProducts()

        val cartResponse = CartResponse(
            id = 1,
            products = productList,
            total = 3845,
            discountedTotal = 3600,
            userId = 5796,
            totalProducts = productList.size,
            totalQuantity = 9
        )
        processCartResponse(data = cartResponse)
    }

    private fun processCartResponse(data: CartResponse) {
        binding.apply {
            data.products?.let {
                processProducts(data = it)
            }
            processSummary(
                totalProduct = data.totalProducts,
                totalQuantity = data.totalQuantity,
                totalPrice = data.total,
                discountedPrice = data.discountedTotal
            )
        }
    }

    private fun processSummary(
        totalProduct: Int?,
        totalQuantity: Int?,
        totalPrice: Int?,
        discountedPrice: Int?
    ) {
        binding.apply {
            var totalDiscount: Int? = null

            totalPrice?.let { totalPrice ->
                discountedPrice?.let { discountedPrice ->
                    totalDiscount = totalPrice - discountedPrice
                }
            }

            totalProduct?.let {
                tvTotalProduct.visibility = View.VISIBLE
                tvTotalProduct.text = "$it products"
            } ?: run {
                tvTotalProduct.visibility = View.GONE
            }

            totalQuantity?.let {
                tvTotalQuantity.visibility = View.VISIBLE
                tvTotalQuantity.text = "$it items"
            } ?: run {
                tvTotalQuantity.visibility = View.GONE
            }

            totalPrice?.let {
                tvTotalPrice.visibility = View.VISIBLE
                tvTotalPrice.text = "$$it"
            } ?: run {
                tvTotalPrice.visibility = View.GONE
            }

            discountedPrice?.let {
                tvDiscountedPrice.visibility = View.VISIBLE
                tvDiscountedPrice.text = "$$it"
            } ?: run {
                tvDiscountedPrice.visibility = View.GONE
            }

            totalDiscount?.let {
                tvTotalDiscount.visibility = View.VISIBLE
                tvTotalDiscount.text = "$$it"
            } ?: run {
                tvTotalDiscount.visibility = View.GONE
            }
        }
    }

    private fun processProducts(data: ArrayList<Products>) {
        binding.apply {

            if (data.isEmpty()) {

            } else {
                rvCart.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                rvCart.adapter = cartAdapter
                cartAdapter.setList(data)

                cartAdapter.setOnMinusClickListenerCustom { productId, isDelete -> }
                cartAdapter.setOnPlusClickListenerCustom { productId -> }
            }
        }
    }

    private fun getProducts(): ArrayList<Products> {
        val productList = ArrayList<Products>()
        for (i in 1..10) {
            val product = Products(
                id = 1,
                title = "Ürün $i",
                price = 180,
                quantity = 3,
                total = 500,
                discountPercentage = 10.0,
                discountedPrice = 140
            )
            productList.add(product)
        }
        return productList
    }

    private fun handleClickEvents() {
        binding.apply {

            cardOrder.setOnClickListener {
                findNavController().navigate(R.id.action_cartFragment_to_paymentSuccessFragment)
            }

        }
    }
}