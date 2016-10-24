package com.example.ants.fireantscenteri.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.ants.fireantscenteri.R;
import com.example.ants.fireantscenteri.utils.MFGT;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 15291 on 2016/10/24.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.username)
    EditText mUsername;
    @BindView(R.id.password)
    EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    @OnClick({R.id.btn_login, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                break;
            case R.id.btn_register:
                MFGT.gotoRegister(this);
                break;
        }
    }
}
