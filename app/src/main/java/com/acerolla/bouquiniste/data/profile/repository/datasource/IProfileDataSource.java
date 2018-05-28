package com.acerolla.bouquiniste.data.profile.repository.datasource;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;

/**
 * Created by Acerolla (Evgeniy Solovev).
 */
public interface IProfileDataSource {

    void loadProfile(ResultListener<ProfileData> listener);
    void saveProfile(ProfileData profile);
    void editProfile(ResultListener<String> listener, String userName);
    ProfileData getUserProfileAsync();
    void release();
}
