package com.example.rxjava.Repositories;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.example.rxjava.Model.Post;
import com.example.rxjava.Service.RetrofitClient;
import com.example.rxjava.Service.RetrofitContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepository {

    private static PostRepository instance;
    final MutableLiveData<List<Post>> data = new MutableLiveData<>();

    public static PostRepository getInstance() {
        if (instance == null) {

            instance = new PostRepository();
        }
        return instance;


    }

    public void setPostsList() {


        RetrofitContract endpoint = RetrofitClient.getclient(PostRepository.this).create(RetrofitContract.class);
        endpoint.getPostsData().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

//                ArrayList<Post> posts=new ArrayList();
//
//                for (Post postResponseItem : response.body()) {
//
//                   Post post=new Post();
//                    post.setName(postResponseItem.getName());
//                    post.setId(postResponseItem.getId());
//                    posts.add(post);
//                    Log.v("posts",data.toString());
//
//                }
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }


    public MutableLiveData<List<Post>> getNameList() {
        setPostsList();
        return data;


    }



}
