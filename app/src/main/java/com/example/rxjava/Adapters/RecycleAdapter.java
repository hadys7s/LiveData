package com.example.rxjava.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rxjava.Model.Post;
import com.example.rxjava.R;

import java.util.ArrayList;
import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MainHolder> {
    private ArrayList<Post> Names;


    public RecycleAdapter(ArrayList<Post> posts )
    {

        Names=posts;

    }
    public void updateData(ArrayList<Post> FilteredPosts)
    {

        Names.clear();
        Names.addAll(FilteredPosts);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.item,viewGroup,false);
        return new MainHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder mainHolder, int i) {
        mainHolder.textView.setText(Names.get(i).getName());
        mainHolder.id.setText(Integer.toString(Names.get(i).getId()));

    }

    @Override
    public int getItemCount() {
        return Names.size();
    }

    class MainHolder extends RecyclerView.ViewHolder {

        TextView textView,id;


        public MainHolder(@NonNull View itemView) {
            super(itemView);
            textView= itemView.findViewById(R.id.Name);
            id= itemView.findViewById(R.id.id);
        }
    }
}



