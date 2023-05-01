package com.mhmtyldz.shoppy.adapter.productDetail

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.mhmtyldz.shoppy.R

/**
created by Mehmet E. Yıldız
 **/
class ProductDetailImageSliderAdapter(private val context: Context, private var imageList: ArrayList<String>):
    PagerAdapter()  {
    private lateinit var dialog: Dialog

    override fun getCount(): Int {
        return imageList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View =
            (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                R.layout.card_product_image,
                null
            )
        val ivImages = view.findViewById<ImageView>(R.id.imgProduct)

        imageList[position].let {
            Glide.with(context)
                .load(it)
                .into(ivImages)
        }
        ivImages?.let {
            it.setOnClickListener {
//                showDialog(imageList[position])
                onImageClickListenerCustom?.let { listener ->
                    listener(imageList)
                }
            }
        }


        val vp = container as ViewPager
        vp.addView(view, 0)
        return view
    }

    private var onImageClickListenerCustom: ((imageList: ArrayList<String>) -> Unit)? = null
    fun setOnClickListenerCustom(f: ((imageList: ArrayList<String>) -> Unit)) {
        onImageClickListenerCustom = f
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }

}