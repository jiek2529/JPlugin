package com.jiek.jplugin;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.jiek.constant.JConstant;
import com.jiek.plugin_core.PluginManager;
import com.jiek.plugin_core.ProxyActivity;

/**
 * System services not available to Activities before onCreate()?  什么原因导致
 */
public class MainActivity extends Activity {

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String apkPath;
            switch (v.getId()) {
                case R.id.go_login:
                    apkPath = Environment.getExternalStorageDirectory() + "/login-debug.apk";
                    break;
                case R.id.go_hello:
                    apkPath = Environment.getExternalStorageDirectory() + "/hello-debug.apk";
                    break;
                default:
                    return;
            }
            jumpLogin(apkPath);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.go_login).setOnClickListener(onClickListener);
        findViewById(R.id.go_hello).setOnClickListener(onClickListener);
    }

    public void jumpLogin(String apkPath) {
//        PluginManager.getInstance().setContext(getApplication());
        PluginManager.getInstance().loadPlugin(apkPath);
        PackageInfo packageInfo = PluginManager.getInstance().getPluginPackageInfo();
        if (packageInfo != null && packageInfo.activities != null && packageInfo.activities.length > 0) {
            Intent intent = new Intent(MainActivity.this, ProxyActivity.class);
            intent.putExtra(JConstant.CLASS_NAME, packageInfo.activities[0].name);
            startActivity(intent);
        } else {
            Toast.makeText(this, "请将插件包放入：" + apkPath, Toast.LENGTH_SHORT).show();
        }

    }
}
