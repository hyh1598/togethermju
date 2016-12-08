package com.example.mathpresso.togethermju;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.example.mathpresso.togethermju.model.Group;

/**
 * Created by mk on 2016-12-03.
 */
public class GroupFilterDialog extends Dialog implements View.OnClickListener {
    Context mContext;
    GroupFilterDialog.OnSelectItemListener mListener;

    RelativeLayout btnAll;
    RelativeLayout btnMine;

    //1: 취업 2:공모전 3:봉사 4:장학금 none : 그외 공지사항
    public static final String FILTER_ALL = "none";
    public static final String FILTER_MINE = "1";



    interface OnSelectItemListener {
        void OnClick(String type);
    }

    public GroupFilterDialog(Context context, GroupFilterDialog.OnSelectItemListener listener) {
        super(context);
        mContext = context;
        mListener = listener;
    }

    public static GroupFilterDialog init(Context context, GroupFilterDialog.OnSelectItemListener listener) {
        GroupFilterDialog dialog = new GroupFilterDialog(context, listener);
        dialog.setup();
        return dialog;
    }

    private void setup() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_group_filter, null);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        btnAll = (RelativeLayout) view.findViewById(R.id.btnAll);
        btnMine = (RelativeLayout) view.findViewById(R.id.btnMine);

        btnAll.setOnClickListener(this);
        btnMine.setOnClickListener(this);

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
                mListener.OnClick(FILTER_MINE);
                dismiss();
                break;

        }
    }
}