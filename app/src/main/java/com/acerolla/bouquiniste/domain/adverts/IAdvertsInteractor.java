package com.acerolla.bouquiniste.domain.adverts;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;
import com.acerolla.bouquiniste.domain.BaseInteractor;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IAdvertsInteractor extends BaseInteractor {


    void loadAdvertList(ResultListener<List<AdvertData>> listener);
    void loadUserAdverts(ResultListener<List<AdvertData>> listener, int userId);
    void saveAdvertToCache(AdvertData advertData);
    void loadCategories(ResultListener<List<CategoryParentData>> listener);
    void loadAdvertsByCategories(ResultListener<List<AdvertData>> listener, int categoryId);
}
