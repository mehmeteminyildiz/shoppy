package com.mhmtyldz.shoppy.view.products

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mhmtyldz.shoppy.R
import com.mhmtyldz.shoppy.adapter.category.CardCategoryProductsAdapter
import com.mhmtyldz.shoppy.adapter.product.CardProductSearchAdapter
import com.mhmtyldz.shoppy.databinding.FragmentProductsBinding
import com.mhmtyldz.shoppy.model.products.Product

class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding: FragmentProductsBinding get() = _binding!!

    private var categoryName: String? = null

    private val productAdapter = CardProductSearchAdapter()
    private val categoryAdapter = CardCategoryProductsAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(inflater)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleArguments()
        handleClickEvents()
        searchListener()
        getAllCategories()
        getProducts()
    }

    private fun searchListener() {
        binding.apply {
            etSearch.doOnTextChanged { inputText, _, _, _ ->
                if (inputText != null) {
                    val currentTextLength = inputText.length
                    if (currentTextLength >= 0) {
                        Handler(Looper.getMainLooper()).postDelayed({
                            try {
                                if (currentTextLength == inputText.length) {
                                    Log.e("değişti!:", "$inputText")
                                    getProducts()
                                }

                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }, 1000)
                    }
                }
            }
        }
    }


    private fun handleClickEvents() {
        binding.apply {
            tilSearch.setEndIconOnClickListener {
                Log.e("TAG", "mic clicked")
            }
        }
    }

    private fun getAllCategories() {
        val categoryList = arrayListOf(
            "smartphones",
            "laptops",
            "fragrances",
            "skincare",
            "groceries",
            "home-decoration",
            "furniture",
            "tops",
            "womens-dresses",
            "womens-shoes",
            "mens-shirts",
            "mens-shoes",
            "mens-watches",
            "womens-watches",
            "womens-bags",
            "womens-jewellery",
            "sunglasses",
            "automotive",
            "motorcycle",
            "lighting"
        )

        processCategories(data = categoryList)
    }

    private fun processCategories(data: ArrayList<String>?) {
        binding.apply {

            if (data.isNullOrEmpty()) {
                rvCategories.visibility = View.GONE
            } else {
                rvCategories.visibility = View.VISIBLE
                rvCategories.adapter = categoryAdapter
                categoryAdapter.setList(data)
                categoryAdapter.setOnClickListenerCustom { categoryName ->
                    Log.e("TAG", "categoryName : $categoryName")
                    getProducts()
                }
            }
        }
    }


    private fun getProducts() {
        binding.apply {
            val imm =
                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(etSearch.windowToken, 0)
        }
        Log.e("TAG", "getProducts")
        val product = Product(
            id = 1231,
            title = "Erkek ayakkabı Nike",
            price = 844,
            images = arrayListOf(
                "https://m.media-amazon.com/images/I/71TawoxTk6L._UY500_.jpg",
                "https://m.media-amazon.com/images/I/71TawoxTk6L._UY500_.jpg"
            ),
            thumbnail = "https://m.media-amazon.com/images/I/71TawoxTk6L._UY500_.jpg"
        )
        val productList =
            arrayListOf(product, product, product, product, product, product, product, product)
        processProductResponse(data = productList)
    }

    private fun processProductResponse(data: ArrayList<Product>?) {
        binding.apply {

            if (data.isNullOrEmpty()) {
                llProducts.visibility = View.GONE
            } else {
                llProducts.visibility = View.VISIBLE
                rvProducts.adapter = productAdapter
                rvProducts.layoutManager =
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                productAdapter.setList(data)

                productAdapter.setOnClickListenerCustom {
                    gotoProductDetail(productId = it)
                }
            }
        }
    }

    private fun gotoProductDetail(productId: Int) {
        val bundle = Bundle()
        bundle.putInt("productId", productId)
        findNavController().navigate(R.id.action_productsFragment_to_productDetailFragment, bundle)
    }

    private fun handleArguments() {
        arguments?.let { args ->
            categoryName = args.getString("categoryName")
            Log.e("TAG", "categoryName : $categoryName")
        }
    }

}