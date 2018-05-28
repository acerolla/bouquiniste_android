package com.acerolla.bouquiniste.domain.adding;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;
import com.acerolla.bouquiniste.domain.BaseInteractor;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IAddingInteractor extends BaseInteractor {

    void postAdvert(ResultListener<AdvertData> listener, AdvertData advertData);
    void loadCategories(ResultListener<List<CategoryParentData>> listener);
    void saveImagePath(String path);
    String getImagePath();
}
