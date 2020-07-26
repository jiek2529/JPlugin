package com.jiek.plugin_core;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * 加载插件的 dex里的类
 * 1. 获取到插件的资源对象
 * 2. 获取到插件中的类加载器
 * 3. 获取插件的包信息类
 */
public class PluginManager {
    private volatile static PluginManager instance;
    //上下文
    private Context context;
    //插件的资源
    private Resources pluginResources;
    //插件的类加载类
    private DexClassLoader dexClassLoader;
    //插件的包信息类
    private PackageInfo pluginPackageInfo;

    public static PluginManager getInstance() {
        if (instance == null) {
            synchronized (PluginManager.class) {
                if (instance == null) {
                    instance = new PluginManager();
                }
            }
        }
        return instance;
    }

    public void setContext(Application context) {
        this.context = context;
    }

    public void loadPlugin(String pluginPath) {
        //包管理器
        PackageManager packageManager = context.getPackageManager();
        //获取包下的 Activitys
        pluginPackageInfo = packageManager.getPackageArchiveInfo(pluginPath, PackageManager.GET_ACTIVITIES);
        File pluginFile = context.getDir("plugin", Context.MODE_PRIVATE);
        dexClassLoader = new DexClassLoader(pluginPath, pluginFile.getAbsolutePath(), null, context.getClassLoader());

        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getDeclaredMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, pluginPath);

            pluginResources = new Resources(assetManager, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Resources getPluginResources() {
        return pluginResources;
    }

    public DexClassLoader getDexClassLoader() {
        return dexClassLoader;
    }

    public PackageInfo getPluginPackageInfo() {
        return pluginPackageInfo;
    }
}
