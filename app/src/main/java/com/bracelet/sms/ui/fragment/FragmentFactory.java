package com.bracelet.sms.ui.fragment;

/**
 * @Package : com.hunter.im.ui.fragment
 * @Auther : tellt
 * @Date : 2018/11/12 13:45
 * @Description: 主界面4个Fragment工厂
 */
public class FragmentFactory {

    static FragmentFactory mInstance;

    private FragmentFactory() {
    }

    public static FragmentFactory getInstance() {
        if (mInstance == null) {
            synchronized (FragmentFactory.class) {
                if (mInstance == null) {
                    mInstance = new FragmentFactory();
                }
            }
        }
        return mInstance;
    }

    private ConversationFragment mConversationFragment;
    private BraceletsFragment mBraceletsFragment;
    private SettingFragment mSettingFragment;

    public ConversationFragment getConversationFragment() {
        if (mConversationFragment == null) {
            synchronized (FragmentFactory.class) {
                if (mConversationFragment == null) {
                    mConversationFragment = new ConversationFragment();
                }
            }
        }
        return mConversationFragment;
    }

    public BraceletsFragment getDevicesFragment() {
        if (mBraceletsFragment == null) {
            synchronized (FragmentFactory.class) {
                if (mBraceletsFragment == null) {
                    mBraceletsFragment = new BraceletsFragment();
                }
            }
        }
        return mBraceletsFragment;
    }

    public SettingFragment getSettingFragment() {
        if (mSettingFragment == null) {
            synchronized (FragmentFactory.class) {
                if (mSettingFragment == null) {
                    mSettingFragment = new SettingFragment();
                }
            }
        }
        return mSettingFragment;
    }
}
