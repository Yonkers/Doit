package com.yong.doit.ui.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.yong.doit.R;

public class MenuFragment extends BaseFragment {

    private OnClickListener onClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.menuDayTasks:
                    switchFragment(BaseFragment.FRAGMENT_DAY_TASKS, true);
                    break;
                case R.id.menuAllEvents:
                    switchFragment(BaseFragment.FRAGMENT_EVENT_LIST, true);
                    break;
                case R.id.menuMyHorner:
                    switchFragment(BaseFragment.FRAGMENT_DAY_TASKS, true);
                    break;
                case R.id.menuSetting:
                    switchFragment(BaseFragment.FRAGMENT_SETTING, true);
                    break;
                case R.id.menuCourseList:
                	switchFragment(BaseFragment.FRAGMENT_COURSE_ROAD_MAP, true);
                	break;
                default:
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, null);
        v.findViewById(R.id.menuAllEvents).setOnClickListener(onClickListener);
        v.findViewById(R.id.menuDayTasks).setOnClickListener(onClickListener);
        v.findViewById(R.id.menuMyHorner).setOnClickListener(onClickListener);
        v.findViewById(R.id.menuSetting).setOnClickListener(onClickListener);
        v.findViewById(R.id.menuCourseList).setOnClickListener(onClickListener);
        v.findViewById(R.id.menuStudyRecord).setOnClickListener(onClickListener);
        return v;
    }

    @Override
    public void refreshFragment() {

    }

}
