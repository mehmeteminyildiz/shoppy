package com.mhmtyldz.shoppy.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mhmtyldz.shoppy.R
import com.mhmtyldz.shoppy.adapter.category.CardCategoryAdapter
import com.mhmtyldz.shoppy.adapter.product.CardProductAdapter
import com.mhmtyldz.shoppy.databinding.FragmentHomeBinding
import com.mhmtyldz.shoppy.model.products.Product


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    private val categoryAdapter = CardCategoryAdapter()
    private val smartPhonesProductAdapter = CardProductAdapter()
    private val laptopsProductAdapter = CardProductAdapter()
    private val skincareProductAdapter = CardProductAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleClickEvents()
        getAllCategories()
        getProducts()
    }

    private fun getProducts() {
        // products getirilir ve en son adapter ve rv atamaları yapılır
        getSmartPhoneProducts()
        getLaptopProducts()
        getSkincareProducts()
    }

    private fun getSkincareProducts() {
        val product = Product(
            id = 1231,
            title = "Erkek ayakkabı Nike",
            price = 844,
            images = arrayListOf("https://m.media-amazon.com/images/I/71TawoxTk6L._UY500_.jpg")
        )
        val productList =
            arrayListOf(product, product, product, product, product, product, product, product)
        processSkincaresResponse(data = productList)
    }

    private fun processSkincaresResponse(data: ArrayList<Product>?) {
        binding.apply {

            if (data.isNullOrEmpty()) {
                llSkincares.visibility = View.GONE
            } else {
                llSkincares.visibility = View.VISIBLE
                rvSkinCare.adapter = skincareProductAdapter
                rvSkinCare.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                skincareProductAdapter.setList(data)
            }
        }

    }

    private fun getLaptopProducts() {
        val product = Product(
            id = 1231,
            title = "Erkek ayakkabı Nike",
            price = 844,
            images = arrayListOf("https://m.media-amazon.com/images/I/71TawoxTk6L._UY500_.jpg")
        )
        val productList =
            arrayListOf(product, product, product, product, product, product, product, product)
        processLaptopResponse(data = productList)
    }

    private fun processLaptopResponse(data: ArrayList<Product>?) {
        binding.apply {

            if (data.isNullOrEmpty()) {
                llLaptops.visibility = View.GONE
            } else {
                llLaptops.visibility = View.VISIBLE
                rvLaptops.adapter = laptopsProductAdapter
                rvLaptops.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                laptopsProductAdapter.setList(data)
            }
        }
    }

    private fun getSmartPhoneProducts() {
        val product = Product(
            id = 1231,
            title = "Erkek ayakkabı Nike",
            price = 844,
            images = arrayListOf("https://m.media-amazon.com/images/I/71TawoxTk6L._UY500_.jpg")
        )
        val productList =
            arrayListOf(product, product, product, product, product, product, product, product)
        processSmartPhonesResponse(data = productList)

    }

    private fun processSmartPhonesResponse(data: ArrayList<Product>?) {
        binding.apply {

            if (data.isNullOrEmpty()) {
                llSmartPhones.visibility = View.GONE
            } else {
                llSmartPhones.visibility = View.VISIBLE
                rvSmartPhones.adapter = smartPhonesProductAdapter
                rvSmartPhones.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                smartPhonesProductAdapter.setList(data)
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
                    gotoProductsByCategoryName(categoryName = categoryName)
                }
            }
        }
    }

    private fun gotoProductsByCategoryName(categoryName: String) {
        val bundle = Bundle()
        bundle.putString("categoryName", categoryName)
        findNavController().navigate(R.id.action_homeFragment_to_productsFragment, bundle)
    }

    private fun handleClickEvents() {
        binding.apply {
            imgCart.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_cartFragment)
            }
            imgSearch.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_productsFragment)
            }

        }
    }

}