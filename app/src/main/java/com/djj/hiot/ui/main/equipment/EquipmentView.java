package com.djj.hiot.ui.main.equipment;

import com.djj.hiot.base.BaseView;
import com.djj.hiot.entity.HolderDeviceEntity;

import java.util.List;

public interface EquipmentView extends BaseView {
    void showToast(String msg);
    void refreshData(List<HolderDeviceEntity> data);
    void stopRefresh();
}
