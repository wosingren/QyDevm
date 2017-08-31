package com.wtzn.qy.qydevm.ui;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.wtzn.qy.qydevm.R;
import com.wtzn.qy.qydevm.utils.CommUtils;

/**
 * Created by Administrator on 2017/8/9.
 */
public class MyPopupWin {
    public Button bt1,bt2,bt3;
    public PopupWindow popwin;
    public MyPopupWin(Context context, int layout,int offx,int offy,View view) {

        View popview= View.inflate(context, layout, null);
        popwin= new PopupWindow(popview, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
       //popwin.setBackgroundDrawable(new BitmapDrawable());
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popwin.setBackgroundDrawable(new ColorDrawable());



        popwin.showAsDropDown(view, CommUtils.dp2px(context,offx),CommUtils.dp2px(context,offy));

        // 设置为true之后，PopupWindow内容区域 才可以响应点击事件
        popwin.setTouchable(true);
        popwin.setFocusable(true);//设置点击menu以外其他地方以及返回键退出
        popwin.setOutsideTouchable(true);//设置触摸外面时消失
        //
        //popwin.showAtLocation(view, Gravity.LEFT, CommUtils.dp2px(context,offx),CommUtils.dp2px(context,offy));
        popwin.update();

        bt1= (Button) popview.findViewById(R.id.btRk);
        bt2= (Button) popview.findViewById(R.id.btDp);
        bt3= (Button) popview.findViewById(R.id.btHk);
    }
}
