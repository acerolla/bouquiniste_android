package com.acerolla.bouquiniste.presentation.edit.view;

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
import com.acerolla.bouquiniste.di.DiManager;
import com.acerolla.bouquiniste.presentation.category.view.CategoryActivity;
import com.acerolla.bouquiniste.presentation.edit.presenter.IEditPresenter;
import com.acerolla.bouquiniste.presentation.utils.dialogs.DialogEditDescription;
import com.acerolla.bouquiniste.presentation.utils.dialogs.DialogEditLocation;
import com.acerolla.bouquiniste.presentation.utils.dialogs.DialogEditPhone;
import com.acerolla.bouquiniste.presentation.utils.dialogs.DialogEditPrice;
import com.acerolla.bouquiniste.presentation.utils.dialogs.DialogEditTitle;

import java.util.Locale;

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
        mView.setItemClickListener(v -> mPresenter.handleCategoryClicked());

        mView.setItemClickListener(v -> {
            switch (v.getId()) {
                case R.id.tv_title:
                case R.id.tv_author:
                    DialogEditTitle dialog1 = new DialogEditTitle();
                    dialog1.setListener((title, author) -> {
                        mView.setTitle(title);
                        mView.setAuthor(author);
                    });
                    dialog1.show(getActivity().getFragmentManager(), "");
                    break;
                case R.id.tv_price:
                    DialogEditPrice dialog2 = new DialogEditPrice();
                    dialog2.setListener(price -> mView.setPrice(price));
                    dialog2.show(getActivity().getFragmentManager(), "");
                    break;
                case R.id.ll_description:
                    DialogEditDescription dialog3 = new DialogEditDescription();
                    dialog3.setListener(price -> mView.setDescription(price));
                    dialog3.show(getActivity().getFragmentManager(), "");
                    break;
                case R.id.ll_category:
                    mPresenter.handleCategoryClicked();
                    break;
                case R.id.ll_phone:
                    DialogEditPhone dialog4 = new DialogEditPhone();
                    dialog4.setListener(price -> mView.setPhone(price));
                    dialog4.show(getActivity().getFragmentManager(), "");
                    break;
                case R.id.ll_location:
                    DialogEditLocation dialog5 = new DialogEditLocation();
                    dialog5.setListener(price -> mView.setLocation(price));
                    dialog5.show(getActivity().getFragmentManager(), "");
                    break;
            }
        });

        ((EditActivity) getActivity()).setCloseClickListener(v -> mPresenter.handleAdvertClosedClicked());
        ((EditActivity) getActivity()).setShareClickListener(() -> mPresenter.handleShareClick());

        mView.setEditClickListener(v -> mPresenter.handleAdvertEditClicked(mView.collectData()));
        mView.setRefreshListener(v -> mPresenter.handleRefreshClick());
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
    public void share() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, generateText(mView.collectData()));
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    private String generateText(AdvertData data) {
        String body = "";
        body += "Продаю книгу.\r\n";
        body += data.getAuthor() + "  —  " + data.getTitle() + "\r\n";
        body += String.format(Locale.getDefault(), "За %.0f %s \r\n", data.getPrice(), "\u20BD");
        body += data.getPhone() + " (" + data.getLocation() + ")";

        return body;
    }

    @Override
    public void setContentVisibility(boolean isVisible) {
        if (isVisible) {
            mView.setContentVisibility(View.VISIBLE);
            ((EditActivity) getActivity()).setFabVisibility(View.VISIBLE);
        } else {
            mView.setContentVisibility(View.INVISIBLE);
            ((EditActivity) getActivity()).setFabVisibility(View.GONE);
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
    public void onDestroy() {
        super.onDestroy();
        mView = null;

        if (mPresenter != null) {
            mPresenter.release();
        }
        mPresenter = null;
    }
}
