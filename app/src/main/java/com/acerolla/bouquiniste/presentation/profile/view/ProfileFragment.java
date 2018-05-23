package com.acerolla.bouquiniste.presentation.profile.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acerolla.bouquiniste.data.profile.entity.ProfileData;
import com.acerolla.bouquiniste.data.profile.repository.ProfileRepository;
import com.acerolla.bouquiniste.domain.profile.ProfileInteractor;
import com.acerolla.bouquiniste.presentation.profile.presenter.IProfilePresenter;
import com.acerolla.bouquiniste.presentation.profile.presenter.ProfilePresenter;

/**
 * Created by Acerolla (Evgeniy Solovev) on 23.05.2018.
 */
public class ProfileFragment extends Fragment implements IProfileView {

    private ProfileView mView;

    IProfilePresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = new ProfileView(getContext());
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = new ProfilePresenter(new ProfileInteractor(new ProfileRepository()));
        mPresenter.bindView(this);
    }

    @Override
    public void setContent(ProfileData data) {
        mView.setAvatar(data.getUser());
        mView.setUserName(data.getUser());
        mView.setEmail(data.getEmail());
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
