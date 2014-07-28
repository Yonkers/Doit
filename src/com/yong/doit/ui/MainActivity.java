package com.yong.doit.ui;

import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;

import com.yong.doit.R;
import com.yong.doit.ui.widget.*;

public class MainActivity extends BaseActivity {

	private BaseFragment dayTaskFragment;
	private BaseFragment eventListFragment;
	private BaseFragment addEventFragment;
	private BaseFragment addTaskFragment;
	private BaseFragment settingFragment;
	private BaseFragment courseRoadMapFragment;
	private MenuFragment menuFragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		switchFragment(BaseFragment.FRAGMENT_DAY_TASKS, false);

		menuFragment = new MenuFragment();
		menuFragment.setBaseHandler(baseHandler);
		// set the Behind View
//		setBehindContentView(R.layout.menu_frame);
//		getFragmentManager().beginTransaction()
//				.replace(R.id.menu_frame, menuFragment).commit();

		// customize the SlidingMenu
//		SlidingMenu sm = getSlidingMenu();
//		sm.setShadowWidthRes(R.dimen.shadow_width);
//		sm.setShadowDrawable(R.drawable.shadow);
//		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
//		sm.setFadeDegree(0.35f);
//		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
//		sm.setMode(SlidingMenu.LEFT);

//		setSlidingActionBarEnabled(false);
		// getActionBar().setDisplayHomeAsUpEnabled(true);

        NotifyService.getInstance().startCheckTask();
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	@Override
	protected int getFragmentContainerId() {
		return R.id.fragment_content;
	}

	@Override
	protected BaseFragment getFragment(int type) {
		if (BaseFragment.FRAGMENT_DAY_TASKS == type) {
			if (null == dayTaskFragment) {
				dayTaskFragment = new DayTaskFragment();
			}
			currentFragment = dayTaskFragment;
		} else if (BaseFragment.FRAGMENT_EVENT_LIST == type) {
			if (null == eventListFragment) {
				eventListFragment = new EventListFragment();
			}
			currentFragment = eventListFragment;
		} else if (BaseFragment.FRAGMENT_ADD_EVENT == type) {
			if (null == addEventFragment) {
				addEventFragment = new EditEventFragment();
			}
			currentFragment = addEventFragment;
		} else if (BaseFragment.FRAGMENT_ADD_TASK == type) {
			if (null == addTaskFragment) {
				addTaskFragment = new EditTaskFragment();
			}
			currentFragment = addTaskFragment;
		} else if (BaseFragment.FRAGMENT_SETTING == type) {
			if (null == settingFragment) {
				settingFragment = new SettingFragment();
			}
			currentFragment = settingFragment;
		} else if (BaseFragment.FRAGMENT_COURSE_ROAD_MAP == type) {
			if (null == courseRoadMapFragment) {
				courseRoadMapFragment = new CourseRoadMapFragment();
			}
			currentFragment = courseRoadMapFragment;
		}

		return currentFragment;
	}

	@Override
	protected void handleMsg(Message msg) {

	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (null != currentFragment) {
			if (currentFragment.dispatchKeyEvent(event)) {
				return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}

}
