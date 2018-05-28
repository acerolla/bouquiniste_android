package com.acerolla.bouquiniste.domain.profile;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;
import com.acerolla.bouquiniste.data.profile.repository.IProfileRepository;
import com.acerolla.bouquiniste.domain.adverts.IAdvertsInteractor;

import java.util.List;

/**
 * Created by Acerolla (Evgeniy Solovev).
 */
public class ProfileInteractor implements IProfileInteractor {

    private IProfileRepository mRepository;
    private IAdvertsInteractor mAdvertsInteractor;

    public ProfileInteractor(IProfileRepository repository, IAdvertsInteractor advertsInteractor) {
        mRepository = repository;
        mAdvertsInteractor = advertsInteractor;
    }

    @Override
    public void loadProfile(ResultListener<ProfileData> listener) {
        mRepository.loadProfile(listener);
    }

    @Override
    public void editProfile(ResultListener<ProfileData> listener, String userName) {
        mRepository.editProfile(listener, userName);
    }

    @Override
    public void loadUserAdverts(ResultListener<List<AdvertData>> listener, int userId) {
        mAdvertsInteractor.loadUserAdverts(listener, userId);
    }

    @Override
    public ProfileData getUserProfileAsync() {
        return mRepository.getUserProfileAsync();
    }

    @Override
    public boolean isUserLoggedIn() {
        return mRepository.getUserProfileAsync() != null;
    }

    @Override
    public void saveProfile(ProfileData data) {
        mRepository.saveProfile(data);
    }

    @Override
    public void release() {
        mRepository = null;

        if (mAdvertsInteractor != null) {
            mAdvertsInteractor.release();
        }
        mAdvertsInteractor = null;
    }
}
