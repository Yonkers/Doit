package com.yong.doit.ui;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import com.bmob.im.demo.ui.FragmentBase;
import com.bmob.im.demo.ui.fragment.ContactFragment;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yong.doit.R;
import com.yong.doit.ui.widget.DayTaskFragment;
import com.yong.doit.ui.widget.EditTaskFragment;

/**
 * Created by yonkers on 8/9/14.
 */
public class DoitMain extends BaseActivity implements View.OnClickListener{

    public static final int FRAGMENT_DAY_TASKS = 4;// 一天的任务列表界面
    public static final int FRAGMENT_FRIENDS = 5;//
    public static final int FRAGMENT_ADD_DAY_TASK = 6;//

    @ViewInject(R.id.btn_task)
    private Button btnTask;

    @ViewInject(R.id.btn_study)
    private Button btnStudy;

    @ViewInject(R.id.btn_rank_list)
    private Button btnRankList;

    @ViewInject(R.id.btn_friends)
    private Button btnfriends;

    @ViewInject(R.id.btn_setting)
    private Button btnSetting;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doit_main);
        ViewUtils.inject(this);

        btnTask.setOnClickListener(this);
        btnRankList.setOnClickListener(this);
        btnStudy.setOnClickListener(this);
        btnfriends.setOnClickListener(this);
        btnSetting.setOnClickListener(this);

        switchFragment(FRAGMENT_DAY_TASKS,false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_task:
                toast("task");

                break;
            case R.id.btn_rank_list:
                toast("rank list");
                break;
            case R.id.btn_study:
                switchFragment(FRAGMENT_DAY_TASKS,false);
                break;
            case R.id.btn_friends:
                switchFragment(FRAGMENT_FRIENDS,false);
                break;
            case R.id.btn_setting:
                toast("setting");
                break;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void handleMsg(Message msg) {

    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.fragment_content;
    }

    @Override
    protected FragmentBase getFragment(int type) {
        switch (type){
            case FRAGMENT_DAY_TASKS:
                return DayTaskFragment.getInstance();
            case FRAGMENT_FRIENDS:
                return ContactFragment.getInstance();
            case FRAGMENT_ADD_DAY_TASK:
                return EditTaskFragment.getInstance();
        }
        return null;
    }
}
