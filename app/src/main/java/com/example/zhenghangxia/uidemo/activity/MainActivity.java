package com.example.zhenghangxia.uidemo.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.zhenghangxia.uidemo.R;
import com.example.zhenghangxia.uidemo.base.BaseActivity;
import com.example.zhenghangxia.uidemo.fragment.CategoryFragment;
import com.example.zhenghangxia.uidemo.fragment.FindFragment;
import com.example.zhenghangxia.uidemo.fragment.MineFragment;
import com.example.zhenghangxia.uidemo.fragment.RecentFragment;
import com.example.zhenghangxia.uidemo.utils.Constants;


/**
 * 主界面
 *
 */
public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {
    private FragmentTransaction mTransaction;
    private RecentFragment mRecentFragment;
    // 当前页面对应的Fragment
    private Fragment mCurFragment;
    private BottomNavigationBar mBottomNavigationBar;
    private CategoryFragment mCategoryFragment;
    private FindFragment mFindFragment;
    private MineFragment mMineFragment;

//    private FrameLayout mFrameLayout;

    @Override
    protected int getViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

//        mFrameLayout = (FrameLayout) findViewById(R.id.frame_content);
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bnb_main);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);

        // 添加导航子栏目
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.icon_main,"最近")
                        .setActiveColorResource(R.color.md_blue_400))
                .addItem(new BottomNavigationItem(R.mipmap.icon_app,"分类")
                        .setActiveColorResource(R.color.md_blue_400))
                .addItem(new BottomNavigationItem(R.mipmap.icon_work,"发现")
                        .setActiveColorResource(R.color.md_blue_400))
                .addItem(new BottomNavigationItem(R.mipmap.icon_mine,"我的")
                        .setActiveColorResource(R.color.md_blue_400))
                .setFirstSelectedPosition(0)
                .initialise();
        // 添加默认页
        mTransaction = getSupportFragmentManager().beginTransaction();
        mRecentFragment = new RecentFragment();
        mTransaction.add(R.id.frame_content,mRecentFragment).commit();
        mCurFragment = mRecentFragment;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvents() {
        mBottomNavigationBar.setTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(int position) {

        switch (position) {
            case 0:
                if (mRecentFragment == null)
                    mRecentFragment = new RecentFragment();
                switchFragmentContent(mRecentFragment);
                break;
            case 1:
                if (mCategoryFragment == null)
                    mCategoryFragment = new CategoryFragment();
                switchFragmentContent(mCategoryFragment);
                break;
            case 2:
                if (mFindFragment == null)
                    mFindFragment = new FindFragment();
                switchFragmentContent(mFindFragment);
                break;
            case 3:
                if (mMineFragment == null)
                    mMineFragment = new MineFragment();
                switchFragmentContent(mMineFragment);
                break;
        }

    }


    /**
     *  切换页面
     * @param fragment
     *              碎片页面
     */
    private void switchFragmentContent(Fragment fragment) {

        if (mCurFragment != fragment) {
            mTransaction = getSupportFragmentManager().beginTransaction();
            // 判断是否被add过
            if (!fragment.isAdded()) {
                // 如果没有 add ，隐藏当前 Fragment，add 需要展示的 Fragment
                mTransaction.hide(mCurFragment).add(R.id.frame_content, fragment).commit();
            } else {
                //如果已经 add ，隐藏当前 Fragment，直接 show 需要展示的 Fragment 即可
                mTransaction.hide(mCurFragment).show(fragment).commit();
            }
            //当前的主 Fragment 设置更改为已经展示的 Fragment
            mCurFragment = fragment;
        }

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    //再按一次退出程序
    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - Constants.EXIT_TIME) > 2000) {
            Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            Constants.EXIT_TIME = System.currentTimeMillis();
            return;
        }
        finish();
    }
}
