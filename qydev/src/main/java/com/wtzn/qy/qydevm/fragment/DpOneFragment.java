package com.wtzn.qy.qydevm.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.wtzn.qy.qydevm.R;
import com.wtzn.qy.qydevm.bean.BaseMsg;
import com.wtzn.qy.qydevm.http.HttpUrl;
import com.wtzn.qy.qydevm.zxing.CaptureActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 添加调配单
 */
public class DpOneFragment extends Fragment {
    public static final int RESULT_OK           = -1;
    public final static int SCANNING_REQUEST_CODE = 1;
    private EditText etImei;
    private EditText etPrice;
    private EditText etConsigno;
    private EditText etConsignee;
    private EditText etRemark;
    private Button Dp;


    public DpOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dp_one, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        onInit();
    }

    private void onInit() {
        etImei = (EditText) getView().findViewById(R.id.etIMEI);
        etPrice = (EditText) getView().findViewById(R.id.etPrice);
        etConsigno = (EditText) getView().findViewById(R.id.etConsigno);
        etConsignee = (EditText) getView().findViewById(R.id.etConsignee);
        etRemark = (EditText) getView().findViewById(R.id.etRemark);
    


        etImei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, SCANNING_REQUEST_CODE);


            }
        });

        Dp = (Button) getView().findViewById(R.id.btDp);



//        Bundle bd= getArguments();
//        dev =(Device) bd.getSerializable("data");
//        tvDevName.setText("设备名称:"+dev.getDevName());

        Dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpOneDevice();
            }
        });



    }

    private void dpOneDevice() {
        Dp.setText("正在调配...");
        String IMEI = etImei.getText().toString();
        String price = etPrice.getText().toString();
        String consignee = etConsignee.getText().toString();
        String consigno = etConsignee.getText().toString();
        String remark = etRemark.getText().toString();

        OkHttpUtils.post().url(HttpUrl.dpOneDevice)
                .addParams("IMEI",IMEI)
                .addParams("price",price)
                .addParams("consignee",consignee)
                .addParams("consigno",consigno)
                .addParams("remark",remark)
                .build()
                .execute(new StringCallback() {


            @Override
            public void onError(Call call, Exception e, int i) {
                Toast.makeText(getActivity(), getResources().getString(R.string.httpErro), Toast.LENGTH_SHORT).show();
                Dp.setText("调配");
                Dp.setEnabled(true);
            }

            @Override
            public void onResponse(String s, int i) {
                BaseMsg msg= JSON.parseObject(s,BaseMsg.class);

                Toast.makeText(getActivity(),msg.getContent(),Toast.LENGTH_LONG).show();



                if(msg.isSuccess())
                {
                    Dp.setText("调配完成");
                    Dp.setEnabled(false);
                }
                else
                {
                    Dp.setText("调配");
                    Dp.setEnabled(true);
                }


            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SCANNING_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    final Bundle bundle = data.getExtras();
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //textView.setText(bundle.getString("result"));
                            etImei.setText(bundle.getString("result"));
                        }
                    });
                }
                break;
            default:
                break;
        }


    }
}
