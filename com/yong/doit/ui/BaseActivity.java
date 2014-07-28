package com.yong.doit.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.slidingmenu.lib.app.SlidingFragmentActivity;
import com.yong.doit.ui.widget.BaseFragment;

public abstract class BaseActivity extends SlidingFragmentActivity {

    protected BaseFragment currentFragment;

    protected int type;

    protected Handler baseHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BaseFragment.WHAT_SWITCH_FRAGMENT:
                    int type = msg.arg1;
                    boolean refresh = (Boolean) msg.obj;
                    switchFragment(type, refresh);
                    break;
                case BaseFragment.WHAT_FINISH_FRAGMENT:
                    int t = msg.arg1;
                    finishFragment(t);
                    break;

                default:
                    handleMsg(msg);
                    break;
            }
        }

    };

    protected abstract void handleMsg(Message msg);

    protected void switchFragment(int type, boolean refresh) {
        if (this.type != type) {

            int containerId = getFragmentContainerId();
            if (containerId > 0) {

                hideFragment(currentFragment);

                FragmentTransaction ts = getFragmentManager()
                        .beginTransaction();
                currentFragment = getFragment(type);

                if (currentFragment.isBaseHandlerNull()) {
                    currentFragment.setBaseHandler(baseHandler);
                }
                if (currentFragment.isAdded() && currentFragment.isHidden()) {
                    ts.show(currentFragment);
                    if (refresh) {
                        currentFragment.refreshFragment();
                    }
                } else {
                    ts.add(containerId, currentFragment);
                }
                ts.commit();
                this.type = type;
            }
        }
        if (null != getSlidingMenu()) {
            getSlidingMenu().showContent();
        }
    }

    private void hideFragment(Fragment fragment) {
        if (null == fragment) return;
        FragmentTransaction ts = getFragmentManager()
                .beginTransaction();
        if (null != ts) {
            ts.hide(fragment);
            ts.commit();
        }
    }

    /**
     * 暂时不建议调用
     *
     * @param type
     */
    protected void finishFragment(int type) {
        if (this.type == type) {
            if (null != currentFragment) {
                FragmentTransaction ts = getFragmentManager()
                        .beginTransaction();
                ts.remove(currentFragment);
                ts.commit();
            }
        }
    }

    protected abstract int getFragmentContainerId();

    protected abstract BaseFragment getFragment(int type);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
