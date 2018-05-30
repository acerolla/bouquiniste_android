package com.acerolla.bouquiniste.presentation.favorites.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.di.DiManager;
import com.acerolla.bouquiniste.presentation.detail.view.DetailActivity;
import com.acerolla.bouquiniste.presentation.favorites.presenter.FavoritesPresenter;
import com.acerolla.bouquiniste.presentation.favorites.presenter.IFavoritesPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class FavoritesFragment extends Fragment implements IFavoritesView {

    private FavoritesView mView;

    @Inject
    IFavoritesPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = (FavoritesView) inflater.inflate(R.layout.fragment_favorites, container, false);
        mView.initViews();

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DiManager.getFavoritesComponent().inject(this);
        mPresenter.bindView(this);

        setListeners();
    }

    private void setListeners() {
        mView.setItemClickListener(v -> mPresenter.handleItemClicked(mView.getDataByView(v)));
        mView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.handleRefresh();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == DetailActivity.REQUEST_CODE_DETAIL) {
            if (data != null && data.getExtras() != null && data.getExtras().containsKey(DetailActivity.EXTRA_IS_CHANGED)) {
                mPresenter.handleDetailFinished(data.getExtras().getBoolean(DetailActivity.EXTRA_IS_CHANGED));
            }
        }
    }

    @Override
    public void setContentData(List<AdvertData> data) {
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
    public void setEmptyMessageVisibility(boolean isVisible) {
        /*if (isVisible) {
            mView.setEmptyMessageVisibility(View.VISIBLE);
        } else {
            mView.setEmptyMessageVisibility(View.GONE);
        }*/
    }

    @Override
    public void navigateToDetail() {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        startActivityForResult(intent, DetailActivity.REQUEST_CODE_DETAIL);
    }

    @Override
    public void stopRefreshing() {
        mView.setRefreshing(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mView = null;

        if (mPresenter != null) {
            mPresenter.release();
        }
        mPresenter = null;
    }
}
