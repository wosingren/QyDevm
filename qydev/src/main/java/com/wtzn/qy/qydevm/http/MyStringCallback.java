package com.wtzn.qy.qydevm.http;

import android.util.Log;

import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by Administrator on 2017/8/9.
 */
public class MyStringCallback extends StringCallback {
    private static final String TAG = "http";
    @Override
    public void onBefore(Request request, int id)
    {
        Log.i(TAG,"loading...");

    }

    @Override
    public void onAfter(int id)
    {
        Log.i(TAG,"onAfter");
    }



    @Override
    public void onResponse(String response, int id)
    {
        Log.e(TAG, "onResponse："+response);
        //mTv.setText("onResponse:" + response);


    }

    @Override
    public void inProgress(float progress, long total, int id)
    {
        Log.e(TAG, "inProgress:" + progress);
        // mProgressBar.setProgress((int) (100 * progress));
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        e.printStackTrace();
        //mTv.setText("onError:" + e.getMessage());
    }
}
