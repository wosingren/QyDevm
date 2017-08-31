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
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.wtzn.qy.qydevm.ContentActivity;
import com.wtzn.qy.qydevm.R;
import com.wtzn.qy.qydevm.adapter.ImportListAdapter;
import com.wtzn.qy.qydevm.bean.BaseMsg;
import com.wtzn.qy.qydevm.bean.OperateInfo;
import com.wtzn.qy.qydevm.http.HttpUrl;
import com.wtzn.qy.qydevm.ui.LoadMoreListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 入库单列表
 */
public class ImportListFragment extends Fragment {
    private List<OperateInfo> mDataList= new ArrayList<OperateInfo>();
    private ImportListAdapter mLvAdapter;
    private LoadMoreListView mListView;
    private int currentPage = 1;

    public ImportListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_import_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onInit();
    }



    private void onInit() {


        mListView =  (LoadMoreListView) getView().findViewById(R.id.listView);
        mLvAdapter = new ImportListAdapter(getActivity(),mDataList);


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

               // menu.add(Menu.NONE,11,1,"修改");
                menu.add(Menu.NONE,12,2,"删除");
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

        OkHttpUtils.get().url(HttpUrl.findImportInfoByPage)
                .addParams("page",String.valueOf(currentPage))
                .addParams("rows",String.valueOf(10))
                .build().execute(new StringCallback() {


            @Override
            public void onError(Call call, Exception e, int i) {
                Toast.makeText(getActivity(), getResources().getString(R.string.httpErro), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String s, int i) {
                BaseMsg msg= JSON.parseObject(s,BaseMsg.class);
                if(msg.isSuccess()) {
                    String content = msg.getContent();

                    List<OperateInfo> list = (ArrayList<OperateInfo>) (JSON.parseArray(content, OperateInfo.class));
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

            case 11://"修改"

                Toast.makeText(getActivity(),"修改功能测试中",Toast.LENGTH_SHORT).show();
                break;
            case 12://"删除"

                OperateInfo oif= mDataList.get(pos);
                delete(oif.getBatchNo());
                break;
        }
        return super.onContextItemSelected(item);
    }


    private void delete(String batchNo)
    {

        OkHttpUtils.post().url(HttpUrl.deleteImportInfo)
                .addParams("batchNo",batchNo)
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
