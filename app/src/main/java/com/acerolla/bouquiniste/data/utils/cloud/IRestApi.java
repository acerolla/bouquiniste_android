package com.acerolla.bouquiniste.data.utils.cloud;

import android.text.BoringLayout;

import com.acerolla.bouquiniste.data.advert.entity.AdvertRequest;
import com.acerolla.bouquiniste.data.advert.entity.AdvertResponse;
import com.acerolla.bouquiniste.data.profile.entity.ProfileResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IRestApi {

    @POST("/register")
    Call<BaseResponseObject<ProfileResponse>> register(@Query("email") String email);

    @POST("/login")
    Call<BaseResponseObject<ProfileResponse>> login(@Query("email") String email, @Query("password") String password);

    @POST("/logout")
    Call<BaseResponseObject> logout();

    @POST("/advert")
    Call<BaseResponseObject<AdvertResponse>> postAdvert(@Body AdvertRequest requestBody);

    @GET("/advert")
    Call<BaseResponseObject<List<AdvertResponse>>> getAdverts();

    @GET("/advert/{id}")
    Call<BaseResponseObject<AdvertResponse>> getAdvert(@Path("id") int id);

    @GET("/user")
    Call<BaseResponseObject<ProfileResponse>> getUser();

    @GET("/user/favorites")
    Call<BaseResponseObject<List<AdvertResponse>>> getFavorites();

    @GET("/user/{id}/adverts")
    Call<BaseResponseObject<List<AdvertResponse>>> getUserAdverts(@Path("id") int userId);

    @Multipart
    @PUT("/advert/{id}")
    Call<BaseResponseObject<AdvertResponse>> editAdvert(@Path("id") int advertId, @Body AdvertRequest requestBody);

    @PUT("/user")
    Call<BaseResponseObject<ProfileResponse>> editUser(@Body ProfileResponse response);

    @PUT("/advert/{id}/favorite")
    Call<BaseResponseObject<AdvertResponse>> addToFavorite(@Path("id") int advertId);

    @PUT("/advert/{id}/unfavorite")
    Call<BaseResponseObject<AdvertResponse>> removeFromFavorite(@Path("id") int advertId);
}
