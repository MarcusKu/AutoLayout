package com.marcus.autoadapter;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);

        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metric);

        int width = metric.widthPixels;  // 屏幕宽度（像素）
        int height = metric.heightPixels;  // 屏幕高度（像素）
        float density = metric.density;  // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
        Log.d(TAG, "widthPixels : " + metric.widthPixels);
        Log.d(TAG, "heightPixels : " + metric.heightPixels);
        Log.d(TAG, "density : " + metric.density);
        Log.d(TAG, "densityDpi : " + metric.densityDpi);
        Log.d(TAG, "scaledDensity : " + metric.scaledDensity);
        Log.d(TAG, "xdpi : " + metric.xdpi);
        Log.d(TAG, "ydpi : " + metric.ydpi);
        Log.d(TAG, "widthDP : " + metric.widthPixels / metric.density);
        Log.d(TAG, "heightDP : " + metric.heightPixels / metric.density);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged : " + newConfig.toString());
    }
}
