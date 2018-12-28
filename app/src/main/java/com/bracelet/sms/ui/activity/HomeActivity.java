package com.bracelet.sms.ui.activity;

import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import butterknife.BindView;

import com.bracelet.sms.R;
import com.bracelet.sms.ui.adapter.CommonFragmentPagerAdapter;
import com.bracelet.sms.ui.base.BaseActivity;
import com.bracelet.sms.ui.base.BaseFragment;
import com.bracelet.sms.ui.fragment.FragmentFactory;
import com.bracelet.sms.ui.presenter.HomeAtPresenter;
import com.bracelet.sms.ui.presenter.view.IHomeAtView;
import com.bracelet.sms.ui.utils.PopupWindowUtils;
import com.bracelet.sms.ui.utils.ThemeUtils;
import com.bracelet.sms.ui.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity<IHomeAtView, HomeAtPresenter>
        implements ViewPager.OnPageChangeListener, IHomeAtView {

    private static final String TAG = "HomeActivity";

    private List<BaseFragment> mFragmentList = new ArrayList<>(3);

    @BindView(R.id.ibConversationAddMenu)
    ImageButton mIbConversationAddMenu;
    @BindView(R.id.ibBraceletsAddMenu)
    ImageButton mIbBraceletAddMenu;
    @BindView(R.id.vpContent)
    ViewPager mVpContent;

    //底部
    @BindView(R.id.llConversation)
    LinearLayout mLlConversation;
    @BindView(R.id.tvConversationNormal)
    TextView mTvConversationNormal;
    @BindView(R.id.tvConversationPress)
    TextView mTvConversationPress;
    @BindView(R.id.tvConversationTextNormal)
    TextView mTvConversationTextNormal;
    @BindView(R.id.tvConversationTextPress)
    TextView mTvConversationTextPress;
    @BindView(R.id.tvConversationCount)
    public TextView mTvConversationCount;

    @BindView(R.id.llBracelet)
    LinearLayout mLlBracelet;
    @BindView(R.id.tvBraceletNormal)
    TextView mTvBraceletNormal;
    @BindView(R.id.tvBraceletPress)
    TextView mTvBraceletPress;
    @BindView(R.id.tvBraceletTextNormal)
    TextView mTvBraceletTextNormal;
    @BindView(R.id.tvBraceletTextPress)
    TextView mTvBraceletTextPress;
    @BindView(R.id.tvBraceletCount)
    public TextView mTvBraceletCount;
    @BindView(R.id.tvBraceletRedDot)
    public TextView mTvBraceletRedDot;

    @BindView(R.id.llSetting)
    LinearLayout mLlSetting;
    @BindView(R.id.tvSettingNormal)
    TextView mTvSettingNormal;
    @BindView(R.id.tvSettingPress)
    TextView mTvSettingPress;
    @BindView(R.id.tvSettingTextNormal)
    TextView mTvSettingTextNormal;
    @BindView(R.id.tvSettingTextPress)
    TextView mTvSettingTextPress;

    /**
     * 在setContentView()调用之前调用，可以设置WindowFeature
     * (如：this.requestWindowFeature(Window.FEATURE_NO_TITLE);)
     */
    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void initView() {
        super.initView();
        mIbConversationAddMenu.setVisibility(View.VISIBLE);

        //默认选中第一个
        setTransparency();
        mTvConversationPress.getBackground().setAlpha(255);
        mTvConversationTextPress.setTextColor(ThemeUtils.getPressColor(255));

        //设置ViewPager的最大缓存页面
        mVpContent.setOffscreenPageLimit(3);

        mFragmentList.add(FragmentFactory.getInstance().getConversationFragment());
        mFragmentList.add(FragmentFactory.getInstance().getDevicesFragment());
        mFragmentList.add(FragmentFactory.getInstance().getSettingFragment());
        mVpContent.setAdapter(
                new CommonFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList));
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initListener() {
        super.initListener();
        mIbConversationAddMenu.setOnClickListener(v -> {
            //显示或隐藏popupwindow
            View menuView = View.inflate(HomeActivity.this, R.layout.menu_home, null);
            PopupWindow popupWindow =
                    PopupWindowUtils.getPopupWindowAtLocation(menuView, getWindow().getDecorView(),
                            Gravity.TOP | Gravity.RIGHT, UIUtils.dip2Px(5), mAppBar.getHeight() + 30);
            menuView.findViewById(R.id.tvCreateConversation).setOnClickListener(v1 -> {
                //jumpToActivity(CreateGroupActivity.class);
                popupWindow.dismiss();
            });

            menuView.findViewById(R.id.tvAddDevice).setOnClickListener(v1 -> {
                jumpToActivity(AddBraceletActivity.class);
                popupWindow.dismiss();
            });
        });

        mIbBraceletAddMenu.setOnClickListener(v -> {
            jumpToActivity(AddBraceletActivity.class);
        });

        mLlConversation.setOnClickListener(this::bottomBtnClick);
        mLlBracelet.setOnClickListener(this::bottomBtnClick);
        mLlSetting.setOnClickListener(this::bottomBtnClick);

        //mVpContent.setOnPageChangeListener(this);
        mVpContent.addOnPageChangeListener(this);
    }

    @Override
    protected void registerBroadcast() {
        super.registerBroadcast();
    }

    @Override
    protected void unRegisterBroadcast() {
        super.unRegisterBroadcast();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //按back键不finish activity，等同于按home键
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mVpContent != null) {
            updateToolbar(mVpContent.getCurrentItem());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVpContent.removeOnPageChangeListener(this);
    }

    private void updateToolbar(int position) {
        switch (position) {
            case 0:
                setToolbarTitle(UIUtils.getString(R.string.str_conversation));
                mIbConversationAddMenu.setVisibility(View.VISIBLE);
                mIbBraceletAddMenu.setVisibility(View.GONE);
                break;
            case 1:
                setToolbarTitle(UIUtils.getString(R.string.str_bracelet_title));
                mIbConversationAddMenu.setVisibility(View.GONE);
                mIbBraceletAddMenu.setVisibility(View.VISIBLE);
                break;
            case 2:
                setToolbarTitle(UIUtils.getString(R.string.str_setting));
                mIbConversationAddMenu.setVisibility(View.GONE);
                mIbBraceletAddMenu.setVisibility(View.GONE);
                break;
            default:
                setToolbarTitle(UIUtils.getString(R.string.app_name));
                mIbConversationAddMenu.setVisibility(View.GONE);
                mIbBraceletAddMenu.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * 是否让Toolbar有返回按钮(默认可以，一般一个应用中除了主界面，其他界面都是可以有返回按钮的)
     */
    @Override
    protected boolean isToolbarCanBack() {
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {
        //根据ViewPager滑动位置更改透明度
        int diaphaneity_one = (int) (255 * positionOffset);
        int diaphaneity_two = (int) (255 * (1 - positionOffset));
        updateToolbar(position);
        switch (position) {
            case 0:
                mTvConversationNormal.getBackground().setAlpha(diaphaneity_one);
                mTvConversationPress.getBackground().setAlpha(diaphaneity_two);
                mTvBraceletNormal.getBackground().setAlpha(diaphaneity_two);
                mTvBraceletPress.getBackground().setAlpha(diaphaneity_one);
                mTvConversationTextNormal.setTextColor(ThemeUtils.getNormalColor(diaphaneity_one));
                mTvConversationTextPress.setTextColor(ThemeUtils.getPressColor(diaphaneity_two));
                mTvBraceletTextNormal.setTextColor(ThemeUtils.getNormalColor(diaphaneity_two));
                mTvBraceletTextPress.setTextColor(ThemeUtils.getPressColor(diaphaneity_one));
                break;
            case 1:
                mTvBraceletNormal.getBackground().setAlpha(diaphaneity_one);
                mTvBraceletPress.getBackground().setAlpha(diaphaneity_two);
                mTvBraceletTextNormal.setTextColor(ThemeUtils.getNormalColor(diaphaneity_one));
                mTvBraceletTextPress.setTextColor(ThemeUtils.getPressColor(diaphaneity_two));
                mTvSettingNormal.getBackground().setAlpha(diaphaneity_two);
                mTvSettingPress.getBackground().setAlpha(diaphaneity_one);
                mTvSettingTextNormal.setTextColor(ThemeUtils.getNormalColor(diaphaneity_two));
                mTvSettingTextPress.setTextColor(ThemeUtils.getPressColor(diaphaneity_one));
                break;
            case 2:
                mTvSettingNormal.getBackground().setAlpha(diaphaneity_one);
                mTvSettingPress.getBackground().setAlpha(diaphaneity_two);
                mTvSettingTextNormal.setTextColor(ThemeUtils.getNormalColor(diaphaneity_one));
                mTvSettingTextPress.setTextColor(ThemeUtils.getPressColor(diaphaneity_two));
                break;
        }
    }

    @Override
    public void onPageSelected(int position) {
        if (position == 1) {
            //如果是“通讯录”页被选中，则显示快速导航条
            //FragmentFactory.getInstance().getDevicesFragment().showQuickIndexBar(true);
        } else {
            //FragmentFactory.getInstance().getDevicesFragment().showQuickIndexBar(false);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state != ViewPager.SCROLL_STATE_IDLE) {
            //滚动过程中隐藏快速导航条
            //FragmentFactory.getInstance().getDevicesFragment().showQuickIndexBar(false);
        } else {
            //FragmentFactory.getInstance().getDevicesFragment().showQuickIndexBar(true);
        }
    }

    /**
     * 用于创建Presenter和判断是否使用MVP模式(由子类实现)
     */
    @Override
    protected HomeAtPresenter createPresenter() {
        return new HomeAtPresenter(this);
    }

    /**
     * 得到当前界面的布局文件id(由子类实现)
     */
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_home;
    }

    /**
     * 把press图片、文字全部隐藏(设置透明度)
     */
    private void setTransparency() {
        mTvConversationNormal.getBackground().setAlpha(255);
        mTvBraceletNormal.getBackground().setAlpha(255);
        mTvSettingNormal.getBackground().setAlpha(255);

        mTvConversationPress.getBackground().setAlpha(1);
        mTvBraceletPress.getBackground().setAlpha(1);
        mTvSettingPress.getBackground().setAlpha(1);

        mTvConversationTextNormal.setTextColor(ThemeUtils.getNormalColor(255));
        mTvBraceletTextNormal.setTextColor(ThemeUtils.getNormalColor(255));
        mTvSettingTextNormal.setTextColor(ThemeUtils.getNormalColor(255));

        mTvConversationTextPress.setTextColor(ThemeUtils.getPressColor(0));
        mTvBraceletTextPress.setTextColor(ThemeUtils.getPressColor(0));
        mTvSettingTextPress.setTextColor(ThemeUtils.getPressColor(0));
    }

    public void bottomBtnClick(View view) {
        setTransparency();
        switch (view.getId()) {
            case R.id.llConversation:
                setToolbarTitle(UIUtils.getString(R.string.str_conversation));
                mVpContent.setCurrentItem(0, false);
                mTvConversationPress.getBackground().setAlpha(255);
                mTvConversationTextPress.setTextColor(ThemeUtils.getPressColor(255));
                mIbConversationAddMenu.setVisibility(View.VISIBLE);
                mIbBraceletAddMenu.setVisibility(View.GONE);
                break;
            case R.id.llBracelet:
                setToolbarTitle(UIUtils.getString(R.string.str_bracelet));
                mVpContent.setCurrentItem(1, false);
                mTvBraceletPress.getBackground().setAlpha(255);
                mTvBraceletTextPress.setTextColor(ThemeUtils.getPressColor(255));
                mIbConversationAddMenu.setVisibility(View.GONE);
                mIbBraceletAddMenu.setVisibility(View.VISIBLE);
                break;
            case R.id.llSetting:
                setToolbarTitle(UIUtils.getString(R.string.str_setting));
                mVpContent.setCurrentItem(2, false);
                mTvSettingPress.getBackground().setAlpha(255);
                mTvSettingTextPress.setTextColor(ThemeUtils.getPressColor(255));
                mIbConversationAddMenu.setVisibility(View.GONE);
                mIbBraceletAddMenu.setVisibility(View.GONE);
                break;
        }
    }
}
