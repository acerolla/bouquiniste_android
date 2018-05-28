package com.acerolla.bouquiniste.data.profile.repository.datasource;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class ProfileMemoryCacheDataSource implements IProfileDataSource {

    private ProfileData mProfile;

    @Override
    public void loadProfile(ResultListener<ProfileData> listener) {
        //ignore
    }

    @Override
    public void saveProfile(ProfileData profile) {
        mProfile = profile;
    }

    @Override
    public void editProfile(ResultListener<String> listener, String userName) {
        //ignore
    }

    @Override
    public ProfileData getUserProfileAsync() {
        return mProfile;
    }

    @Override
    public void release() {
        mProfile = null;
    }
}
