package com.example.zhenghangxia.uidemo.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhenghangxia.uidemo.R;
import com.example.zhenghangxia.uidemo.activity.PostDetailsActivity;
import com.example.zhenghangxia.uidemo.base.BaseFragment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import static com.example.zhenghangxia.uidemo.utils.Constants.images;
import static com.example.zhenghangxia.uidemo.utils.Constants.tips;

/**
 * Created by zhenghangxia on 17-6-30.
 *
 *  最近
 *
 */

public class RecentFragment extends BaseFragment {
    private TextView mTitle;
    private SwipeRefreshLayout mRefresh;
    private ListView mListView;
    private Banner mBanner;

    @Override
    protected int getViewLayout() {
        return R.layout.fragment_recent;
    }

    @Override
    protected void initView() {
        // 页面标题
        mTitle = (TextView) getView().findViewById(R.id.tv_find_header);
        mTitle.setText("最近");
        // 刷新
        mRefresh = (SwipeRefreshLayout) getView().findViewById(R.id.srl_recent);
        // 列表
        mListView = (ListView) getView().findViewById(R.id.lv_recent);
        // 添加头布局
        mListView.addHeaderView(View.inflate(getActivity(), R.layout.header_list_recent, null));
        // 添加尾布局
        mListView.addFooterView(View.inflate(getActivity(), R.layout.footer_list_recent, null));
        // banner轮播控件
        mBanner = (Banner) getView().findViewById(R.id.banner_recent);
        // 设置轮播
        setBannerView();
    }

    /**
     *  设置图片轮播
     */
    private void setBannerView() {
        // 加载图片和标题内容
        List imageList = new ArrayList();
        List titleList = new ArrayList();
        for (int i = 0; i < images.length; i++) {
            imageList.add(images[i]);
            titleList.add(tips[i]);
        }
        // 设置加载器
        mBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        // 设置相关属性
        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);//设置页码与标题
//        mBanner.setIndicatorGravity(BannerConfig.CENTER);//设置指示器位置
        mBanner.setDelayTime(3000);//设置轮播时间
        mBanner.setImages(imageList);//设置图片源
        mBanner.setBannerTitles(titleList);//设置标题
        mBanner.start();

    }

    @Override
    protected void initData() {
        mRefresh.setRefreshing(true);
        // 模拟加载网络数据
        mRefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadData();
                mRefresh.setRefreshing(false);
            }
        },2000);
    }


    /**
     *  加载网络数据
     */
    private void loadData() {

        List<String> mList = new ArrayList<>();
        mList.add("\n1\n\nthis is 1\n");
        mList.add("\n2\n\n" +
                "this is 2\n");
        mList.add("\n3\n\n" +
                "this is 3\n");
        mList.add("\n4\n\n" +
                "this is 4\n");
        mList.add("\n5\n\n" +
                "this is 5\n");
        mList.add("\n6\n\n" +
                "this is 6\n");
        mList.add("\n7\n\n" +
                "this is 7\n");
        mList.add("\n8\n\n" +
                "this is 8\n");
        mListView.setAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,mList));

    }

    @Override
    protected void initEvents() {
        // ListView数据列表点击事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), PostDetailsActivity.class);
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                intent.putExtra("content",tv.getText());
                startActivity(intent);
            }
        });
    }
}
