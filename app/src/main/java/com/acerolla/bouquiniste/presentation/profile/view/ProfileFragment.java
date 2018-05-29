package com.acerolla.bouquiniste.presentation.profile.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;
import com.acerolla.bouquiniste.di.DiManager;
import com.acerolla.bouquiniste.presentation.profile.presenter.IProfilePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Acerolla (Evgeniy Solovev).
 */
public class ProfileFragment extends Fragment implements IProfileView {

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
    }

    @Override
    public void showEditDialog(String userName) {
        AppCompatEditText edit = new AppCompatEditText(getContext());
        edit.setText(userName);

        new AlertDialog.Builder(getContext())
                .setTitle(R.string.profile_dialog_title)
                .setView(edit)
                .setPositiveButton(
                        android.R.string.yes,
                        (dialog, which) -> mPresenter.handleNameChanged(edit.getText().toString()))
                .setNegativeButton(
                        android.R.string.cancel,
                        null)
                .show();

    }

    @Override
    public void setContentProfile(ProfileData data) {
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
    public void setAdvertsVisibility(boolean isVisible) {
        if (isVisible) {
            mView.setAdvertsVisibility(View.VISIBLE);
        } else {
            mView.setAdvertsVisibility(View.GONE);
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
    public void onDestroy() {
        super.onDestroy();
        mView = null;

        if (mPresenter != null) {
            mPresenter.release();
        }
        mPresenter = null;
    }
}
