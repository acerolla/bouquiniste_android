package com.acerolla.bouquiniste.presentation.favorites.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acerolla.bouquiniste.presentation.favorites.presenter.FavoritesPresenter;
import com.acerolla.bouquiniste.presentation.favorites.presenter.IFavoritesPresenter;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class FavoritesFragment extends Fragment implements IFavoritesView {

    private FavoritesView mView;

    IFavoritesPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = new FavoritesView(getContext());
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = new FavoritesPresenter();
        mPresenter.bindView(this);
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
