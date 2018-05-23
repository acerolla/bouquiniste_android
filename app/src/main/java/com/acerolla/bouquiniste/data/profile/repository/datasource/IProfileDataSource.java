package com.acerolla.bouquiniste.data.profile.repository.datasource;

import com.acerolla.bouquiniste.data.profile.ResultListener;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;

/**
 * Created by Acerolla (Evgeniy Solovev) on 23.05.2018.
 */
public interface IProfileDataSource {

    void loadProfile(ResultListener<ProfileData> listener);
    void saveProfile(ProfileData profile);
}
