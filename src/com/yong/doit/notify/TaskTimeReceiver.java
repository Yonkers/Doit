package com.yong.doit.notify;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.yong.doit.ui.widget.NotifyService;
import com.yong.doit.ui.widget.UIUtil;

/**
 * Created by yonkers on 5/12/14.
 */
public class TaskTimeReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(NotifyService.ACTION)) {
            UIUtil.showToast(context,"reveice alarm event");
        }
    }
}
