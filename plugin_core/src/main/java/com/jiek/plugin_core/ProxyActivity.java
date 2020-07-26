package com.jiek.plugin_core;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static com.jiek.constant.JConstant.CLASS_NAME;

public class ProxyActivity extends AppCompatActivity {

    private static final String TAG = "ProxyActivity";
    private PluginInterface pluginInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String className = intent.getStringExtra(CLASS_NAME);
        try {
            //todo 此处找不到 class; loadClass -> Method threw 'java.lang.IllegalAccessError' exception.
//           参考 https://www.jianshu.com/p/7217d61c513f
//            Class<?> hiClass = PluginManager.getInstance().getDexClassLoader().loadClass("com.jiek.hello.Hi");
//            Log.e(TAG, "onCreate: hi > " + hiClass.newInstance().toString());

            Class<?> aClass = PluginManager.getInstance().getDexClassLoader().loadClass(className);
            pluginInterface = (PluginInterface) aClass.newInstance();
            //注入上下文
            pluginInterface.attach(this);
            //调用 onCreate 生命周期
            pluginInterface.onCreate(savedInstanceState);

            //调试代码
            Resources pluginResource = PluginManager.getInstance().getPluginResources();
            Log.e(TAG, pluginResource.getClass().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startActivity(Intent intent) {
        String className = intent.getStringExtra(CLASS_NAME);
        Intent target = new Intent(this, ProxyActivity.class);
        target.putExtra(CLASS_NAME, className);
        super.startActivity(target);
    }

    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getPluginResources();
    }

    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getDexClassLoader();
    }

    @Override
    protected void onStart() {
        super.onStart();
        pluginInterface.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        pluginInterface.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pluginInterface.onDestory();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pluginInterface.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        pluginInterface.onResume();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        pluginInterface.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        pluginInterface.onBackPressed();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        pluginInterface.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
