package com.djj.hiot.ui.main.message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.djj.hiot.R;
import com.djj.hiot.base.BaseActivity;
import com.djj.hiot.base.BaseFragment;

import javax.inject.Inject;

public class MessageFragment extends BaseFragment<MessageView,MessagePresenter> implements MessageView{
    @Inject
    MessagePresenter messagePresenter;

    @Override
    protected MessagePresenter createPresenter() {
        return messagePresenter;
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message,container,false);
        return view;
    }

    @Override
    protected void injectDependencies() {
        super.injectDependencies();
        ((BaseActivity)getActivity()).getActivityComponent().inject(this);
    }
}
