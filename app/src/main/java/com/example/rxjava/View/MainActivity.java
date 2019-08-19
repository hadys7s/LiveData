package com.example.rxjava.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rxjava.Adapters.RecycleAdapter;
import com.example.rxjava.Model.Post;
import com.example.rxjava.R;
import com.example.rxjava.ViewModel.PostsViewModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ArrayList<Post> postsNames = new ArrayList<>();
    RecyclerView recyclerView;
    RecycleAdapter recycleAdapter;
    private Toolbar toolbar;
    PostsViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel =
                ViewModelProviders.of(this).get(PostsViewModel.class);
        viewModel.init();

        observeViewModel(viewModel);
        initRecycle();

        // Attaching the layout to the toolbar object
        toolbar = findViewById(R.id.tool_bar);
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_filter) {


            //observeViewModel(viewModel);

            postsNames.clear();
            if(viewModel.getFilteredPosts().getValue()!=null)
             postsNames.addAll(viewModel.getFilteredPosts().getValue());
            recycleAdapter.notifyDataSetChanged();



            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initRecycle() {

        recyclerView = findViewById(R.id.namesRecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycleAdapter = new RecycleAdapter(postsNames);
        recyclerView.setAdapter(recycleAdapter);
    }

    private void observeViewModel(PostsViewModel viewModel) {

        // Update the list when the data changes


        viewModel.getRepositoryListOfNames().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> names) {
                postsNames.clear();
                postsNames.addAll(names);

               // postsNames.replaceAll(names);
                recycleAdapter.notifyDataSetChanged();


            }


        });
    }

       /* else
        {
            viewModel.getRepositoryListOfNamesFilterd().observe(this, new Observer<Post>() {
                @Override
                public void onChanged(Post names) {
                    if (names != null) {
                        PostsNamesFiltered.add(names);
                    }


                }
            });


        }*/
}



