package com.training.testapp.apiclient;


import com.training.testapp.models.APIResponse;
import com.training.testapp.models.CommentsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface InstaAPI {

    @GET("v1/media/search")
    Call<APIResponse> loadAllPosts(@Query("lat") double latitude, @Query("lng") double longitude,
                                   @Query("access_token") String accessToken, @Query("scope") String scope, @Query("distance") double distance);


    @GET("v1/media/{mediaId}/comments")
    Call<CommentsResponse> getRecentComments(@Path("mediaId") String mediaId, @Query("access_token") String accessToken);
}
