package com.acerolla.bouquiniste.presentation.profile.view;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;

import java.util.List;

/**
 * Created by Acerolla (Evgeniy Solovev).
 */
public interface IProfileView {

    void showEditDialog(String userName);

    void setContentProfile(ProfileData data);
    void setContentAdverts(List<AdvertData> data);

    void setProfileVisibility(boolean isVisible);
    void setAdvertsVisibility(boolean isVisible);
    void setLoaderVisibility(boolean isVisible);
    void setErrorVisibility(boolean isVisible);
    void setEmptyMessageVisibility(boolean isVisible);
}
