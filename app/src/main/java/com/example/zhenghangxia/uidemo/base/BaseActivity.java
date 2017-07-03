package com.example.zhenghangxia.uidemo.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zhenghangxia on 17-6-30.
 *
 *  基类
 */

public abstract class BaseActivity extends AppCompatActivity {

    // 加载布局
    protected abstract int getViewLayout();

    // 控件
    protected abstract void initView();

    // 数据
    protected abstract void initData();

    // 事件
    protected abstract void initEvents();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewLayout());

        initView();

        initData();

        initEvents();

    }

}
