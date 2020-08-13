package com.drz.main.viewholder

import android.animation.ObjectAnimator
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.drz.main.R
import com.drz.main.bean.CustomBean
import com.zhpan.bannerview.holder.ViewHolder

/**
 * 应用模块:
 *
 *
 * 类描述:
 *
 *
 *
 * @author darryrzhoong
 * @since 2020-02-28
 */
class CustomPageViewHolder : ViewHolder<CustomBean> {
    private var mOnSubViewClickListener: OnSubViewClickListener? = null
    var mImageView: ImageView? = null
    fun setOnSubViewClickListener(subViewClickListener: OnSubViewClickListener ){
        mOnSubViewClickListener= subViewClickListener
    }

    override fun createView(
        viewGroup: ViewGroup,
        context: Context,
        position: Int
    ): View {
        val view =
            LayoutInflater.from(context).inflate(R.layout.main_item_custom_view, viewGroup, false)
        mImageView = view.findViewById(R.id.banner_image)
        return view
    }

    override fun onBind(
        context: Context,
        data: CustomBean,
        position: Int,
        size: Int
    ) {
        mImageView!!.setImageResource(data.imageRes)
        val alphaAnimator = ObjectAnimator.ofFloat(mImageView, "alpha", 0f, 1f)
        alphaAnimator.duration = 1500
        alphaAnimator.start()
    }

    interface OnSubViewClickListener {
        fun onViewClick(view: View?, position: Int)
    }
}