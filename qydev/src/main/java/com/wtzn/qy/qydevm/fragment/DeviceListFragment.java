package com.wtzn.qy.qydevm.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.wtzn.qy.qydevm.ContentActivity;
import com.wtzn.qy.qydevm.R;
import com.wtzn.qy.qydevm.adapter.DevAdapter;
import com.wtzn.qy.qydevm.bean.BaseMsg;
import com.wtzn.qy.qydevm.bean.Device;
import com.wtzn.qy.qydevm.http.HttpUrl;
import com.wtzn.qy.qydevm.ui.LoadMoreListView;
import com.wtzn.qy.qydevm.ui.MyPopupWin;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;


import okhttp3.Call;

/**
 * 设备列表
 */
public class DeviceListFragment extends Fragment {
    private static final String TAG = "TestFrag";
    private List<Device> mDataList= new ArrayList<Device>();
    private DevAdapter mLvAdapter;
    private LoadMoreListView mListView;
    private MyPopupWin popupWin;
    Button btDevAdd;
    private int currentPage = 1;

    public DeviceListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_devicelist, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onInit();
    }



    private void onInit() {

        btDevAdd = (Button) getView().findViewById(R.id.btDevAdd);
        btDevAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ContentActivity.class);
                Bundle bd = new Bundle();
                bd.putString("frg","addDevice");
                intent.putExtras(bd);
                startActivityForResult(intent, 101);

            }
        });
        mListView =  (LoadMoreListView) getView().findViewById(R.id.listView);
        mLvAdapter = new DevAdapter(getActivity(),mDataList);


        mListView.setAdapter(mLvAdapter);
        mDataList.clear();
        currentPage=1;
        getDataList();

        mListView.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onloadMore() {
                loadMoreData();
            }
        });

        mListView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add(Menu.NONE,0,0,"入库");
                menu.add(Menu.NONE,1,1,"回库");
                menu.add(Menu.NONE,2,2,"即入即出");
                menu.add(Menu.NONE,3,3,"修改设备");
                menu.add(Menu.NONE,4,4,"删除设备");
            }
        });

    }

    public void loadMoreData()
    {
        currentPage += 1;
        getDataList();
    }

    private void getDataList()
    {

        OkHttpUtils.get().url(HttpUrl.findDeviceByPage)
                .addParams("page",String.valueOf(currentPage))
                .addParams("rows",String.valueOf(10))
                .build().execute(new StringCallback() {


            @Override
            public void onError(Call call, Exception e, int i) {
                Toast.makeText(getActivity(), getResources().getString(R.string.httpErro), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String s, int i) {
                BaseMsg msg=JSON.parseObject(s,BaseMsg.class);
                if(msg.isSuccess()) {
                    String content = msg.getContent();

                    List<Device> list = (ArrayList<Device>) (JSON.parseArray(content, Device.class));
                    mLvAdapter.addAll(list);
                    mLvAdapter.notifyDataSetChanged();
                }
                else
                {
                    Toast.makeText(getActivity(),msg.getContent(),Toast.LENGTH_LONG).show();
                }
                mListView.setLoadCompleted();

            }
        });

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int pos = info.position;
        Intent intent = new Intent(getActivity(), ContentActivity.class);
        Bundle bd = new Bundle();
        switch (item.getItemId())
        {
            case 0:// "入库"

                bd.putString("frg","rk");
                bd.putSerializable("data",mDataList.get(pos));
                intent.putExtras(bd);
                startActivity(intent);
                break;
            case 1:// "回库"

                bd.putString("frg","hk");
                bd.putSerializable("data",mDataList.get(pos));
                intent.putExtras(bd);
                startActivity(intent);
                break;
            case 2://"即入即出"

                bd.putString("frg","io");
                bd.putSerializable("data",mDataList.get(pos));
                intent.putExtras(bd);
                startActivity(intent);
                break;
            case 3://"修改设备"

                bd.putString("frg","updateDevice");
                bd.putSerializable("data",mDataList.get(pos));
                intent.putExtras(bd);
                //startActivity(intent);
                startActivityForResult(intent, 102);
                break;
            case 4://"删除设备"

                Device dev= mDataList.get(pos);
                delete(dev.getDevNo());
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void delete(String batchNo)
    {

        OkHttpUtils.post().url(HttpUrl.deleteDevice)
                .addParams("devNo",batchNo)
                .build().execute(new StringCallback() {


            @Override
            public void onError(Call call, Exception e, int i) {
                Toast.makeText(getActivity(), getResources().getString(R.string.httpErro), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String s, int i) {
                BaseMsg msg= JSON.parseObject(s,BaseMsg.class);

                Toast.makeText(getActivity(),msg.getContent(),Toast.LENGTH_LONG).show();

                initData();

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        initData();
    }

    private void initData()
    {
        mDataList.clear();
        currentPage=1;
        getDataList();
    }
}
