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

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.di.DiManager;
import com.acerolla.bouquiniste.presentation.adding.presenter.IAddingPresenter;

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
        mView = new AddingView(getContext());
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DiManager.getAddingComponent().inject(this);
        mPresenter.bindView(this);
        setListeners();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_FILE_CHOOSER && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                mPresenter.handleFileChoosed(data.getData());
            }
        }
    }

    private void setListeners() {
        mView.setAddClickListener(v -> mPresenter.handleAddClick());
        mView.setImageClickListener(v -> mPresenter.handleUploadClick());
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
