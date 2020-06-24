package com.djj.hiot.injection.component;

import com.djj.hiot.injection.PerActivity;
import com.djj.hiot.injection.module.ActivityModule;
import com.djj.hiot.ui.login.LoginActivity;
import com.djj.hiot.ui.main.MainActivity;
import com.djj.hiot.ui.main.equipment.EquipmentFragment;
import com.djj.hiot.ui.main.message.MessageFragment;
import com.djj.hiot.ui.main.mine.MineFragment;
import com.djj.hiot.ui.main.scene.SceneFragment;
import com.djj.hiot.ui.register.RegisterActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity activity);

    void inject(MessageFragment messageFragment);

    void inject(EquipmentFragment equipmentFragment);

    void inject(MineFragment mineFragment);

    void inject(SceneFragment sceneFragment);

    void inject(LoginActivity loginActivity);

    void inject(RegisterActivity registerActivity);

//    void inject(ZxingActivity zxingActivity);
//
//    void inject(DeviceDetailActivity deviceDetailActivity);
//
//    void inject(DataHistoryActivity dataHistoryActivity);
//
//    void inject(GpsHistoryActivity gpsHistoryActivity);
}
