package com.wtzn.qy.qydevm.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.wtzn.qy.qydevm.R;
import com.wtzn.qy.qydevm.bean.BaseMsg;
import com.wtzn.qy.qydevm.bean.Device;
import com.wtzn.qy.qydevm.bean.ImportInfo;
import com.wtzn.qy.qydevm.http.HttpUrl;
import com.wtzn.qy.qydevm.zxing.CaptureActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 添加入库单
 */
public class ImportOneFragment extends Fragment {
    public static final int RESULT_OK           = -1;
    public final static int SCANNING_REQUEST_CODE = 1;
    private EditText etImei;
    private EditText etPrice;
    private EditText etConsigno;
    private EditText etRemark;
    private TextView tvDevName;
    private Button btRk;
    Device dev;

    public ImportOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_import_one, container, false);
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
        etRemark = (EditText) getView().findViewById(R.id.etRemark);
        tvDevName = (TextView) getView().findViewById(R.id.tvDevName);


        etImei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, SCANNING_REQUEST_CODE);


            }
        });

        btRk = (Button) getView().findViewById(R.id.btRk);



        Bundle bd= getArguments();
        dev =(Device) bd.getSerializable("data");
        tvDevName.setText("设备名称:"+dev.getDevName());

        btRk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                importOneDevice();
            }
        });



    }

    private void importOneDevice() {
        btRk.setText("正在入库...");
        String IMEI = etImei.getText().toString();
        String price = etPrice.getText().toString();
        String devNo = dev.getDevNo();
        String consigno = etConsigno.getText().toString();
        String remark = etRemark.getText().toString();

        OkHttpUtils.post().url(HttpUrl.ImportOneDevice)
                .addParams("IMEI",IMEI)
                .addParams("price",price)
                .addParams("devNo",devNo)
                .addParams("consigno",consigno)
                .addParams("remark",remark)
                .build()
                .execute(new StringCallback() {


            @Override
            public void onError(Call call, Exception e, int i) {
                Toast.makeText(getActivity(), getResources().getString(R.string.httpErro), Toast.LENGTH_SHORT).show();
                btRk.setText("入库");
                btRk.setEnabled(true);
            }

            @Override
            public void onResponse(String s, int i) {
                BaseMsg msg= JSON.parseObject(s,BaseMsg.class);

                Toast.makeText(getActivity(),msg.getContent(),Toast.LENGTH_LONG).show();



                if(msg.isSuccess())
                {
                    btRk.setText("入库完成");
                    btRk.setEnabled(false);
                }
                else
                {
                    btRk.setText("入库");
                    btRk.setEnabled(true);
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
