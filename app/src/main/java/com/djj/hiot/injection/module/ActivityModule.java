package com.djj.hiot.injection.module;

import android.app.Activity;
import android.app.Application;

import com.djj.hiot.App;
import com.djj.hiot.injection.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity){

        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity provideActivity(){

        return this.activity;
    }
}
