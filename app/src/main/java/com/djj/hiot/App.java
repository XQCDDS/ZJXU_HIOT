package com.djj.hiot;


import android.app.Application;

import com.djj.hiot.injection.component.ApplicationComponent;
import com.djj.hiot.injection.component.DaggerApplicationComponent;
import com.djj.hiot.injection.module.ApplicationModule;

public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        initInjector();
    }

    private void initInjector(){
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        component.inject(this);
    }

    public ApplicationComponent component(){
        return component;
    }
}
