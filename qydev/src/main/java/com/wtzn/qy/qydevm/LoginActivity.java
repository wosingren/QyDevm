package com.wtzn.qy.qydevm;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.wtzn.qy.qydevm.application.MyApplication;
import com.wtzn.qy.qydevm.bean.BaseMsg;
import com.wtzn.qy.qydevm.bean.User;
import com.wtzn.qy.qydevm.http.HttpUrl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class LoginActivity extends AppCompatActivity {


    public EditText userName;
    public EditText userPassword;
    public Button loginBtn;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        userName= (EditText) findViewById(R.id.user_name);
        userPassword= (EditText) findViewById(R.id.user_password);
        loginBtn= (Button) findViewById(R.id.loginBtn);
        // 获取SharedPreferences对象
        SharedPreferences sp = getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
        // 获取Editor对象
        editor = sp.edit();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login() {
        loginBtn.setEnabled(false);
        loginBtn.setText("请稍后.......");
        OkHttpUtils
                .get()
                .url(HttpUrl.LOGIN)
                .addParams("userName", userName.getText().toString())
                .addParams("password", userPassword.getText().toString())
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(LoginActivity.this, getResources().getString(R.string.httpErro), Toast.LENGTH_SHORT).show();
                        loginBtn.setEnabled(true);
                        loginBtn.setText("登录");
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        BaseMsg BaseMsg = JSON.parseObject(response.toString(), BaseMsg.class);
                        if (BaseMsg.isSuccess()) {
                            User u = JSON.parseObject( BaseMsg.getContent(), User.class);
                            //((MyApplication) getApplication()).setToken(BaseMsg.getContent());//保存token
                            ((MyApplication) getApplication()).setUser(u);
                            editor.clear();
                            editor.putString("userName", userName.getText().toString());
                            editor.putString("password", userPassword.getText().toString());
                            editor.commit();
                            Intent intent = new Intent();
                            intent.setClass(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, BaseMsg.getContent().toString(), Toast.LENGTH_SHORT).show();
                            loginBtn.setEnabled(true);
                            loginBtn.setText("登录");
                        }
                    }
                });
    }
}
