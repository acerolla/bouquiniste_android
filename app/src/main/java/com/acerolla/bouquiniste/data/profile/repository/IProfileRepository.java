package com.acerolla.bouquiniste.data.profile.repository;

import com.acerolla.bouquiniste.data.profile.BaseRepository;
import com.acerolla.bouquiniste.data.profile.ResultListener;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;

/**
 * Created by Acerolla (Evgeniy Solovev) on 23.05.2018.
 */
public interface IProfileRepository extends BaseRepository {

    void loadProfile(ResultListener<ProfileData> listener);
}
