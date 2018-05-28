package com.acerolla.bouquiniste.presentation.profile.presenter;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;
import com.acerolla.bouquiniste.domain.profile.IProfileInteractor;
import com.acerolla.bouquiniste.presentation.profile.view.IProfileView;

/**
 * Created by Acerolla (Evgeniy Solovev).
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
        mInteractor.loadProfile(resultProfile -> {
            if (resultProfile != null && mInteractor != null) {
                mInteractor.loadUserAdverts(resultAdverts -> {
                    if (resultAdverts != null && mView != null) {
                        if (!resultAdverts.isEmpty()) {
                            mView.setContentProfile(resultProfile);
                            mView.setProfileVisibility(true);
                            mView.setContentAdverts(resultAdverts);
                            mView.setAdvertsVisibility(true);
                            mView.setLoaderVisibility(false);
                        } else {
                            mView.setEmptyMessageVisibility(true);
                            mView.setLoaderVisibility(false);
                        }
                    }
                }, resultProfile.getId());
            } else {
                if (mView != null) {
                    mView.setLoaderVisibility(false);
                    mView.setErrorVisibility(true);
                }
            }
        });
    }

    @Override
    public void handleNameClicked() {
        if (mInteractor.isUserLoggedIn()) {
            mView.showEditDialog(mInteractor.getUserProfileAsync().getName());
        }
    }

    @Override
    public void handleNameChanged(String userName) {
        if (!userName.isEmpty()) {
            mInteractor.editProfile(new ResultListener<ProfileData>() {
                @Override
                public void onResult(ProfileData result) {

                }
            }, userName);
        }
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
