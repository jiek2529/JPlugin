package com.jiek.plugin_core;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.jiek.constant.JConstant;

public class BaseActivity extends Activity implements PluginInterface {
    //上下文
    protected Activity proxyActvity;

    @Override
    public void attach(Activity jActivity) {
        proxyActvity = jActivity;
    }

//    @Override
//    public Activity getBaseContext() {
//        return proxyActvity;
//    }

    @Override
    public void setContentView(View view) {
        if (proxyActvity == null) {
            super.setContentView(view);
        } else {
            proxyActvity.setContentView(view);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        proxyActvity.setContentView(layoutResID);
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return proxyActvity.findViewById(id);
    }

    @Override
    public Intent getIntent() {
        return proxyActvity.getIntent();
    }

    @Override
    public ClassLoader getClassLoader() {
        return proxyActvity.getClassLoader();
    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        return proxyActvity.getLayoutInflater();
    }

    @Override
    public void startActivity(Intent intent) {
        Intent target = new Intent();
        target.putExtra(JConstant.CLASS_NAME, intent.getComponent().getClassName());
        proxyActvity.startActivity(target);
    }

    @Override
    public Resources getResources() {
        return proxyActvity.getResources();
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        return proxyActvity.getApplicationInfo();
    }

    @Override
    public Window getWindow() {
        return proxyActvity.getWindow();
    }

    @Override
    public WindowManager getWindowManager() {
        return proxyActvity.getWindowManager();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onStart() {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onResume() {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onPause() {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onStop() {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onDestory() {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public void onBackPressed() {
    }
}
