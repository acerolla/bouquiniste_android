package com.acerolla.bouquiniste.presentation.profile.presenter;

import com.acerolla.bouquiniste.data.profile.ResultListener;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;
import com.acerolla.bouquiniste.domain.profile.IProfileInteractor;
import com.acerolla.bouquiniste.presentation.profile.view.IProfileView;

/**
 * Created by Acerolla (Evgeniy Solovev) on 23.05.2018.
 */
public class ProfilePresenter implements IProfilePresenter {

    private IProfileView mView;
    private IProfileInteractor mInteractor;

    public ProfilePresenter(IProfileInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void bindView(IProfileView view) {
        mView = view;
        mInteractor.loadProfile(result -> mView.setContent(result));
    }

    @Override
    public void release() {
        mView = null;

        if (mInteractor != null) {
            mInteractor.release();
        }
        mInteractor = null;
    }
}
