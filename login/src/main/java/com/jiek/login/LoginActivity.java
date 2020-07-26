package com.jiek.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jiek.plugin_core.BaseActivity;

public class LoginActivity extends BaseActivity {

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.login_back:
                    finish();
                    break;
                case R.id.go_detail:
                    toast("goDetail");
                    startActivity(new Intent(proxyActvity, DetailActivity.class));
                default:
                    break;
            }
        }
    };

    private void toast(String msg) {
        Toast.makeText(proxyActvity, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.login_back).setOnClickListener(onClickListener);
        findViewById(R.id.go_detail).setOnClickListener(onClickListener);
    }
}
