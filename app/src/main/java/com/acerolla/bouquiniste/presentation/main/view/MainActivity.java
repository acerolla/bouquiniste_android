package com.acerolla.bouquiniste.presentation.main.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.presentation.main.presenter.IMainPresenter;
import com.acerolla.bouquiniste.presentation.main.presenter.MainPresenter;
import com.acerolla.bouquiniste.presentation.profile.view.ProfileFragment;

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
        mView.setMenuListener(mMenuListener);

        mPresenter = new MainPresenter();
        mPresenter.bindView(this);
    }

    @Override
    public void showProfile() {
        changeFragment(new ProfileFragment());
    }

    private void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(MainView.ID_CONTENT_FRAME, fragment)
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mView = null;

        if (mPresenter != null) {
            mPresenter.release();
        }
        mPresenter = null;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mMenuListener = item -> {
        if (item.getItemId() == R.id.action_menu_profile) {
            mPresenter.handleProfileItemClick();
            return true;
        }

        return false;
    };
}
