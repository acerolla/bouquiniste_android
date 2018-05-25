package com.acerolla.bouquiniste.data.utils.cloud;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Evgeniy Solovev
 * Date: 25.05.2018
 * Email: solevur@gmail.com
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponseObject<T> {

    public T data;
}
