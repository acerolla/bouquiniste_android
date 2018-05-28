package com.acerolla.bouquiniste.presentation.main.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.auth.entity.login.LoginData;
import com.acerolla.bouquiniste.data.auth.repository.AuthRepository;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;
import com.acerolla.bouquiniste.data.category.repository.CategoryRepository;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;
import com.acerolla.bouquiniste.presentation.adding.view.AddingFragment;
import com.acerolla.bouquiniste.presentation.adverts.view.AdvertsFragment;
import com.acerolla.bouquiniste.presentation.favorites.view.FavoritesFragment;
import com.acerolla.bouquiniste.presentation.main.presenter.IMainPresenter;
import com.acerolla.bouquiniste.presentation.main.presenter.MainPresenter;
import com.acerolla.bouquiniste.presentation.profile.view.ProfileFragment;

import java.util.List;

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
        //initToolbar();

        mPresenter = new MainPresenter();
        mPresenter.bindView(this);
    }

    private void initToolbar() {
        setSupportActionBar(mView.getToolbar());
    }

    @Override
    public void showProfile() {
        changeFragment(new ProfileFragment());
    }

    private void changeFragment(Fragment fragment) {
        hideCategories();
        getSupportFragmentManager().beginTransaction()
                .replace(MainView.ID_CONTENT_FRAME, fragment)
                .commit();
    }

    private void hideCategories() {
        mView.setFilterVisibility(View.GONE);
    }

    @Override
    public void showAdding() {
        changeFragment(new AddingFragment());
    }

    @Override
    public void showFavorites() {
        changeFragment(new FavoritesFragment());
    }

    @Override
    public void showAdverts() {
        changeFragment(new AdvertsFragment());
    }

    @Override
    public void initToolbarForAdverts(View.OnClickListener listener) {
        mView.setFilterVisibility(View.VISIBLE);
        mView.setCategoryClickListener(listener);
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
        } else if (item.getItemId() == R.id.action_menu_add_advert) {
            mPresenter.handleAddingClick();
            return true;
        } else if (item.getItemId() == R.id.action_menu_favorites) {
            mPresenter.handleFavoritesClick();
            return true;
        } else if (item.getItemId() == R.id.action_menu_adverts) {
            mPresenter.handleAdvertsClick();
            return true;
        }

        return false;
    };
}
