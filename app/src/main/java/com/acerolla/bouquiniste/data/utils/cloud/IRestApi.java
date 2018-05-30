package com.acerolla.bouquiniste.data.utils.cloud;

import com.acerolla.bouquiniste.data.advert.entity.AdvertRequest;
import com.acerolla.bouquiniste.data.advert.entity.AdvertResponse;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentResponse;
import com.acerolla.bouquiniste.data.profile.entity.ProfileResponse;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IRestApi {

    @POST("api/register")
    Call<BaseResponseObject<ProfileResponse>> register(@Query("email") String email);

    @POST("api/login")
    Call<BaseResponseObject<ProfileResponse>> login(@Query("email") String email, @Query("password") String password);

    @POST("api/logout")
    Call<BaseResponseObject> logout();



    @GET("api/user")
    Call<BaseResponseObject<ProfileResponse>> getUser();

    @PUT("api/user")
    Call<BaseResponseObject<ProfileResponse>> editUser(@Body ProfileResponse response);

    @GET("api/user/favorites")
    Call<BaseResponseObject<List<AdvertResponse>>> getFavorites();

    @GET("api/user/{id}/adverts")
    Call<BaseResponseObject<List<AdvertResponse>>> getUserAdverts(@Path("id") int userId);



    @GET("api/advert")   //?page=3
    Call<BaseResponseObject<List<AdvertResponse>>> getAdverts();

    @GET("api/advert/{id}")
    Call<BaseResponseObject<AdvertResponse>> getAdvert(@Path("id") int id);

    @POST("api/advert")
    Call<BaseResponseObject<AdvertResponse>> postAdvert(@Body Map<String, String> requestBody);

    @Multipart
    @POST("api/advert")//TODO: post with file
    Call<BaseResponseObject<AdvertResponse>> postAdvert(@PartMap Map<String, String> requestBody, @Part MultipartBody.Part file);

    @PUT("api/advert/{id}")
    Call<BaseResponseObject<AdvertResponse>> editAdvert(@Path("id") int advertId, @Body Map<String, String> requestBody);

    @PUT("api/advert/{id}/favorite")
    Call<BaseResponseObject<AdvertResponse>> addToFavorite(@Path("id") int advertId);

    @PUT("api/advert/{id}/unfavorite")
    Call<BaseResponseObject<AdvertResponse>> removeFromFavorite(@Path("id") int advertId);



    @GET("api/categories")
    Call<BaseResponseObject<List<CategoryParentResponse>>> getCategoryList();

    @GET("api/categories/{id}/adverts")
    Call<BaseResponseObject<List<AdvertResponse>>> getAdvertsByCategory(@Path("id") int categoryId);
}
