package com.mhmtyldz.shoppy.view.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mhmtyldz.shoppy.R
import com.mhmtyldz.shoppy.adapter.category.CardCategoryAdapter
import com.mhmtyldz.shoppy.databinding.FragmentCartBinding
import com.mhmtyldz.shoppy.databinding.FragmentHomeBinding

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding: FragmentCartBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleClickEvents()
    }

    private fun handleClickEvents() {
        binding.apply {

        }
    }
}