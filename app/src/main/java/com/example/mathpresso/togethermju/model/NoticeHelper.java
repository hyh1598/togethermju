package com.example.mathpresso.togethermju.model;

import com.example.mathpresso.togethermju.core.AppController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by choijinjoo on 2016. 11. 30..
 */
public class NoticeHelper {

    private static final NoticeHelper instance = new NoticeHelper();

    public static NoticeHelper getInstance() {
        return instance;
    }

    public ArrayList<Notice> getNoticeList() {
        ArrayList<Notice> notice;
        try {
            notice = new ArrayList<>(AppController.getInstance().getDatabaseManager().getSimpleNoticeDataDao().queryForAll());
        } catch (SQLException e) {
            return new ArrayList<>();
        }
        return notice;
    }

    public void setNoticeList(List<Notice> noticeList) {
        try {
            for (Notice notice : noticeList) {
                AppController.getInstance().getDatabaseManager().getSimpleNoticeDataDao().createOrUpdate(notice);
            }
        } catch (SQLException e) {

        }

    }
}
