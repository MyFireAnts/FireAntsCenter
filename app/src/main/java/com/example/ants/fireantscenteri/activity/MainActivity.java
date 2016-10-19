package com.example.ants.fireantscenteri.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.ants.fireantscenteri.R;
import com.example.ants.fireantscenteri.utils.L;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.layout_new_good)
    RadioButton layoutNewGood;
    @BindView(R.id.layout_boutique)
    RadioButton layoutBoutique;
    @BindView(R.id.layout_category)
    RadioButton layoutCategory;
    @BindView(R.id.layout_cart)
    RadioButton layoutCart;
    @BindView(R.id.tvCartHint)
    TextView tvCartHint;
    @BindView(R.id.layout_personal_center)
    RadioButton layoutPersonalCenter;

    RadioButton[] buttons;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        buttons = new RadioButton[5];
        buttons[0] = layoutNewGood;
        buttons[1] = layoutBoutique;
        buttons[2] = layoutCategory;
        buttons[3] = layoutCart;
        buttons[4] = layoutPersonalCenter;
    }


    public void onCheckedChange(View view) {
        switch (view.getId()) {
            case R.id.layout_new_good:
                index = 0;
                break;
            case R.id.layout_boutique:
                index = 1;
                break;
            case R.id.layout_category:
                index = 2;
                break;
            case R.id.layout_cart:
                index = 3;
                break;
            case R.id.layout_personal_center:
                index = 4;
                break;
        }
        setRadioButtonStatus();
    }

    private void setRadioButtonStatus() {
        L.e("RadioButtonIndex= " + index);
        for (int i = 0; i < buttons.length; i++) {
            if (i == index) {
                buttons[i].setChecked(true);
            } else {
                buttons[i].setChecked(false);
            }
        }
    }
}
