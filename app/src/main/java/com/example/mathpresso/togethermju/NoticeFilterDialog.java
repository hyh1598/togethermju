package com.example.mathpresso.togethermju;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;


/**
 * Created by choijinjoo on 2016. 11. 30..
 */
public class NoticeFilterDialog extends Dialog implements View.OnClickListener {
    Context mContext;
    OnSelectItemListener mListener;

    RelativeLayout btnAll;
    RelativeLayout btnWork;
    RelativeLayout btnContest;
    RelativeLayout btnVoluntary;
    RelativeLayout btnScholarship;

    //1: 취업 2:공모전 3:봉사 4:장학금 none : 그외 공지사항
    public static final String FILTER_ALL = "none";
    public static final String FILTER_WORK = "1";
    public static final String FILTER_CONTEST = "2";
    public static final String FILTER_VOLUNTARY = "3";
    public static final String FILTER_SCHOLARSHIP = "4";


    interface OnSelectItemListener {
        void OnClick(String type);
    }

    public NoticeFilterDialog(Context context, OnSelectItemListener listener) {
        super(context);
        mContext = context;
        mListener = listener;
    }

    public static NoticeFilterDialog init(Context context, OnSelectItemListener listener) {
        NoticeFilterDialog dialog = new NoticeFilterDialog(context, listener);
        dialog.setup();
        return dialog;
    }

    private void setup() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_notice_filter, null);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        btnAll = (RelativeLayout) view.findViewById(R.id.btnAll);
        btnWork = (RelativeLayout) view.findViewById(R.id.btnWork);
        btnContest = (RelativeLayout) view.findViewById(R.id.btnContest);
        btnVoluntary = (RelativeLayout) view.findViewById(R.id.btnVoluntary);
        btnScholarship = (RelativeLayout) view.findViewById(R.id.btnScholarship);

        btnAll.setOnClickListener(this);
        btnWork.setOnClickListener(this);
        btnContest.setOnClickListener(this);
        btnVoluntary.setOnClickListener(this);
        btnScholarship.setOnClickListener(this);

        setContentView(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAll:
                mListener.OnClick(FILTER_ALL);
                dismiss();
                break;
            case R.id.btnContest:
                mListener.OnClick(FILTER_CONTEST);
                dismiss();
                break;
            case R.id.btnWork:
                mListener.OnClick(FILTER_WORK);
                dismiss();
                break;
            case R.id.btnVoluntary:
                mListener.OnClick(FILTER_VOLUNTARY);
                dismiss();
                break;
            case R.id.btnScholarship:
                mListener.OnClick(FILTER_SCHOLARSHIP);
                dismiss();
                break;
        }
    }
}
