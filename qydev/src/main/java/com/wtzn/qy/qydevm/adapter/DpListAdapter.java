package com.wtzn.qy.qydevm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wtzn.qy.qydevm.R;
import com.wtzn.qy.qydevm.bean.OperateInfo;

import java.util.List;

/**
 * 调配适配器
 */
public class DpListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<OperateInfo> mDataList;
    public DpListAdapter(Context context, List<OperateInfo> datas) {
        mDataList = datas;
        mInflater = LayoutInflater.from(context);
    }

    public void addAll(List<OperateInfo> mDatas)
    {
        this.mDataList.addAll(mDatas);
    }

    @Override
    public int getCount() {
        if (mDataList!=null) return mDataList.size();
        else return 0;

    }

    @Override
    public OperateInfo getItem(int position) {

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
            convertView = mInflater.inflate(R.layout.dplist_item,
                    null);
            holder = new ViewHolder();
//                holder.ivIcon = (ImageView) convertView
//                        .findViewById(R.id.iv_icon);
            holder.tvBatchNo = (TextView) convertView
                    .findViewById(R.id.item_batchNo);
                holder.tvDate = (TextView) convertView
                        .findViewById(R.id.item_createTime);
            holder.tvConsignee  = (TextView) convertView.findViewById(R.id.item_consignee);
            holder.tvConsigno = (TextView) convertView.findViewById(R.id.item_consigno);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        OperateInfo dev = getItem(position);
        if(dev!=null) {
            holder.tvBatchNo.setText(dev.getBatchNo());
            holder.tvDate.setText(dev.getCreateTime());
            holder.tvConsignee.setText(dev.getConsignee());
            holder.tvConsigno.setText(dev.getConsigno());
        }
//            holder.tvModel.setText(dev.getCreatedate().toString());

        // mBitmapUtils.display(holder.ivIcon, HttpUrl.SERVER_URL+news.listimage);
//            httpUtil.loadImageByVolley( HttpUrl.SERVER_URL+news.listimage,holder.ivIcon);
//
//            Log.e("http",news.listimage);

        return convertView;
    }

    private final class ViewHolder {
        // public ImageView ivIcon;
        public TextView tvBatchNo;
        public TextView tvDate;
        public TextView tvConsignee;
        public TextView tvConsigno;
    }
}
