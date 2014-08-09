package com.yong.doit.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.widget.Toast;
import com.bmob.im.demo.ui.ActivityBase;
import com.bmob.im.demo.ui.FragmentBase;
import com.yong.doit.R;

public abstract class BaseActivity extends ActivityBase {

    protected FragmentBase currentFragment;

    protected int type;

    protected Handler baseHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FragmentBase.WHAT_SWITCH_FRAGMENT:
                    int type = msg.arg1;
                    boolean refresh = (Boolean) msg.obj;
                    switchFragment(type, refresh);
                    break;
                case FragmentBase.WHAT_FINISH_FRAGMENT:
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

                FragmentTransaction ts = getFragmentManager().beginTransaction();
                ts.setCustomAnimations(R.anim.fragment_slide_left_enter,R.anim.fragment_slide_left_exit);

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
//        if (null != getSlidingMenu()) {
//            getSlidingMenu().showContent();
//        }
    }

    private void hideFragment(Fragment fragment) {
        if (null == fragment) return;
        FragmentTransaction ts = getFragmentManager().beginTransaction();
        ts.setCustomAnimations(R.anim.fragment_slide_left_enter,R.anim.fragment_slide_left_exit);
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
                FragmentTransaction ts = getFragmentManager().beginTransaction();
                ts.remove(currentFragment);
                ts.commit();
            }
        }
    }

    protected abstract int getFragmentContainerId();

    protected abstract FragmentBase getFragment(int type);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void toast(String toast){
        Toast.makeText(this,toast,Toast.LENGTH_SHORT).show();
    }

}
