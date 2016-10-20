package com.example.ants.fireantscenteri.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ants.fireantscenteri.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 15291 on 2016/10/20.
 */

public class FooterViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tvFooter)
    public TextView mTvFooter;

    public FooterViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
