package com.acerolla.bouquiniste.data.category.repository;

import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;
import com.acerolla.bouquiniste.data.profile.BaseRepository;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface ICategoryRepository extends BaseRepository {

    List<CategoryParentData> getCategories();
}
