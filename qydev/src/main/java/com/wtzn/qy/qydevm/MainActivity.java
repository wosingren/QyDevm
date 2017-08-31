package com.wtzn.qy.qydevm;

import android.app.SearchManager;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

import com.viewpagerindicator.TabPageIndicator;
import com.wtzn.qy.qydevm.fragment.DeviceListFragment;
import com.wtzn.qy.qydevm.fragment.DpListFragment;
import com.wtzn.qy.qydevm.fragment.HkListFragment;
import com.wtzn.qy.qydevm.fragment.ImportListFragment;
import com.wtzn.qy.qydevm.fragment.OutportListFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    TextView tbText;
    private TabPageIndicator mVpiIndicator;
    private String [] mTabTitle;
    private ViewPager mVpMain;
    private ArrayList<Fragment> mFragmentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        //初始化ActionBar
//        ActionBar actionBar = getSupportActionBar();
//
//        actionBar.setLogo(R.drawable.home);
//        actionBar.setDisplayUseLogoEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(true);
////        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeButtonEnabled(true);
//        actionBar.setTitle("设备出入库管理");

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setLogo(R.mipmap.ic_launcher);

        //设置导航图标要在setSupportActionBar方法之后
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE);
        //getSupportActionBar().setTitle("设备出入库管理");
        toolbar.setNavigationIcon(R.drawable.home);
        TextView tbText=(TextView)findViewById(R.id.tbTitle);
        tbText.setText("设备出入库管理");
//       getSupportActionBar().setTitle("设备出入库管理");
        toolbar.setSubtitle("SubTitle");
       // setTitle("aaa");


        mVpiIndicator= (TabPageIndicator)  findViewById(R.id.vpiMain);
        mVpMain = (ViewPager)  findViewById(R.id.vpMain);
        mTabTitle=getResources().getStringArray(R.array.mainTab);
        mFragmentList = new ArrayList<Fragment>();
        Bundle bd=new Bundle();
        Fragment fg1=new DeviceListFragment();
        mFragmentList.add(fg1);
        Fragment fg2= new ImportListFragment();
        mFragmentList.add(fg2);
        Fragment fg3= new OutportListFragment();
        mFragmentList.add(fg3);
        Fragment fg4= new DpListFragment();
        mFragmentList.add(fg4);
        Fragment fg5= new HkListFragment();
        mFragmentList.add(fg5);
        mVpMain.setAdapter(new HeaderAdapter(getSupportFragmentManager(),mFragmentList));
        mVpiIndicator.setViewPager(mVpMain);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        return  true;


    }

    class HeaderAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> frgList;

        public HeaderAdapter(FragmentManager fm, ArrayList<Fragment> data) {
            super(fm);
            this.frgList=data;

        }

        @Override
        public Fragment getItem(int position) {
            return frgList.get(position);
        }

        @Override
        public int getCount() {
            return frgList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return mTabTitle[position];
        }
    }

}
