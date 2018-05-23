package com.acerolla.bouquiniste.data.profile.repository.datasource;

import com.acerolla.bouquiniste.data.profile.ResultListener;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;

/**
 * Created by Acerolla (Evgeniy Solovev) on 23.05.2018.
 */
public class ProfileCloudDataSource implements IProfileDataSource {

    @Override
    public void loadProfile(ResultListener<ProfileData> listener) {
        //now_ignore
        listener.onResult(null);
    }

    @Override
    public void saveProfile(ProfileData profile) {
        //ignore
    }
}
