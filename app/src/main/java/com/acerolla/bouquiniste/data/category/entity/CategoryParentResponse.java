package com.acerolla.bouquiniste.data.category.entity;

import com.acerolla.bouquiniste.data.utils.cloud.BaseResponseObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryParentResponse {

    public int id;

    public String title;

    public BaseResponseObject<List<CategoryChildrenResponse>> children;

}
