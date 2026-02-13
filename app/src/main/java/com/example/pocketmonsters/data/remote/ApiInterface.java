package com.example.pocketmonsters.data.remote;

import com.example.pocketmonsters.model.UserDetail;
import com.example.pocketmonsters.model.UserRanking;
import com.example.pocketmonsters.model.UserID;
import com.example.pocketmonsters.model.UserNearby;
import com.example.pocketmonsters.model.VirtualItemActivated;
import com.example.pocketmonsters.model.VirtualItemDetail;
import com.example.pocketmonsters.model.VirtualItemNearby;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    //Using paths for API call that need specific header parameter
    //Using query for API call that need specific query parameter
    //Using FormUrlEncoded for API call that post with a specific header parameter
    //Using field for API call that post with a specific query parameter (usually the sid in this case)

    @POST("users")
    Call<UserID> getUserID();

    @GET("objects")
    Call<List<VirtualItemNearby>> getVirtualItemsNearby(@Query("sid") String sid, @Query("lat") double lat, @Query("lon") double lon);

    @GET("objects/{id}")
    Call<VirtualItemDetail> getVirtualItemDetail(@Path("id") String id, @Query("sid") String sid);

    @FormUrlEncoded
    @POST("objects/{id}/activate")
    Call<VirtualItemActivated> activatedVirtualItem(@Path("id") String id, @Field("sid") String sid);

    //no need to be implemented
    //    @POST("objects")
    //    Call<VirtualItemActivated> createVirtualItem(@Query("sid") String sid, @Query("lat") double lat, @Query("lon") double lon);

    //    @DELETE("objects/{id}")
    //    Call<Void> deleteVirtualItem(@Path("id") String id, @Query("sid") String sid);

    @GET("users")
    Call<List<UserNearby>> getUserNearby(@Query("sid") String sid, @Query("lat") String lat, @Query("lon") String lon);

    @GET("users/{id}")
    Call<UserDetail> getUserDetail(@Path("id") String id, @Query("sid") String sid);

    //Creting the update user API call, one for each to update the user's name, picture, and positionShare
    //TODO: check if null variables are passed trough patch or if they are ignored so we can use only one patch call
    @FormUrlEncoded
    @PATCH("users/{id}")
    Call<Void> editUserName(@Path("id") String id, @Field("sid") String sid, @Field("name") String name);

    @FormUrlEncoded
    @PATCH("users/{id}")
    Call<Void> editProfilePicture(@Path("id") String id, @Field("sid") String sid, @Field("profilePicture") String profilePicture);

    @FormUrlEncoded
    @PATCH("users/{id}")
    Call<Void> editPositionShare(@Path("id") String id, @Field("sid") String sid, @Field("positionShare") Boolean positionShare);

    @GET
    Call<List<UserRanking>> getRanking(@Query("sid") String sid);
}
