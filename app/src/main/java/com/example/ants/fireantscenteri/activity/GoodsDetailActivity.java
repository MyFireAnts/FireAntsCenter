package com.example.ants.fireantscenteri.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ants.fireantscenteri.FuLiCenterApplication;
import com.example.ants.fireantscenteri.I;
import com.example.ants.fireantscenteri.R;
import com.example.ants.fireantscenteri.bean.AlbumsBean;
import com.example.ants.fireantscenteri.bean.GoodsDetailsBean;
import com.example.ants.fireantscenteri.bean.MessageBean;
import com.example.ants.fireantscenteri.bean.User;
import com.example.ants.fireantscenteri.net.NetDao;
import com.example.ants.fireantscenteri.net.OkHttpUtils;
import com.example.ants.fireantscenteri.utils.CommonUtils;
import com.example.ants.fireantscenteri.utils.L;
import com.example.ants.fireantscenteri.utils.MFGT;
import com.example.ants.fireantscenteri.view.FlowIndicator;
import com.example.ants.fireantscenteri.view.SlideAutoLoopView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodsDetailActivity extends BaseActivity {

    @BindView(R.id.backClickArea)
    LinearLayout mBackClickArea;
    @BindView(R.id.tv_good_name_english)
    TextView mTvGoodNameEnglish;
    @BindView(R.id.tv_good_name)
    TextView mTvGoodName;
    @BindView(R.id.tv_good_price_shop)
    TextView mTvGoodPriceShop;
    @BindView(R.id.tv_good_price_current)
    TextView mTvGoodPriceCurrent;
    @BindView(R.id.salv)
    SlideAutoLoopView mSalv;
    @BindView(R.id.indicator)
    FlowIndicator mIndicator;
    @BindView(R.id.wv_good_brief)
    WebView mWvGoodBrief;

    int goodsId;
    GoodsDetailActivity mContext;
    boolean isCollected = false;
    @BindView(R.id.iv_good_collect)
    ImageView mIvGoodCollect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);
        goodsId = getIntent().getIntExtra(I.GoodsDetails.KEY_GOODS_ID, 0);
        L.e("details", "goodsid=" + goodsId);
        if (goodsId == 0) {
            finish();
        }
        mContext = this;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        NetDao.downloadGoodsDetail(mContext, goodsId, new OkHttpUtils.OnCompleteListener<GoodsDetailsBean>() {
            @Override
            public void onSuccess(GoodsDetailsBean result) {
                L.i("details=" + result);
                if (result != null) {
                    showGoodDetails(result);
                } else {
                    finish();
                }
            }

            @Override
            public void onError(String error) {
                finish();
                L.e("details,error=" + error);
                CommonUtils.showShortToast(error);
            }
        });
    }

    private void showGoodDetails(GoodsDetailsBean details) {
        mTvGoodNameEnglish.setText(details.getGoodsEnglishName());
        mTvGoodName.setText(details.getGoodsName());
        mTvGoodPriceCurrent.setText(details.getCurrencyPrice());
        mTvGoodPriceShop.setText(details.getShopPrice());
        mSalv.startPlayLoop(mIndicator, getAlbumImgUrl(details), getAlbumImgCount(details));
        mWvGoodBrief.loadDataWithBaseURL(null, details.getGoodsBrief(), I.TEXT_HTML, I.UTF_8, null);
    }

    private int getAlbumImgCount(GoodsDetailsBean details) {
        if (details.getProperties() != null && details.getProperties().length > 0) {
            return details.getProperties()[0].getAlbums().length;
        }
        return 0;
    }

    private String[] getAlbumImgUrl(GoodsDetailsBean details) {
        String[] urls = new String[]{};
        if (details.getProperties() != null && details.getProperties().length > 0) {
            AlbumsBean[] albums = details.getProperties()[0].getAlbums();
            urls = new String[albums.length];
            for (int i = 0; i < albums.length; i++) {
                urls[i] = albums[i].getImgUrl();
            }
        }
        return urls;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        isCollected();
    }

    @OnClick(R.id.backClickArea)
    public void onBackClick() {
        MFGT.finish(this);
    }

    private void isCollected() {
        User user = FuLiCenterApplication.getUser();
        if (user != null) {
            NetDao.isColected(mContext, user.getMuserName(), goodsId, new OkHttpUtils.OnCompleteListener<MessageBean>() {
                @Override
                public void onSuccess(MessageBean result) {
                    if (result != null && result.isSuccess()) {
                        isCollected = true;
                    } else {
                        isCollected = false;
                    }
                    updateGoodsCollectStatus();
                }

                @Override
                public void onError(String error) {
                    isCollected = false;
                    updateGoodsCollectStatus();
                }
            });
        }
        updateGoodsCollectStatus();
    }

    private void updateGoodsCollectStatus() {
        if (isCollected) {
            mIvGoodCollect.setImageResource(R.mipmap.bg_collect_out);
        } else {
            mIvGoodCollect.setImageResource(R.mipmap.bg_collect_in);
        }
    }

}