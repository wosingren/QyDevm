package com.wtzn.qy.qydevm;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.wtzn.qy.qydevm.fragment.DeviceAddFragment;
import com.wtzn.qy.qydevm.fragment.DeviceEditFragment;
import com.wtzn.qy.qydevm.fragment.DpOneFragment;
import com.wtzn.qy.qydevm.fragment.HkOneFragment;
import com.wtzn.qy.qydevm.fragment.ImportOneFragment;
import com.wtzn.qy.qydevm.fragment.IoOneFragment;
import com.wtzn.qy.qydevm.fragment.OutportOneFragment;

public class ContentActivity extends AppCompatActivity {

    private Toolbar toolbar;
    TextView tbText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        init();
    }

    private void init() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Fragment frg=null;
        Bundle bd=getIntent().getExtras();
        tbText=(TextView)findViewById(R.id.tbTitle);
        switch (bd.getString("frg"))
        {
            case "addDevice":

                tbText.setText("添加设备");
                frg = new DeviceAddFragment();break;
            case "updateDevice":

                tbText.setText("修改设备");
                frg = new DeviceEditFragment();break;
            case "rk":

                tbText.setText("设备入库");
                frg = new ImportOneFragment();break;
            case "hk":

                tbText.setText("设备回库");
                frg = new HkOneFragment();break;
            case "io":

                tbText.setText("即入即出");
                frg = new IoOneFragment();break;
            case "addOutport":

                tbText.setText("设备出库");
                frg = new OutportOneFragment();break;
            case "dp":

                tbText.setText("设备调配");
                frg = new DpOneFragment();break;
        }
        frg.setArguments(bd);
        FragmentTransaction fmt = getSupportFragmentManager().beginTransaction();
        fmt.replace(R.id.content,frg);
        fmt.commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

}
