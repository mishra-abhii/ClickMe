package com.example.clickme.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clickme.AddNewPostActivity;
import com.example.clickme.R;
import com.example.clickme.adapters.PostsAdapter;
import com.example.clickme.models.Member;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

public class AdminFragment extends Fragment {


    private ArrayList<Member> adminList;
    private RecyclerView recyclerView;
    private PostsAdapter postsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton addPostBtn;
    SharedPreferences sharedPreferences;
    private String phone;

    public AdminFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_admin, container, false);

        sharedPreferences = this.getActivity().getSharedPreferences("logindata", Context.MODE_PRIVATE);
        phone = sharedPreferences.getString("phone_num", "Number not found!");

        addPostBtn = v.findViewById(R.id.addPost);

        if(Objects.equals(phone, "8840849989") || Objects.equals(phone, "7052800709") || Objects.equals(phone, "1111111111")){
            addPostBtn.setVisibility(View.VISIBLE);
        }

        addPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddNewPostActivity.class));
                finishThisFragmentAndActivity();
            }
        });


        adminList = new ArrayList<Member>();

        Member member = new Member("SEC_ADMIN_NAME", "Fasdfse", "Mumbai, Maharashtra");
        adminList.add(member);
        return v;
    }

    private void finishThisFragmentAndActivity() {
        getActivity().finish();
    }

//    private void setUpRecyclerView(View view) {
//        recyclerView = view.findViewById(R.id.postRecyclerView);
//        layoutManager = new LinearLayoutManager(getActivity());
////        postsAdapter = new PostsAdapter(adminList); MemberAdapter use
//
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(postsAdapter);
//    }
}