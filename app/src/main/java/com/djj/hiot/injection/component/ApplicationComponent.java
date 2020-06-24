package com.djj.hiot.injection.component;

import android.app.Application;
import android.content.Context;

import com.djj.hiot.App;
import com.djj.hiot.http.HttpService;
import com.djj.hiot.http.UserPreferencesHelper;
import com.djj.hiot.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(App application);
    Application application();//将Application开放给其他Component使用
    Context context();//将Context开放给其他Component使用
    HttpService httpService();//将HttpService开放给其他Component使用
    UserPreferencesHelper userPreferencesHelper();//将UserPreferencesHelper开放给其他Component使用

}
