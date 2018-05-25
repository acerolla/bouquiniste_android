package com.acerolla.bouquiniste.domain.profile;

import com.acerolla.bouquiniste.data.profile.ResultListener;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;
import com.acerolla.bouquiniste.domain.BaseInteractor;

/**
 * Created by Acerolla (Evgeniy Solovev).
 */
public interface IProfileInteractor extends BaseInteractor {

    void loadProfile(ResultListener<ProfileData> listener);
    void editProfile(ResultListener<ProfileData> listener, ProfileData userData);
}
