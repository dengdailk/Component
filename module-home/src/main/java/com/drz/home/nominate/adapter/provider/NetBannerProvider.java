package com.drz.home.nominate.adapter.provider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.drz.common.adapter.CommonBindingAdapters;
import com.drz.home.R;
import com.zhpan.bannerview.holder.ViewHolder;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 * @author darryrzhoong
 * @since 2020-02-13
 */
public class NetBannerProvider implements ViewHolder<String> {
    private ImageView mImageView;
    @Override
    public View createView(ViewGroup viewGroup, Context context, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_item_banner_item_view, viewGroup, false);
        mImageView = view.findViewById(R.id.banner_bg);
        return view;
    }

    @Override
    public void onBind(Context context, String data, int position, int size) {
        CommonBindingAdapters.loadImage(mImageView, data);
    }

//    @Override
//    public View createView(ViewGroup viewGroup, Context context, int position) {
//        return  View.inflate(context,R.layout.home_item_banner_item_view, null);
//    }
//
//    @Override
//    public void onBind(Context context, String data, int position, int size) {
//        ImageView imageView = itemView.findViewById(R.id.banner_bg);
//        CommonBindingAdapters.loadImage(imageView, data);
//    }
}
