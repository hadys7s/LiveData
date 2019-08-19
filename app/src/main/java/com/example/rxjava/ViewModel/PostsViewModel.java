package com.example.rxjava.ViewModel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.rxjava.Model.Post;
import com.example.rxjava.Repositories.PostRepository;

import java.util.ArrayList;
import java.util.List;

public class PostsViewModel extends ViewModel {


    private MutableLiveData<List<Post>> repositoryListOfNames;
    private PostRepository postsRepository;

    public void init() {

        if (repositoryListOfNames != null) {
            return;
        }
        postsRepository = PostRepository.getInstance();
        repositoryListOfNames = postsRepository.getNameList();


    }

    public MutableLiveData<List<Post>> getRepositoryListOfNames() {
        return repositoryListOfNames;
    }
    public LiveData<List<Post>> getFilteredPosts() {

        return Transformations.switchMap(repositoryListOfNames,  this::getUpperCaseStringLiveData);
    }
    private LiveData<List<Post>> getUpperCaseStringLiveData(List<Post>posts) {
        MutableLiveData<List<Post>> liveData = new MutableLiveData<>();
        ArrayList<Post> FilteredList = new ArrayList<>();
        for (Post postItem:
                posts) {
            FilteredList.add(postItem);
        }

        liveData.setValue(FilteredList);
        return liveData;
    }
}
