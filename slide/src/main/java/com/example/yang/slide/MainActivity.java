package com.example.yang.slide;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout rightdDrawerLayout=null;
    private DrawerLayout drawerLayout= null;
    private FrameLayout rightfFrameLayout= null;

    private ActionBarDrawerToggle toggle=null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //右边第一层抽屉
        rightFirstDrawerLayout();
        //右边第二层抽屉
        rightSecondDrawerLayout();

        //toolbar
        Toolbar toolbar= (Toolbar) findViewById(R.id.toobar_main);
        toolbar.setTitle("你好");
        toolbar.setLogo(R.mipmap.ic_launcher);

        //这个是左边抽屉打开的开关
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);

    }

    /**
     * 右边第一层抽屉
     * */
    private void rightFirstDrawerLayout()
    {
        //设置右边第一层抽屉的的大小为和屏幕一样大
        rightdDrawerLayout= (DrawerLayout) findViewById(R.id.drawerLayout_slide_right);
        ViewGroup.LayoutParams layoutParams=rightdDrawerLayout.getLayoutParams();
        layoutParams.width=getResources().getDisplayMetrics().widthPixels;
        rightdDrawerLayout.setLayoutParams(layoutParams);


        drawerLayout=(DrawerLayout) findViewById(R.id.drawerLayout_main);

        //抽屉滑动关闭，使之不能划开，
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset)
            {

            }

            @Override
            public void onDrawerOpened(View drawerView)
            {

            }

            @Override
            public void onDrawerClosed(View drawerView)
            {
                //这里在抽屉再次合上的时候，再次锁上，使之不能划开
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
            }

            @Override
            public void onDrawerStateChanged(int newState)
            {

            }
        });
    }


    /**
     * 右边第二层抽屉
     * */
    private void rightSecondDrawerLayout() //右边的二层抽屉
    {

        //设置宽度为屏幕大小
        rightfFrameLayout= (FrameLayout) findViewById(R.id.frameLayou_right_in);
        ViewGroup.LayoutParams layoutParams=rightfFrameLayout.getLayoutParams();
        layoutParams.width=getResources().getDisplayMetrics().widthPixels;
        rightfFrameLayout.setLayoutParams(layoutParams);
        //让右边不能滑出
        rightdDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
        rightdDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset)
            {

            }

            @Override
            public void onDrawerOpened(View drawerView)
            {

            }

            @Override
            public void onDrawerClosed(View drawerView)
            {
                //第二层抽屉关闭的时候，给自己加锁，给第一层解开锁
                rightdDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,Gravity.RIGHT);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, Gravity.RIGHT);
            }

            @Override
            public void onDrawerStateChanged(int newState)
            {

            }
        });
    }


    /**
     * 在点击事件里，打开抽屉
     * */
    public void onClick (View v)
    {


        switch (v.getId()){
            case R.id.button_first:

                //让第一层抽屉打开，同时把滑动锁打开，允许滑动
                drawerLayout.openDrawer(rightdDrawerLayout);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, Gravity.RIGHT);
                //让第二层的右边抽屉锁上，不能滑出
                rightdDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,Gravity.RIGHT);
                break;
            case R.id.button_second:

                //让第二层抽屉打开，同时把滑动锁打开，允许滑动
                rightdDrawerLayout.openDrawer(rightfFrameLayout);
                rightdDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, Gravity.RIGHT);
                //  让第一层抽屉，直接为打开状态
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN, Gravity.RIGHT);
                break;

        }

    }




}
