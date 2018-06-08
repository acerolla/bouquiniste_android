package com.acerolla.bouquiniste.presentation.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.di.DiManager;
import com.acerolla.bouquiniste.presentation.adding.view.AddingFragment;
import com.acerolla.bouquiniste.presentation.adverts.view.AdvertsFragment;
import com.acerolla.bouquiniste.presentation.auth.container.view.LoginContainerActivity;
import com.acerolla.bouquiniste.presentation.favorites.view.FavoritesFragment;
import com.acerolla.bouquiniste.presentation.main.presenter.IMainPresenter;
import com.acerolla.bouquiniste.presentation.profile.view.ProfileFragment;

import javax.inject.Inject;

/**
 * Created by Acerolla (Evgeniy Solovev).
 */
public class MainActivity extends AppCompatActivity implements IMainView {

    private MainView mView;

    @Inject
    IMainPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mView = new MainView(this);
        setContentView(mView);

        mView.setMenuListener(mMenuListener);
        initToolbar();

        DiManager.getMainContainerComponent().inject(this);
        mPresenter.bindView(this);
    }

    private void initToolbar() {
        setSupportActionBar(mView.getToolbar());
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LoginContainerActivity.REQUEST_LOGIN && resultCode == RESULT_OK) {
            mPresenter.handleUserLoggedIn();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main_toolbar, menu);
        mView.initToolbarMenu(((AdvertsFragment) getCurrentFragment())::onSearchTextChanged, menu, this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_toolbar_search:
                mView.getToolbar().setNavigationIcon(R.drawable.ic_arrow_back_black_24px);
                break;
            case R.id.menu_item_toolbar_filter:
                ((AdvertsFragment) getCurrentFragment()).onFilterPressed();
                break;
            case R.id.menu_item_toolbar_clear:
                ((FavoritesFragment) getCurrentFragment()).onClearAllPressed();
                break;
            case R.id.menu_item_toolbar_logout:
                mPresenter.handleLogoutClick();
                break;
        }

        return super.onOptionsItemSelected(item);
    }



    private Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(MainView.ID_CONTENT_FRAME);
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
    public void setSearchButtonVisibility(boolean isVisible) {
        mView.setSearchButtonVisibility(isVisible);
    }

    @Override
    public void setFilterVisibility(boolean isVisible) {
        mView.setFilterVisibility(isVisible);
    }

    @Override
    public void setClearAllVisibility(boolean isVisible) {
        mView.setClearAllVisibility(isVisible);
    }

    @Override
    public void setLogoutVisibility(boolean isVisible) {
        mView.setLogoutVisibility(isVisible);
    }

    @Override
    public void navigateToLogin() {
        Intent intent = new Intent(this, LoginContainerActivity.class);
        startActivityForResult(intent, LoginContainerActivity.REQUEST_LOGIN);
    }


    public void setTitle(String title) {
        mView.getToolbar().setTitle(title);
    }

    public void collapseSearch() {
        mView.collapseSearch();
    }

    @Override
    public void imitateAdvertsClick() {
        mView.getBottomMenu().setSelectedItemId(R.id.action_menu_adverts);
        mPresenter.handleAdvertsClick();
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
