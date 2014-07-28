package com.yong.doit.ui.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yong.doit.R;

public class UIUtil {

    public static void showToast(Context context, String msg) {
        if (null != context) {
            Toast t = new Toast(context);
            View v = LayoutInflater.from(context).inflate(R.layout.toast, null);
            t.setView(v);
            TextView text = (TextView) v.findViewById(R.id.toast_text);
            text.setText(msg);
            t.setGravity(Gravity.BOTTOM, 0, 185);
            t.setDuration(Toast.LENGTH_SHORT);
            t.show();
        }
    }

}
