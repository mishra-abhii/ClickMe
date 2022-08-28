package com.example.clickme.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.clickme.PostViewModel;
import com.example.clickme.R;
import com.example.clickme.adapters.PostsAdapter;
import com.example.clickme.models.Post;

import java.util.ArrayList;


public class PostFragment extends Fragment {

    private ArrayList<Post> postsList;
    private RecyclerView recyclerView;
    private PostsAdapter postsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private PostViewModel postViewModel;
    private ProgressBar postLoaderBar;

    public PostFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        postsList = new ArrayList<Post>();
        View view = inflater.inflate(R.layout.fragment_post, container, false);
        postLoaderBar = view.findViewById(R.id.postLoaderBar);
        setUpRecyclerView(view);

        postViewModel = new ViewModelProvider(this).get(PostViewModel.class);
        postViewModel.readPosts(postsList, postsAdapter, postLoaderBar);
        return view;
    }

    private void setUpRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.postRecyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        postsAdapter = new PostsAdapter(postsList, getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(postsAdapter);
    }
}