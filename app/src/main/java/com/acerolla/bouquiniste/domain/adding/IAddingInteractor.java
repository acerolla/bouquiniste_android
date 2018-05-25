package com.acerolla.bouquiniste.domain.adding;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.profile.ResultListener;
import com.acerolla.bouquiniste.domain.BaseInteractor;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IAddingInteractor extends BaseInteractor {

    void postAdvert(ResultListener<AdvertData> listener, AdvertData advertData);
}
