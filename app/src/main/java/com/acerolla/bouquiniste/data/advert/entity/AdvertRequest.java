package com.acerolla.bouquiniste.data.advert.entity;

import java.io.File;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class AdvertRequest {

    public int id;
    public String title;
    public String author;
    public String description;
    public float price;
    public String status;
    public String phone;
    public int category_id;
    public int user_id;
    public File image;
}
