package com.jiek.jplugin;

import android.app.Application;

import com.jiek.plugin_core.PluginManager;

public class JApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PluginManager.getInstance().setContext(this);
    }
}
