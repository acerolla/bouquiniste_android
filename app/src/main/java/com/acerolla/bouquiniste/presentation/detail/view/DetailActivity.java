package com.acerolla.bouquiniste.presentation.detail.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.di.DiManager;
import com.acerolla.bouquiniste.presentation.detail.presenter.IDetailPresenter;

import javax.inject.Inject;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class DetailActivity extends AppCompatActivity implements IDetailView {

    public static final int REQUEST_CODE_DETAIL = 1;
    public static final String EXTRA_IS_CHANGED = "DetailActivity.EXTRA_IS_CHANGED";

    private DetailView mView;

    @Inject
    IDetailPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_detail);
        mView = findViewById(R.id.root_detail);
        mView.initViews();


        DiManager.getDetailComponent().inject(this);
        mPresenter.bindView(this);

        initToolbar();
        setListeners();
    }

    @SuppressLint("RestrictedApi")
    private void initToolbar() {
        setSupportActionBar(mView.getToolbar());

        /*getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24px);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);*/
    }

    private void setListeners() {
        mView.setFavoriteClickListener(v -> mPresenter.handleFavoriteClick());
    }

    @Override
    public void setContentData(AdvertData data) {
        mView.setContentData(data);
    }

    @Override
    public void setContentVisibility(boolean isVisible) {
        if (isVisible) {
            mView.setContentVisibility(View.VISIBLE);
        } else {
            mView.setContentVisibility(View.GONE);
        }
    }

    @Override
    public void setLoaderVisibility(boolean isVisible) {
        if (isVisible) {
            mView.setLoaderVisibility(View.VISIBLE);
        } else {
            mView.setLoaderVisibility(View.GONE);
        }
    }

    @Override
    public void setErrorVisibility(boolean isVisible) {
        if (isVisible) {
            mView.setErrorVisibility(View.VISIBLE);
        } else {
            mView.setErrorVisibility(View.GONE);
        }
    }

    @Override
    public void changeFavoriteStatus(boolean isFavorite) {

    }

    @Override
    public void finish() {
        setResult(RESULT_OK);
        super.finish();
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
}
