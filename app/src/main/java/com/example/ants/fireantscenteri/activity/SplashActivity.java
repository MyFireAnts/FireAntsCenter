package com.example.ants.fireantscenteri.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ants.fireantscenteri.R;
import com.example.ants.fireantscenteri.utils.MFGT;

public class SplashActivity extends AppCompatActivity {

    private final long sleepTime = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /**
         * final boolean
         * postDelayed(Runnable r, long delayMillis)
         * Causes the Runnable r to be added to the message queue,
         * to be run after the specified amount of time elapses.
         * 导致要添加到消息队列中的可运行R，经过规定的时间量之后运行。
         * 可运行将向此处理程序附加的线程上运行。 时基是uptimeMillis()，
         * 时间在深睡眠中度过会增加一个额外的延迟执行。
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MFGT.gotoMainActivity(SplashActivity.this);
                finish();
            }
        }, sleepTime);
    }
}
