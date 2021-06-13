package com.example.testrequests;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;


public interface TabadolAPI {
//    @Headers("Content-Type: application/json")
    @GET("jposts")
    Call<List<Post>> getPosts();


    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> login(@FieldMap Map<String, String> fields);

    @GET("jcurrentuser")
    Call<User> getCurrentUser();

    @POST("testpostjson")
    Call<ResponseJson> testPostJson(@Body Login login);

    @GET("jprofile/{id}")
    Call<User> getUser(@Path("id") long id);


    @GET
    Call<User> getUser(@Url String url);

}
