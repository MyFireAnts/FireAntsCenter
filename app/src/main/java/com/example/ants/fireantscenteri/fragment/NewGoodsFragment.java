package com.example.ants.fireantscenteri.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ants.fireantscenteri.I;
import com.example.ants.fireantscenteri.R;
import com.example.ants.fireantscenteri.activity.MainActivity;
import com.example.ants.fireantscenteri.adapter.GoodsAdapter;
import com.example.ants.fireantscenteri.bean.NewGoodsBean;
import com.example.ants.fireantscenteri.net.NetDao;
import com.example.ants.fireantscenteri.net.OkHttpUtils;
import com.example.ants.fireantscenteri.utils.CommonUtils;
import com.example.ants.fireantscenteri.utils.ConvertUtils;
import com.example.ants.fireantscenteri.utils.L;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 15291 on 2016/10/19.
 */

public class NewGoodsFragment extends Fragment {

    @BindView(R.id.tv_refresh)
    TextView tvRefresh;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;

    MainActivity mainActivity;
    GoodsAdapter goodsAdapter;
    ArrayList<NewGoodsBean> newGoodsBeanArrayList;
    int pageId = 1;
    GridLayoutManager gridLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_newgoods, container, false);
        ButterKnife.bind(this, layout);
        mainActivity = (MainActivity) getContext();
        newGoodsBeanArrayList = new ArrayList<>();
        goodsAdapter = new GoodsAdapter(mainActivity, newGoodsBeanArrayList);
        initView();
        initData();
        setListener();
        return layout;
    }

    private void setListener() {
        setPullUpListener();
        setPullDownListener();
    }

    private void setPullDownListener() {
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl.setRefreshing(true);
                tvRefresh.setVisibility(View.VISIBLE);
                pageId = 1;
                downloadNewGoods(I.ACTION_PULL_DOWN);
            }
        });
    }

    private void setPullUpListener() {
        rv.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastPosition = gridLayoutManager.findLastVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastPosition == goodsAdapter.getItemCount() - 1
                        && goodsAdapter.isMore()) {
                    pageId++;
                    downloadNewGoods(I.ACTION_PULL_UP);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstPosition = gridLayoutManager.findFirstVisibleItemPosition();
                srl.setEnabled(firstPosition == 0);
            }
        });
    }

    private void initData() {
        downloadNewGoods(I.ACTION_DOWNLOAD);
    }

    private void downloadNewGoods(final int action) {
        NetDao.downloadNewGoods(mainActivity, pageId, new OkHttpUtils.OnCompleteListener<NewGoodsBean[]>() {
            @Override
            public void onSuccess(NewGoodsBean[] result) {
                srl.setRefreshing(false);
                tvRefresh.setVisibility(View.GONE);
                goodsAdapter.setMore(true);
                L.e("result=" + result);
                if (result != null && result.length > 0) {
                    ArrayList<NewGoodsBean> list = ConvertUtils.array2List(result);
                    if (action == I.ACTION_DOWNLOAD || action == I.ACTION_PULL_DOWN) {
                        goodsAdapter.initData(list);
                    } else {
                        goodsAdapter.addData(list);
                    }
                    if (list.size() < I.PAGE_SIZE_DEFAULT) {
                        goodsAdapter.setMore(false);
                    }
                } else {
                    goodsAdapter.setMore(false);
                }
            }

            @Override
            public void onError(String error) {
                srl.setRefreshing(false);
                tvRefresh.setVisibility(View.GONE);
                goodsAdapter.setMore(false);
                CommonUtils.showShortToast(error);
                L.e("error:" + error);
            }
        });
    }

    private void initView() {
        srl.setColorSchemeColors(
                getResources().getColor(R.color.google_blue),
                getResources().getColor(R.color.google_green),
                getResources().getColor(R.color.google_red),
                getResources().getColor(R.color.google_yellow)
        );
        gridLayoutManager = new GridLayoutManager(mainActivity, I.COLUM_NUM);
        rv.setLayoutManager(gridLayoutManager);
        rv.setHasFixedSize(true);
        rv.setAdapter(goodsAdapter);
    }
}
