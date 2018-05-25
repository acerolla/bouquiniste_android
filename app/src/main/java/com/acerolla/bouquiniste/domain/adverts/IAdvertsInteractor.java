package com.acerolla.bouquiniste.domain.adverts;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.profile.ResultListener;
import com.acerolla.bouquiniste.domain.BaseInteractor;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IAdvertsInteractor extends BaseInteractor {


    void loadAdvertList(ResultListener<List<AdvertData>> listener);
    void getUserAdverts(ResultListener<List<AdvertData>> listener, int userId);
}
