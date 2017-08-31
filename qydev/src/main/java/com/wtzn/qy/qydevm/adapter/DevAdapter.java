package com.wtzn.qy.qydevm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.wtzn.qy.qydevm.R;
import com.wtzn.qy.qydevm.bean.Device;

import java.util.List;

/**
 * Created by Administrator on 2017/8/9.
 */
public class DevAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Device> mDataList;
    public DevAdapter(Context context, List<Device> datas) {
        mDataList = datas;
        mInflater = LayoutInflater.from(context);
    }

    public void addAll(List<Device> mDatas)
    {
        this.mDataList.addAll(mDatas);
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
            convertView = mInflater.inflate(R.layout.devicelist_item, null);
            holder = new ViewHolder();
            holder.tvDevName = (TextView) convertView
                    .findViewById(R.id.item_devName);
                holder.tvModel = (TextView) convertView
                        .findViewById(R.id.item_model);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Device dev = getItem(position);
        holder.tvDevName.setText(dev.getDevName());
        holder.tvModel.setText(dev.getModel());

        return convertView;
    }

    private final class ViewHolder {
        public TextView tvDevName;
        public TextView tvModel;
    }
}
