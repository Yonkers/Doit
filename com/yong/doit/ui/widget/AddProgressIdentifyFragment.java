package com.yong.doit.ui.widget;

import android.app.DialogFragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yong.doit.R;
import com.yong.doit.data.bean.ProgressIdentify;
import com.yong.doit.service.ProgressIdentifyService;

public class AddProgressIdentifyFragment extends DialogFragment implements
        OnClickListener {
    private OnDismissListener onDismissListener;

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.edit_progress_identify, null);
        v.findViewById(R.id.btnCancel).setOnClickListener(this);
        v.findViewById(R.id.btnOK).setOnClickListener(this);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != getDialog()) {
            getDialog().setTitle("添加进度鉴定方法");
        }
    }

    private void dismiss(boolean canceled) {
        dismiss();
        if (null != onDismissListener) {
            onDismissListener.onDismiss(canceled);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCancel:
                dismiss(true);
                break;
            case R.id.btnOK:
                TextView name = (TextView) getView().findViewById(
                        R.id.tvIdentifyName);
                String n = name.getText().toString().trim();
                if (!TextUtils.isEmpty(n)) {
                    ProgressIdentifyService.getInstance().save(
                            new ProgressIdentify(n));
                    UIUtil.showToast(getActivity(), "save identify: " + n);
                    dismiss(false);
                } else {
                    UIUtil.showToast(getActivity(), "填写信息为空");
                }
                break;

            default:
                break;
        }
    }

    interface OnDismissListener {
        void onDismiss(boolean canceled);
    }
}
