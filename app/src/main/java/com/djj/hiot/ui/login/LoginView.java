package com.djj.hiot.ui.login;

import com.djj.hiot.base.BaseView;

public interface LoginView extends BaseView {
    void showToast(String msg);
    void loginSucceed();
}
