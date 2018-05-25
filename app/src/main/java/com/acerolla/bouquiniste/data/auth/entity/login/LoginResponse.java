package com.acerolla.bouquiniste.data.auth.entity.login;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse {

    public String email;
    public String password;
}
