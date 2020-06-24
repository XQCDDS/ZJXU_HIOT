package com.djj.hiot.ui.main.mine;

import android.support.v4.app.Fragment;

import com.djj.hiot.base.BaseView;

public interface MineView extends BaseView {
    void showUserHead(String imgUrl);

    void showUserName(String username);

    void showUserEmail(String email);

    void showToast(String msg);

    void logoutSucceed();

    Fragment getFragment();
}
