package com.acerolla.bouquiniste.presentation.main.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.acerolla.bouquiniste.presentation.main.presenter.IMainPresenter;

/**
 * Created by Acerolla (Evgeniy Solovev) on 22.05.2018.
 */
public class MainActivity extends AppCompatActivity implements IMainView {

    private MainView mView;

    IMainPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mView = new MainView(this);
        setContentView(mView);
    }
}
