package com.mhmtyldz.shoppy.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mhmtyldz.shoppy.R
import com.mhmtyldz.shoppy.adapter.category.CardCategoryAdapter
import com.mhmtyldz.shoppy.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    private val categoryAdapter = CardCategoryAdapter()

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
            imgSearch.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_productsFragment)
            }

        }
    }

}