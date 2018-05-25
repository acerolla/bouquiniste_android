package com.acerolla.bouquiniste.data.profile.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileResponse {

    public int id;
    public String token;
    public String name;
    public String email;
}
