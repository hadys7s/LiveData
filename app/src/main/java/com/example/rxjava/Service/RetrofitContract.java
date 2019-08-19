package com.example.rxjava.Service;


import java.util.List;

import com.example.rxjava.Model.Post;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitContract {


    @GET("posts")
    Call<List<Post>> getPostsData();
}
