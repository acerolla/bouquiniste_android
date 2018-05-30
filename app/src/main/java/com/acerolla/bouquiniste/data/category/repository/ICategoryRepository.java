package com.acerolla.bouquiniste.data.category.repository;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;
import com.acerolla.bouquiniste.data.BaseRepository;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface ICategoryRepository extends BaseRepository {

    void loadCategories(ResultListener<List<CategoryParentData>> listener);
    void loadAdvertsByCategory(ResultListener<List<AdvertData>> listResultListener, int categoryId);

    void clearCategory();
}
