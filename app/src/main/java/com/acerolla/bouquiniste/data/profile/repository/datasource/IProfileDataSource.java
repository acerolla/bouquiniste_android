package com.acerolla.bouquiniste.data.profile.repository.datasource;

import com.acerolla.bouquiniste.data.profile.ResultListener;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;

/**
 * Created by Acerolla (Evgeniy Solovev).
 */
public interface IProfileDataSource {

    void loadProfile(ResultListener<ProfileData> listener);
    void saveProfile(ProfileData profile);
    void editProfile(ResultListener<ProfileData> listener, ProfileData userData);
}
