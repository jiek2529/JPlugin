package com.jiek.hello;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jiek.plugin_core.BaseActivity;

public class HelloActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        findViewById(R.id.goNext).setOnClickListener(onClickListener);
    }

    //当插件化开发时，不能使用 xml 中加 onClick 属性进行点击控制
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.goNext:
                    //插件化时，需要使用 proxyActivity 进行控制，否则会崩溃
                    startActivity(new Intent(proxyActvity, NextActivity.class));
                    break;
                default:
                    break;
            }
        }
    };
}
