package com.acerolla.bouquiniste.domain.profile;

import com.acerolla.bouquiniste.data.profile.ResultListener;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;
import com.acerolla.bouquiniste.data.profile.repository.IProfileRepository;

/**
 * Created by Acerolla (Evgeniy Solovev).
 */
public class ProfileInteractor implements IProfileInteractor {

    private IProfileRepository mRepository;

    public ProfileInteractor(IProfileRepository repository) {
        mRepository = repository;
    }

    @Override
    public void loadProfile(ResultListener<ProfileData> listener) {
        mRepository.loadProfile(listener);
    }

    @Override
    public void editProfile(ResultListener<ProfileData> listener, ProfileData userData) {
        mRepository.editProfile(listener, userData);
    }

    @Override
    public void release() {
        mRepository = null;
    }
}
