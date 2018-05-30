package com.acerolla.bouquiniste.presentation.adding.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;
import com.acerolla.bouquiniste.di.DiManager;
import com.acerolla.bouquiniste.presentation.adding.presenter.IAddingPresenter;
import com.acerolla.bouquiniste.presentation.category.view.CategoryActivity;
import com.acerolla.bouquiniste.presentation.detail.view.DetailActivity;
import com.acerolla.bouquiniste.presentation.main.view.IMainView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class AddingFragment extends Fragment implements IAddingView {

    private static final int REQUEST_FILE_CHOOSER = 42;

    private AddingView mView;

    @Inject
    IAddingPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mView = (AddingView) inflater.inflate(R.layout.fragment_adding, container, false);
        mView.initViews();

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DiManager.getAddingComponent().inject(this);
        mPresenter.bindView(this);
        setListeners();
    }

    private void setListeners() {
        mView.setAddClickListener(v -> mPresenter.handleAddClick());
        mView.setImageClickListener(v -> mPresenter.handleUploadClick());
        mView.setCategoryButtonCLickListener(v -> mPresenter.handleCategoryButtonClicked());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_FILE_CHOOSER && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                mPresenter.handleFileChoosed(data.getData());
            }
        } else if (requestCode == DetailActivity.REQUEST_CODE_DETAIL) {
            mPresenter.handleDetailFinished();
        } else if (requestCode == CategoryActivity.REQUEST_CATEGORY && resultCode == Activity.RESULT_OK) {
            mPresenter.handleCategorySelected(
                    data.getExtras().getInt(CategoryActivity.EXTRA_CATEGORY_ID),
                    data.getExtras().getString(CategoryActivity.EXTRA_CATEGORY_TITLE));
        }
    }

    @Override
    public void showChooseFileActivity() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_FILE_CHOOSER);
    }

    @Override
    public AdvertData collectData() {
        return mView.collectData();
    }

    @Override
    public void navigateToDetail(int advertId) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_ID, advertId);
        startActivityForResult(intent, DetailActivity.REQUEST_CODE_DETAIL);
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
    public void changeFragment() {
        if (getActivity() != null) {
            ((IMainView) getActivity()).showAdverts();
        }
    }

    @Override
    public void navigateToCategories() {
        Intent intent = new Intent(getActivity(), CategoryActivity.class);
        startActivityForResult(intent, CategoryActivity.REQUEST_CATEGORY);
    }

    @Override
    public void showCategory(int id, String title) {
        mView.setCategory(id, title);
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
