package com.acerolla.bouquiniste.presentation.adverts.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acerolla.bouquiniste.presentation.adverts.presenter.AdvertsPresenter;
import com.acerolla.bouquiniste.presentation.adverts.presenter.IAdvertsPresenter;

/**
 * Created by Evgeniy Solovev
 * Date: 24.05.2018
 * Email: solevur@gmail.com
 */
public class AdvertsFragment extends Fragment implements IAdvertsView {

    private AdvertsView mView;

    IAdvertsPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mView = new AdvertsView(getContext());
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = new AdvertsPresenter();
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
