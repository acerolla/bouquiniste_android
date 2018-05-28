package com.acerolla.bouquiniste.data.profile.repository;

import com.acerolla.bouquiniste.data.BaseRepository;
import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;

/**
 * Created by Acerolla (Evgeniy Solovev).
 */
public interface IProfileRepository extends BaseRepository {

    void loadProfile(ResultListener<ProfileData> listener);
    void editProfile(ResultListener<ProfileData> listener, String userName);
    ProfileData getUserProfileAsync();
    void saveProfile(ProfileData data);
}
