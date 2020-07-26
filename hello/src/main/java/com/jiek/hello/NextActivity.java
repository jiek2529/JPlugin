package com.jiek.hello;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jiek.plugin_core.BaseActivity;

public class NextActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        findViewById(R.id.show_toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(proxyActvity, "我是 Hello的 Toast", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
