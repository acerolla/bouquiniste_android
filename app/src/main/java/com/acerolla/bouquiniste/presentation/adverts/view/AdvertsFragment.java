package com.acerolla.bouquiniste.presentation.adverts.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;
import com.acerolla.bouquiniste.di.DiManager;
import com.acerolla.bouquiniste.presentation.adverts.presenter.IAdvertsPresenter;
import com.acerolla.bouquiniste.presentation.detail.view.DetailActivity;
import com.acerolla.bouquiniste.presentation.main.view.IMainView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class AdvertsFragment extends Fragment implements IAdvertsView {

    private AdvertsView mView;

    @Inject
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

        DiManager.getAdvertsComponent().inject(this);
        mPresenter.bindView(this);

        setListeners();
        setupToolbar();
    }

    private void setListeners() {
        mView.setItemClickListener(v -> mPresenter.handleItemClicked(mView.getDataByView(v)));
    }

    private void setupToolbar() {
        ((IMainView) getActivity()).initToolbarForAdverts(v ->
            mPresenter.handleFilterPressed());
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
    public void setContentData(List<AdvertData> adverts) {
        mView.setContentData(adverts);
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
    public void navigateToDetail() {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        startActivityForResult(intent, DetailActivity.REQUEST_CODE_DETAIL);
    }

    @Override
    public void showCategory() {
        mView.setCategoryVisibility(View.VISIBLE);
    }

    @Override
    public void setCategoryData(List<CategoryParentData> data) {
        mView.setCategoryData(data);
    }

    @Override
    public void showCategoryErrorToast() {
        Toast.makeText(getContext(), R.string.adverts_toast_category_error, Toast.LENGTH_SHORT).show();
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
