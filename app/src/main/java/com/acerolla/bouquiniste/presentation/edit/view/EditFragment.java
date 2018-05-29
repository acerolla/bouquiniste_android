package com.acerolla.bouquiniste.presentation.edit.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.di.DiManager;
import com.acerolla.bouquiniste.presentation.category.view.CategoryActivity;
import com.acerolla.bouquiniste.presentation.edit.presenter.IEditPresenter;

import javax.inject.Inject;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class EditFragment extends Fragment implements IEditView {

    public static final int REQUEST_EDIT = 46;
    public static final String EXTRA_ID = "EditFragment.EXTRA_ID";

    private EditView mView;

    @Inject
    IEditPresenter mPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mView = (EditView) inflater.inflate(R.layout.fragment_edit, container, false);
        mView.initViews();
        setListeners();

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setListeners();

        DiManager.getEditComponent().inject(this);
        mPresenter.bindView(this);
    }

    private void setListeners() {
        mView.setCategoryButtonClickListener(v -> mPresenter.handleCategoryClicked());

        ((EditActivity) getActivity()).setCloseClickListener(v -> mPresenter.handleAdvertClosedClicked());

        mView.setEditClickListener(v -> mPresenter.handleAdvertEditClicked(mView.collectData()));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CategoryActivity.REQUEST_CATEGORY && resultCode == Activity.RESULT_OK) {
            mPresenter.handleCategorySelected(
                    data.getExtras().getInt(CategoryActivity.EXTRA_CATEGORY_ID),
                    data.getExtras().getString(CategoryActivity.EXTRA_CATEGORY_TITLE));
        }
    }

    @Override
    public int getExtraId() {
        if (getArguments() != null) {
            if (getArguments().containsKey(EXTRA_ID)) {
                return getArguments().getInt(EXTRA_ID);
            }
        }

        return -1;
    }

    @Override
    public void setContentData(AdvertData data) {
        mView.setContentData(data);
    }

    @Override
    public void notifySuccess(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void notifyFailure(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToCategories() {
        Intent intent = new Intent(getContext(), CategoryActivity.class);
        startActivityForResult(intent, CategoryActivity.REQUEST_CATEGORY);
    }

    @Override
    public void changeCategory(int categoryId, String categoryTitle) {
        mView.changeCategory(categoryId, categoryTitle);
    }

    @Override
    public void navigateBack(boolean isEdited) {
        if (isEdited) {
            getActivity().setResult(Activity.RESULT_OK);
        } else {
            getActivity().setResult(Activity.RESULT_CANCELED);
        }

        getActivity().finish();
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
