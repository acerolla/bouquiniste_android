package com.acerolla.bouquiniste.data.category.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryChildrenResponse {

    public int id;
    public String title;
}
