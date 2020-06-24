package com.djj.hiot.ui.login;


import android.app.Activity;
import android.text.TextUtils;

import com.djj.hiot.base.BasePresenter;
import com.djj.hiot.entity.LoginEntity;
import com.djj.hiot.http.HttpResult;
import com.djj.hiot.http.HttpService;
import com.djj.hiot.http.ProgressDialogSubscriber;
import com.djj.hiot.http.UserPreferencesHelper;

import javax.inject.Inject;

import rx.Observable;

public class LoginPresenter extends BasePresenter<LoginView> {

    @Inject
    HttpService service;
    @Inject
    Activity activity;
    @Inject
    UserPreferencesHelper preferencesHelper;

    @Inject
    public LoginPresenter(){
    }

    public void login(final String username,final String password) {
        if (TextUtils.isEmpty(username)) {
            getView().showToast("请输入用户名");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            getView().showToast("请输入密码");
            return;
        }
        Observable<HttpResult<LoginEntity>> observable = service.login(username, password, "app");
        toSubscribe(observable, new ProgressDialogSubscriber<HttpResult<LoginEntity>>(activity) {
            @Override
            public void onNext(HttpResult<LoginEntity> result) {
                if (result != null) {
                    if (result.getStatus() == 1 && result.getData() != null) {
                        getView().loginSucceed();
                        getView().showToast("登录成功");
                        //登录成功，保存Token和UUID
                        preferencesHelper.putTokenValue(result.getData().getTokenValue());
                        preferencesHelper.putUuid(result.getData().getUuid());
                        //保存登录的用户名和密码
                        preferencesHelper.putUserName(username);
                        preferencesHelper.putPassword(password);
                    } else {
                        getView().showToast(result.getMsg());
                    }
                } else {
                    getView().showToast("登录失败result==null");
                }
            }
        });
    }

    //如果保存了用户名密码，即已经登录过，则自动登录
    public void autoLogin(){
        String userName = preferencesHelper.getUserName();
        String password = preferencesHelper.getPassword();
        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)){
            login(userName,password);
        }
    }
}
