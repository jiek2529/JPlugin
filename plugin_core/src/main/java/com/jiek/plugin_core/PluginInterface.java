package com.jiek.plugin_core;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

public interface PluginInterface {
    /**
     * 向代理类注入上下文
     *
     * @param jActivity
     */
    void attach(Activity jActivity);

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestory();

    void onSaveInstanceState(Bundle outState);

    boolean onTouchEvent(MotionEvent event);

    void onBackPressed();
}
