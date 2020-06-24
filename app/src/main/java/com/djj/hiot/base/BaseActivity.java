package com.djj.hiot.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;

import com.djj.hiot.App;
import com.djj.hiot.injection.component.ActivityComponent;
import com.djj.hiot.injection.component.ApplicationComponent;

import com.djj.hiot.injection.component.DaggerActivityComponent;
import com.djj.hiot.injection.module.ActivityModule;

public abstract class BaseActivity<V extends BaseView,P extends BasePresenter> extends AppCompatActivity implements BaseView{

    private P mvpPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        mvpPresenter = createPresenter();
        if(mvpPresenter!=null){
            mvpPresenter.setView((V)this);
        }
    }

    protected abstract void injectDependencies();

    protected abstract P createPresenter();

    private ActivityComponent mActivityComponent;
    public ActivityComponent getActivityComponent(){
        if (mActivityComponent==null){
            mActivityComponent=DaggerActivityComponent.builder()
                    .activityModule(getActivityModule())
                    .applicationComponent(getApplicationComponent())
                    .build();
        }

        return mActivityComponent;
    }

    private ApplicationComponent getApplicationComponent(){
        return ((App)getApplication()).component();
    }

    private ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter!=null){
            mvpPresenter.detachView();
        }
    }
}
