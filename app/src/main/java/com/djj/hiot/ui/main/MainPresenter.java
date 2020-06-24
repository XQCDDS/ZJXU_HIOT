package com.djj.hiot.ui.main;



import android.app.Activity;

import com.djj.hiot.base.BasePresenter;
import com.djj.hiot.entity.LoginEntity;
import com.djj.hiot.http.HttpResultFunc;
import com.djj.hiot.http.HttpService;
import com.djj.hiot.http.ProgressDialogSubscriber;
import com.djj.hiot.http.UserPreferencesHelper;

import javax.inject.Inject;

import rx.Observable;

public class MainPresenter extends BasePresenter<MainView> {
    @Inject
    HttpService service;
    @Inject
    Activity activity;
    @Inject
    UserPreferencesHelper preferencesHelper;

    @Inject
    public MainPresenter(){ }

    public void login(final String username,final String password,String loginCode){
        Observable<LoginEntity> observable =service.login(username,password,loginCode)
                .map(new HttpResultFunc<LoginEntity>());
        toSubscribe(observable,new ProgressDialogSubscriber<LoginEntity>(activity){
            @Override
            public void onNext(LoginEntity loginEntity){
                getView().showToast(loginEntity.getUuid());
                //登录成功，保存Token和UUID
                preferencesHelper.putTokenValue(loginEntity.getTokenValue());
                preferencesHelper.putUuid(loginEntity.getUuid());
                //保存登录时的用户名和密码
                preferencesHelper.putUserName(username);
                preferencesHelper.putPassword(password);
            }
        });
    }
//    public void getData(String param){
//        DataModel.getNetData(param, new DataModelCallback() {
//            @Override
//            public void onSuccess(String data) {
//                getView().showData(data);
//            }
//
//            @Override
//            public void onError(String error) {
//                getView().showError(error);
//            }
//
//            @Override
//            public void onComplete() {
//                getView().showComplete();
//            }
//        });
//    }
}
