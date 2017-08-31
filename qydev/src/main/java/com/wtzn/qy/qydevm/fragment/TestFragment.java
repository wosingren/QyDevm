package com.wtzn.qy.qydevm.fragment;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import com.wtzn.qy.qydevm.R;
import com.wtzn.qy.qydevm.bean.Device;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;


public class TestFragment extends Fragment {
    private static final String TAG = "TestFrag";
    private List<Device> mDataList= new ArrayList<Device>();
    private DevAdapter mLvAdapter;
    private SwipeMenuListView listView;
    public TestFragment() {
        // Required empty public constructor
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
//    public static int dp2px(Context context, float dpValue) {
//        final float scale = context.getResources().getDisplayMetrics().density;
//        return (int) (dpValue * scale + 0.5f);
//    }
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_devicelist, container, false);
       onInit(v);
        //getDevList();
        return v;
    }

    private void getDevList()
    {
        String url3="http://192.168.1.108:8080/QyDevWeb/newsAction_pageQuery";
        OkHttpUtils.get().url(url3).addParams("page","3").addParams("pageSize","5").build().execute(new StringCallback() {

            @Override
            public void onError(okhttp3.Call call, Exception e, int i) {
                Log.e("e",e.getMessage());
            }

            @Override
            public void onResponse(String s, int i) {
                Log.e("yy",s);
//


                List<Device> list =   (ArrayList<Device>)(JSON.parseArray(s, Device.class));
                mDataList = list;

            }
        });

    }

    private void onInit(View v) {

        getDevList();
        listView = (SwipeMenuListView) v.findViewById(R.id.listView);

        mLvAdapter = new DevAdapter();
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getActivity());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(dp2px(90));
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getActivity());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
               // deleteItem.setIcon(R.drawable.ic_delete);
                deleteItem.setTitle("delete");
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        listView.setMenuCreator(creator);
        //listView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        listView.setAdapter(mLvAdapter);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:// open
                        Log.e("tag", "点击打开----》: "+position );
                        break;
                    case 1:// delete
                        mDataList.remove(position);
                        mLvAdapter.notifyDataSetChanged();
                        break;

                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

    }


    class DevAdapter extends BaseAdapter {

       // private BitmapUtils mBitmapUtils;

        public DevAdapter() {
//            mBitmapUtils = new BitmapUtils(getActivity());
//            mBitmapUtils.configDefaultLoadingImage(R.drawable.news_pic_default);
        }

        @Override
        public int getCount() {
            if (mDataList!=null) return mDataList.size();
            else return 0;

        }

        @Override
        public Device getItem(int position) {

            if (mDataList!=null) return mDataList.get(position);
            else return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(getActivity(), R.layout.devicelist_item,
                        null);
                holder = new ViewHolder();
//                holder.ivIcon = (ImageView) convertView
//                        .findViewById(R.id.iv_icon);
                holder.tvTitle = (TextView) convertView
                        .findViewById(R.id.item_devName);
//                holder.tvModel = (TextView) convertView
//                        .findViewById(R.id.tv_date);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Device dev = getItem(position);
            holder.tvTitle.setText(dev.getDevName());
//            holder.tvModel.setText(dev.getCreatedate().toString());

            // mBitmapUtils.display(holder.ivIcon, HttpUrl.SERVER_URL+news.listimage);
//            httpUtil.loadImageByVolley( HttpUrl.SERVER_URL+news.listimage,holder.ivIcon);
//
//            Log.e("http",news.listimage);

            return convertView;
        }

    }



    static class ViewHolder {
       // public ImageView ivIcon;
        public TextView tvTitle;
        public TextView tvDate;
    }

     class MyStringCallback extends StringCallback
    {
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

    //弹出操作窗口

}
