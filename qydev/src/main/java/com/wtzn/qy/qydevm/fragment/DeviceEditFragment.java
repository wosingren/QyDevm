package com.wtzn.qy.qydevm.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.wtzn.qy.qydevm.http.HttpUrl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeviceEditFragment extends Fragment {

    private EditText etDevName;
    private EditText etSupplier;
    private EditText etModel;
    private TextView tvDevNo;
    private Button btDevEdit;
    Device dev;

    public DeviceEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_device_edit, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        onInit();
    }

    private void onInit() {
        etDevName = (EditText) getView().findViewById(R.id.etDevName);
        etSupplier = (EditText) getView().findViewById(R.id.etSupplier);
        etModel = (EditText) getView().findViewById(R.id.etModel);
        tvDevNo = (TextView) getView().findViewById(R.id.tvDevNo);

        btDevEdit = (Button) getView().findViewById(R.id.btDevEdit);



        Bundle bd= getArguments();
        dev =(Device) bd.getSerializable("data");
        tvDevNo.setText("设备名称:"+dev.getDevName());

        etDevName.setText(dev.getDevName());
        etModel.setText(dev.getModel());
        etSupplier.setText(dev.getSupplier());

        tvDevNo.setText(dev.getDevNo());

        btDevEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDevice();
                getActivity().finish();
            }
        });



    }

    private void updateDevice() {
        btDevEdit.setText("修改设备...");
        String devName = etDevName.getText().toString();
        String supplier = etSupplier.getText().toString();
        String devNo = dev.getDevNo();
        String model = etModel.getText().toString();

        OkHttpUtils.post().url(HttpUrl.updateDevice)
                .addParams("devName",devName)
                .addParams("supplier",supplier)
                .addParams("devNo",devNo)
                .addParams("model",model)
                .build()
                .execute(new StringCallback() {


            @Override
            public void onError(Call call, Exception e, int i) {
                Toast.makeText(getActivity(), getResources().getString(R.string.httpErro), Toast.LENGTH_SHORT).show();
                btDevEdit.setText("保存");

            }

            @Override
            public void onResponse(String s, int i) {
                BaseMsg msg= JSON.parseObject(s,BaseMsg.class);

                Toast.makeText(getActivity(),msg.getContent(),Toast.LENGTH_LONG).show();

                btDevEdit.setText("保存");


            }
        });

    }
}
