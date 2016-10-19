package com.example.ants.fireantscenteri.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ants.fireantscenteri.I;
import com.example.ants.fireantscenteri.R;
import com.example.ants.fireantscenteri.bean.NewGoodsBean;
import com.example.ants.fireantscenteri.utils.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by 15291 on 2016/10/19.
 */

public class GoodsAdapter extends RecyclerView.Adapter {

    Context context;
    List<NewGoodsBean> beanList;

    public GoodsAdapter(Context context, List<NewGoodsBean> beanList) {
        this.context = context;
        this.beanList = beanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        if (viewType == I.TYPE_FOOTER) {
            holder = new FooterViewHolder(View.inflate(context, R.layout.item_footer, null));
        } else {
            holder = new GoodsViewHolder(View.inflate(context, R.layout.item_goods, null));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == I.TYPE_FOOTER) {

        } else {
            GoodsViewHolder goodsViewHolder = (GoodsViewHolder) holder;
            NewGoodsBean goodsBean = beanList.get(position);
            //设置显示商品图片
            ImageLoader.downloadImg(context, goodsViewHolder.ivGoodsThumb, goodsBean.getGoodsThumb());
            goodsViewHolder.tvGoodsName.setText(goodsBean.getGoodsName());
            goodsViewHolder.tvGoodsPrice.setText(goodsBean.getCurrencyPrice());
        }
    }

    @Override
    public int getItemCount() {
        return beanList != null ? beanList.size() + 1 : 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return I.TYPE_FOOTER;
        } else {
            return I.TYPE_ITEM;
        }
    }

    static class FooterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvFooter)
        TextView tvFooter;

        FooterViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class GoodsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivGoodsThumb)
        ImageView ivGoodsThumb;
        @BindView(R.id.tvGoodsName)
        TextView tvGoodsName;
        @BindView(R.id.tvGoodsPrice)
        TextView tvGoodsPrice;
        @BindView(R.id.layout_goods)
        LinearLayout layoutGoods;

        GoodsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
