package com.example.zhenghangxia.uidemo.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhenghangxia.uidemo.R;
import com.example.zhenghangxia.uidemo.base.BaseActivity;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class PostDetailsActivity extends BaseActivity {

    private Toolbar mToolBar;
    private ProgressBar mProgressBar;
    private TextView mTitle;
    private TextView mTime;
    private TextView mContent;
    private LinearLayout mPostDetailsCards;
    private FloatingActionMenu mPostDetailsFAMenu;
    private FloatingActionButton mRefreshFAButton;
    private FloatingActionButton mCommentFAButton;

    @Override
    protected int getViewLayout() {
        return R.layout.activity_post_details;
    }

    @Override
    protected void initView() {

        mToolBar = (Toolbar) findViewById(R.id.tb_post_details);
        mProgressBar = (ProgressBar) findViewById(R.id.pb_post_details);
        mTitle = (TextView) findViewById(R.id.tv_post_details_title);
        mTime = (TextView) findViewById(R.id.tv_post_details_time);
        mContent = (TextView) findViewById(R.id.tv_post_details_content);
        mPostDetailsCards = (LinearLayout) findViewById(R.id.ll_posy_details_cards);
        mPostDetailsFAMenu = (FloatingActionMenu) findViewById(R.id.fam_post_details);
        mPostDetailsFAMenu.setClosedOnTouchOutside(true);
        mRefreshFAButton = (FloatingActionButton) findViewById(R.id.fab_post_details_refresh);
        mCommentFAButton = (FloatingActionButton) findViewById(R.id.fab_post_details_comment);

        initToolBar();

    }

    /**
     *  设置标题栏
     */
    private void initToolBar() {

        setSupportActionBar(mToolBar);
        //隐藏默认标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    protected void initData() {

        final String content = getIntent().getStringExtra("content");

        mPostDetailsCards.setVisibility(View.GONE);
        // 模拟数据
        mProgressBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                mProgressBar.setVisibility(View.GONE);
                mPostDetailsCards.setVisibility(View.VISIBLE);
                mTitle.setText("标题");
                mTime.setText("2017-07-03 10:06:00");
                mContent.setText(content);
            }
        },2000);
    }

    @Override
    protected void initEvents() {
        // 刷新按钮
        mRefreshFAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                initData();
                mPostDetailsFAMenu.close(true);

            }
        });

        mCommentFAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PostDetailsActivity.this,"暂无效果",Toast.LENGTH_SHORT).show();
                mPostDetailsFAMenu.close(true);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

}
