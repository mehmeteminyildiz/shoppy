package com.mhmtyldz.shoppy.view.productDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mhmtyldz.shoppy.R
import com.mhmtyldz.shoppy.adapter.productDetail.ProductDetailImageSliderAdapter
import com.mhmtyldz.shoppy.databinding.FragmentProductDetailBinding
import com.mhmtyldz.shoppy.model.products.Product
import me.relex.circleindicator.CircleIndicator


class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding: FragmentProductDetailBinding get() = _binding!!

    lateinit var viewPagerAdapter: ProductDetailImageSliderAdapter
    private lateinit var indicator: CircleIndicator


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        handleClickEvents()
        getProductDetails()
    }

    private fun getProductDetails() {
        val detail = Product(
            id = 17,
            title = "Tree Oil 30ml",
            description = "Tea tree oil contains a number of compounds, including terpinen-4-ol, that have been shown to kill certain bacteria",
            price = 12,
            discountPercentage = 4.09,
            rating = 4.52,
            stock = 5,
            brand = "Hemani Tea",
            category = "skincare",
            thumbnail = "https://i.dummyjson.com/data/products/17/thumbnail.jpg",
            images = arrayListOf(
                "https://i.dummyjson.com/data/products/17/1.jpg",
                "https://i.dummyjson.com/data/products/17/2.jpg",
                "https://i.dummyjson.com/data/products/17/3.jpg",
                "https://i.dummyjson.com/data/products/17/thumbnail.jpg"
            )
        )
        processDetailResponse(data = detail)
    }

    private fun processDetailResponse(data: Product) {
        binding.apply {
            processTitle(data.title)
            processDescription(data.description)
            processPrice(data.price)
            processDiscount(data.discountPercentage)
            processRating(data.rating)
            processStock(data.stock)
            processBrand(data.brand)
            processImages(data.images)

        }
    }

    private fun processStock(stock: Int?) {
        binding.apply {
            stock?.let {
                if (it < 10) {
                    tvLastProductCount.visibility = View.VISIBLE
                    tvLastProductCount.text = "last $it product"
                } else {
                    tvLastProductCount.visibility = View.GONE
                }
            } ?: run {
                tvLastProductCount.visibility = View.GONE
            }
        }
    }

    private fun processRating(rating: Double?) {
        binding.apply {
            rating?.let {
                tvRating.visibility = View.VISIBLE
                tvReviewCount.visibility = View.VISIBLE
                tvRating.text = it.toString()
                tvReviewCount.text = "(572 reviews)"
            } ?: run {
                tvRating.visibility = View.GONE
                tvReviewCount.visibility = View.GONE
            }
        }
    }

    private fun processDiscount(discountPercentage: Double?) {
        binding.apply {
            discountPercentage?.let {
                cardDiscount.visibility = View.VISIBLE
                tvDiscount.visibility = View.VISIBLE
                tvDiscount.text = "Save $it%"
            } ?: run {
                cardDiscount.visibility = View.GONE
                tvDiscount.visibility = View.GONE
            }
        }
    }

    private fun processPrice(price: Int?) {
        binding.apply {
            price?.let {
                tvPrice.visibility = View.VISIBLE
                tvPrice.text = "$$it"
            } ?: run {
                tvPrice.visibility = View.GONE
            }
        }
    }

    private fun processDescription(description: String?) {
        binding.apply {
            description?.let {
                tvDescription.visibility = View.VISIBLE
                tvDescription.text = it
            } ?: run {
                tvDescription.visibility = View.GONE
            }
        }
    }

    private fun processTitle(title: String?) {
        binding.apply {
            title?.let {
                tvName.visibility = View.VISIBLE
                tvName.text = it
            } ?: run {
                tvName.visibility = View.GONE
            }
        }
    }

    private fun processBrand(brand: String?) {
        binding.apply {
            brand?.let {
                tvBrand.visibility = View.VISIBLE
                tvBrand.text = it
            } ?: run {
                tvBrand.visibility = View.GONE
            }
        }
    }

    private fun processImages(images: ArrayList<String>) {
        binding.apply {

            viewPagerAdapter = ProductDetailImageSliderAdapter(requireContext(), images)
            viewpager.adapter = viewPagerAdapter

            this@ProductDetailFragment.indicator =
                requireView().findViewById(R.id.indicator) as CircleIndicator

            indicator.setViewPager(viewpager)
        }

    }

    private fun initialize() {

    }

    private fun handleClickEvents() {
        binding.apply {
            cardBack.setOnClickListener {
                findNavController().popBackStack()
            }
            cardAddToCart.setOnClickListener {
                Toast.makeText(requireContext(), "Added to cart", Toast.LENGTH_SHORT).show()
            }

        }
    }

}