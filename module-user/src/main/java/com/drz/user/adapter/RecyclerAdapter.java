package com.drz.user.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.drz.user.R;

import org.jetbrains.annotations.NotNull;


/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 * @author darryrzhoong
 * @since 2020-02-28
 */
public class RecyclerAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RecyclerAdapter() {
        super(R.layout.user_item_view_layout);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, @Nullable String s) {
          baseViewHolder.setText(R.id.tv_item,s);
    }
}
