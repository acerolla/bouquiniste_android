package com.acerolla.bouquiniste.presentation.profile.presenter;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;
import com.acerolla.bouquiniste.data.profile.repository.ProfileRepository;
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
            if (resultProfile != null && mView != null) {
                if (resultProfile.getId() == ProfileRepository.DEFAULT_ID) {
                    mView.setLoginButtonVisibility(true);
                } else {
                    mView.setLoginButtonVisibility(false);
                }
                mView.setContentProfile(resultProfile);
                mView.setProfileVisibility(true);
            }
            if (resultProfile != null && mInteractor != null) {
                mInteractor.loadUserAdverts(resultAdverts -> {
                    if (resultAdverts != null && mView != null) {
                        if (!resultAdverts.isEmpty()) {
                            mView.setContentAdverts(resultAdverts);
                            //mView.setAdvertsVisibility(true);
                            //mView.setLoaderVisibility(false);
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
    public void handleNameChanged(String oldUserName, String newUserName) {
        if (!newUserName.isEmpty()) {
            mView.setUserName(newUserName);
            if (oldUserName.equals(newUserName)) {
                mView.showUsefulToast("Нет смысла менять на то же самое (:");
                return;
            }

            mInteractor.editProfile(result -> {
                if (result == null) {
                    mView.setUserName(oldUserName);
                    mView.showUsefulToast("Имя не было изменено из-за ошибки ответа сервера.");
                } else {
                    mView.showUsefulToast("Имя успешно изменено!");
                }
            }, newUserName);
        }
    }

    @Override
    public void handleItemClicked(AdvertData data) {
        mView.navigateToEdit(data.getId());
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
