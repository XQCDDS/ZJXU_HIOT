package com.djj.hiot.ui.main.equipment;

import android.app.Activity;

import com.djj.hiot.base.BasePresenter;
import com.djj.hiot.entity.HolderDeviceEntity;
import com.djj.hiot.http.HttpResult;
import com.djj.hiot.http.HttpService;
import com.djj.hiot.http.ProgressDialogSubscriber;
import com.djj.hiot.http.UserPreferencesHelper;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class EquipmentPresenter extends BasePresenter <EquipmentView>{

    @Inject
    HttpService service;
    @Inject
    Activity activity;
    @Inject
    UserPreferencesHelper preferencesHelper;

    @Inject
    public EquipmentPresenter() {
    }

    public void getDeviceList() {
        Observable<HttpResult<List<HolderDeviceEntity>>> observable = service.getDeviceList(1, preferencesHelper.getTokenValue());
        toSubscribe(observable, new ProgressDialogSubscriber<HttpResult<List<HolderDeviceEntity>>>(activity) {
            @Override
            public void onNext(HttpResult<List<HolderDeviceEntity>> result) {
                getView().stopRefresh();
                if (result != null) {
                    if (result.getStatus() == 1) {
                        getView().showToast(result.getMsg());
                        //获取绑定的设备列表成功
                        getView().refreshData(result.getData());
                    } else {
                        getView().showToast(result.getMsg());
                    }
                } else {
                    getView().showToast("失败result==null");
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                getView().stopRefresh();
            }
        });
    }
}
