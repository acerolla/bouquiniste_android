package com.acerolla.bouquiniste.domain.profile;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;
import com.acerolla.bouquiniste.domain.BaseInteractor;

import java.util.List;

/**
 * Created by Acerolla (Evgeniy Solovev).
 */
public interface IProfileInteractor extends BaseInteractor {

    void loadProfile(ResultListener<ProfileData> listener);
    void editProfile(ResultListener<ProfileData> listener, String userName);
    void loadUserAdverts(ResultListener<List<AdvertData>> listener, int userId);
    ProfileData getUserProfileAsync();
    boolean isUserLoggedIn();
    void saveProfile(ProfileData data);
    //void saveAdvertToCache(AdvertData data);
}
