package com.acerolla.bouquiniste.presentation.profile.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;
import com.acerolla.bouquiniste.di.DiManager;
import com.acerolla.bouquiniste.presentation.edit.view.EditActivity;
import com.acerolla.bouquiniste.presentation.edit.view.EditFragment;
import com.acerolla.bouquiniste.presentation.profile.presenter.IProfilePresenter;
import com.acerolla.bouquiniste.presentation.utils.ValuesConverter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Acerolla (Evgeniy Solovev).
 */
public class ProfileFragment extends Fragment implements IProfileView {

    private static final int ZERO = 0;

    private ProfileView mView;

    @Inject
    IProfilePresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mView = (ProfileView) inflater.inflate(R.layout.fragment_profile, container, false);
        mView.initViews();

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DiManager.getProfileComponent().inject(this);
        mPresenter.bindView(this);

        setListeners();
    }

    private void setListeners() {
        mView.setNameClickListener(v -> mPresenter.handleNameClicked());
        mView.setItemClickListener(v -> mPresenter.handleItemClicked(mView.getDataByView(v)));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EditFragment.REQUEST_EDIT && resultCode == Activity.RESULT_OK) {

        }
    }

    @Override
    public void showEditDialog(String userName) {
        AppCompatEditText edit = new AppCompatEditText(getContext());
        int paddingLeftTopRight = ValuesConverter.dp2px(ValuesConverter.DP_30);
        edit.setPadding(paddingLeftTopRight, paddingLeftTopRight, paddingLeftTopRight, ZERO);
        edit.setGravity(Gravity.BOTTOM);
        edit.setBackgroundColor(Color.WHITE);
        edit.setText(userName);

        new AlertDialog.Builder(getContext())
                .setTitle(R.string.profile_dialog_title)
                .setView(edit)
                .setPositiveButton(
                        "Изменить",
                        (dialog, which) -> mPresenter.handleNameChanged(mView.getUserName(), edit.getText().toString()))
                .setNegativeButton(
                        "Отмена",
                        null)
                .show();

    }

    @Override
    public void setLoginButtonVisibility(boolean isVisible) {
        if (isVisible) {
            mView.setLoginButtonVisibility(View.VISIBLE);
        } else {
            mView.setLoginButtonVisibility(View.GONE);
        }
    }

    @Override
    public void setContentProfile(ProfileData data) {
        //mView.setLoginButtonVisibility(View.GONE);
        mView.setAvatar(data.getName());
        mView.setUserName(data.getName());
        mView.setEmail(data.getEmail());
    }

    @Override
    public void setContentAdverts(List<AdvertData> data) {
        mView.setContentAdverts(data);
    }

    @Override
    public void setProfileVisibility(boolean isVisible) {
        if (isVisible) {
            mView.setProfileVisibility(View.VISIBLE);
        } else {
            mView.setProfileVisibility(View.GONE);
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
        if (isVisible) {
            mView.setEmptyMessageVisibility(View.VISIBLE);
        } else {
            mView.setEmptyMessageVisibility(View.GONE);
        }
    }

    @Override
    public void setUserName(String userName) {
        mView.setUserName(userName);
    }

    @Override
    public void showUsefulToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToEdit(int advertId) {
        Intent intent = new Intent(getContext(), EditActivity.class);
        intent.putExtra(EditFragment.EXTRA_ID, advertId);
        startActivityForResult(intent, EditFragment.REQUEST_EDIT);
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
