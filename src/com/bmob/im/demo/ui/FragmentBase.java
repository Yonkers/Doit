package com.bmob.im.demo.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import cn.bmob.im.BmobChatManager;
import cn.bmob.im.BmobUserManager;
import cn.bmob.im.util.BmobLog;

import com.bmob.im.demo.CustomApplcation;
import com.yong.doit.R;
import com.bmob.im.demo.view.HeaderLayout;
import com.bmob.im.demo.view.HeaderLayout.HeaderStyle;
import com.bmob.im.demo.view.HeaderLayout.onLeftImageButtonClickListener;
import com.bmob.im.demo.view.HeaderLayout.onRightImageButtonClickListener;

/** Fragmenet 基类
  * @ClassName: FragmentBase
  * @Description: TODO
  * @author smile
  * @date 2014-5-22 下午2:43:50
  */
public abstract class FragmentBase extends Fragment {
    protected static String TAG;
	public BmobUserManager userManager;
	public BmobChatManager manager;

	/**
	 * 公用的Header布局
	 */
	public HeaderLayout mHeaderLayout;

	protected View contentView;
	
	public LayoutInflater mInflater;
	
	private Handler handler = new Handler();
	
	public void runOnWorkThread(Runnable action) {
		new Thread(action).start();
	}

	public void runOnUiThread(Runnable action) {
		handler.post(action);
	}
    public static final int WHAT_SWITCH_FRAGMENT = 100;
    public static final int WHAT_FINISH_FRAGMENT = 101;

    private Handler baseHandler;

    protected int type;

    public int getType() {
        return type;
    }

    public void setBaseHandler(Handler baseHandler) {
        this.baseHandler = baseHandler;
    }

    public boolean isBaseHandlerNull() {
        return baseHandler == null ? true : false;
    }

    public void refreshFragment(){

    }

    protected void switchFragment(int type, boolean refresh) {
        if (null != baseHandler) {
            Message msg = baseHandler.obtainMessage(WHAT_SWITCH_FRAGMENT);
            msg.arg1 = type;
            msg.obj = refresh;
            msg.sendToTarget();
        }
    }

    protected void finishFragment(int type) {
        if (null != baseHandler) {
            Message msg = baseHandler.obtainMessage(WHAT_FINISH_FRAGMENT);
            msg.arg1 = type;
            msg.sendToTarget();
        }
    }
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		mApplication = CustomApplcation.getInstance();
		userManager = BmobUserManager.getInstance(getActivity());
		manager = BmobChatManager.getInstance(getActivity());
		mInflater = LayoutInflater.from(getActivity());
	}

	
	public FragmentBase() {
		
	}

	Toast mToast;

	public void ShowToast(String text) {
		if (mToast == null) {
			mToast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(text);
		}
		mToast.show();
	}

	public void ShowToast(int text) {
		if (mToast == null) {
			mToast = Toast.makeText(getActivity(), text, Toast.LENGTH_LONG);
		} else {
			mToast.setText(text);
		}
		mToast.show();
	}

	
	/** 打Log
	  * ShowLog
	  * @return void
	  * @throws
	  */
	public void ShowLog(String msg){
		BmobLog.i(msg);
	}
	
	public View findViewById(int paramInt) {
		return getView().findViewById(paramInt);
	}

	public CustomApplcation mApplication;

	/**
	 * 只有title initTopBarLayoutByTitle
	 * @Title: initTopBarLayoutByTitle
	 * @throws
	 */
	public void initTopBarForOnlyTitle(String titleName) {
		mHeaderLayout = (HeaderLayout)findViewById(R.id.common_actionbar);
		mHeaderLayout.init(HeaderStyle.DEFAULT_TITLE);
		mHeaderLayout.setDefaultTitle(titleName);
	}

	/**
	 * 初始化标题栏-带左右按钮
	 * 
	 * @return void
	 * @throws
	 */
	public void initTopBarForBoth(String titleName, int rightDrawableId,
			onRightImageButtonClickListener listener) {
		mHeaderLayout = (HeaderLayout)findViewById(R.id.common_actionbar);
		mHeaderLayout.init(HeaderStyle.TITLE_DOUBLE_IMAGEBUTTON);
		mHeaderLayout.setTitleAndLeftImageButton(titleName,
				R.drawable.base_action_bar_back_bg_selector,
				new OnLeftButtonClickListener());
		mHeaderLayout.setTitleAndRightImageButton(titleName, rightDrawableId,
				listener);
	}

	/**
	 * 只有左边按钮和Title initTopBarLayout
	 * 
	 * @throws
	 */
	public void initTopBarForLeft(String titleName) {
		mHeaderLayout = (HeaderLayout)findViewById(R.id.common_actionbar);
		mHeaderLayout.init(HeaderStyle.TITLE_LIFT_IMAGEBUTTON);
		mHeaderLayout.setTitleAndLeftImageButton(titleName,
				R.drawable.base_action_bar_back_bg_selector,
				new OnLeftButtonClickListener());
	}
	
	/** 右边+title
	  * initTopBarForRight
	  * @return void
	  * @throws
	  */
	public void initTopBarForRight(String titleName,int rightDrawableId,
			onRightImageButtonClickListener listener) {
		mHeaderLayout = (HeaderLayout)findViewById(R.id.common_actionbar);
		mHeaderLayout.init(HeaderStyle.TITLE_RIGHT_IMAGEBUTTON);
		mHeaderLayout.setTitleAndRightImageButton(titleName, rightDrawableId,
				listener);
	}
	
	// 左边按钮的点击事件
	public class OnLeftButtonClickListener implements
			onLeftImageButtonClickListener {

		@Override
		public void onClick() {
			getActivity().finish();
		}
	}
	
	/**
	 * 动画启动页面 startAnimActivity
	 * @throws
	 */
	public void startAnimActivity(Intent intent) {
		this.startActivity(intent);
	}
	
	public void startAnimActivity(Class<?> cla) {
		getActivity().startActivity(new Intent(getActivity(), cla));
	}
	
}
