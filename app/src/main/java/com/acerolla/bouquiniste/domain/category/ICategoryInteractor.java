package com.acerolla.bouquiniste.domain.category;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;
import com.acerolla.bouquiniste.domain.BaseInteractor;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface ICategoryInteractor extends BaseInteractor {

    void loadCategories(ResultListener<List<CategoryParentData>> listener);
    void loadAdvertsByCategory(ResultListener<List<AdvertData>> listener, int categoryId);
}
