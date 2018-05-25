package com.acerolla.bouquiniste.data.advert.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Evgeniy Solovev
 * Date: 25.05.2018
 * Email: solevur@gmail.com
 */
@JsonIgnoreProperties (ignoreUnknown = true)
public class AdvertResponse {

    public int id;
    public String title;
    public String author;
    public String description;
    public float price;
    public String status;
    public String phone;
    public int category_id;
    public int user_id;
    public String image;
    public boolean is_favorite;
}
