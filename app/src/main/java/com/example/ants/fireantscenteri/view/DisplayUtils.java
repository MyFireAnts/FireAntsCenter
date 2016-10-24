package com.example.ants.fireantscenteri.view;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.example.ants.fireantscenteri.R;
import com.example.ants.fireantscenteri.utils.MFGT;

/**
 * Created by 15291 on 2016/10/24.
 */

public class DisplayUtils {
    public static void initBack(final Activity activity) {
        activity.findViewById(R.id.backClickArea).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                activity.finish();
                MFGT.finish(activity);
            }
        });
    }

    public static void initBackWithTitle(final Activity activity, final String title) {
        initBack(activity);
        ((TextView) activity.findViewById(R.id.tv_common_title)).setText(title);
    }
}