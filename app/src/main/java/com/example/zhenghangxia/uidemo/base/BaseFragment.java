package com.example.zhenghangxia.uidemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhenghangxia on 17-6-30.
 *
 *  基类
 *
 */

public abstract class BaseFragment extends Fragment {

    private View baseView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        baseView = inflater.inflate(getViewLayout(),null);
        return baseView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

        initData();

        initEvents();
    }

    protected abstract int getViewLayout();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initEvents();

}
