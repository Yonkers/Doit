package com.yong.doit.ui.widget;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.bmob.im.demo.ui.FragmentBase;
import com.yong.doit.R;
import com.yong.doit.explorer.ChoosePDFActivity;

public class SettingFragment extends FragmentBase implements OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_setting, null);
        v.findViewById(R.id.pdfTest).setOnClickListener(this);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pdfTest:
            	Intent it = new Intent(getActivity(),ChoosePDFActivity.class);
            	startActivity(it);
                break;

            default:
                break;
        }
    }

    @Override
    public void refreshFragment() {

    }


}
