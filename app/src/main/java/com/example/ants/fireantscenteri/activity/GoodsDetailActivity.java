package com.example.ants.fireantscenteri.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ants.fireantscenteri.I;
import com.example.ants.fireantscenteri.R;
import com.example.ants.fireantscenteri.utils.L;

public class GoodsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        int goodsID = getIntent().getIntExtra(I.GoodsDetails.KEY_GOODS_ID, 0);
        L.e("details", "goodsid=" + goodsID);
    }
}
