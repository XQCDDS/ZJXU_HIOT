package com.djj.hiot.ui.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.djj.hiot.R;
import com.djj.hiot.base.BaseActivity;
import com.djj.hiot.ui.main.MainPresenter;
import com.djj.hiot.ui.main.MainView;
import com.djj.hiot.view.NoSlideViewPager;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<MainView,MainPresenter> implements MainView{

    @Override
    public void showToast(String s) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

    @Inject
    MainPresenter mainPresenter;
    private NoSlideViewPager viewPager;
    private RadioGroup main_radio_group;
    private RadioButton main_radio_message;
    private RadioButton main_radio_equipment;
    private RadioButton main_radio_scene;
    private RadioButton main_radio_mine;

    @Override
    protected MainPresenter createPresenter(){
        return mainPresenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainPresenter.login("huawei","huawei","app");

        setContentView(R.layout.activity_main);
//        findViewById(R.id.bt1).setOnClickListener(this);
//        findViewById(R.id.bt2).setOnClickListener(this);
//        findViewById(R.id.bt3).setOnClickListener(this);
        viewPager = findViewById(R.id.main_vp_select);
        main_radio_group = findViewById(R.id.main_radio_group);
        main_radio_message = findViewById(R.id.main_radio_message);
        main_radio_equipment = findViewById(R.id.main_radio_equipment);
        main_radio_scene = findViewById(R.id.main_radio_scene);
        main_radio_mine = findViewById(R.id.main_radio_mine);

        MainFragmentPagerAdapter pagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setSlidable(false);
        main_radio_message.setChecked(true);
        main_radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.main_radio_message:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.main_radio_equipment:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.main_radio_scene:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.main_radio_mine:
                        viewPager.setCurrentItem(3);
                        break;
                }
            }
        });
    }

    @Override
    protected void injectDependencies() {
        getActivityComponent().inject(this);
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.bt1:
//                mainPresenter.getData("normal");
//                break;
//            case R.id.bt2:
//                mainPresenter.getData("error");
//                break;
//            case R.id.bt3:
//                mainPresenter.getData("complete");
//                break;
//        }
//    }
//
//    @Override
//    public void showData(String data) {
//        Toast.makeText(this,data,Toast.LENGTH_SHORT).show();
//    }
//    @Override
//    public void showError(String error) { Toast.makeText(this,error,Toast.LENGTH_SHORT).show(); }
//    @Override
//    public void showComplete() {
//        Toast.makeText(this,"请求成功",Toast.LENGTH_SHORT).show();
//    }
}
