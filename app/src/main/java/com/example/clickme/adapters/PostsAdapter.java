package com.example.clickme.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clickme.PostViewModel;
import com.example.clickme.R;
import com.example.clickme.models.Post;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder>{

    SharedPreferences sharedPreferences;
    private ArrayList<Post> postsList;
    private PostViewModel viewModel;
    private Context context;
    //    OnItemClickListener listener;
    public PostsAdapter(ArrayList<Post> postsList, Context context) {
        this.postsList = postsList;
        this.context = context;
        viewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(PostViewModel.class);
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new PostsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        Post currentPost = postsList.get(position);

        boolean isPhoto = currentPost.getPhotoUrl() != null;
        if (isPhoto) {
            Picasso.get().load(currentPost.getPhotoUrl()).into(holder.postPic);
        } else {
            holder.postPic.setVisibility(View.GONE);
        }

        Picasso.get().load(currentPost.getUser().getProfilePhotoUrl()).into(holder.profilePic);
        holder.caption.setText(currentPost.getCaption());
        holder.adminName.setText(currentPost.getUser().getName());

        //To show full image on click
        holder.postPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ImageDisplayActivity.class);
                intent.putExtra("uri", currentPost.getPhotoUrl());
                v.getContext().startActivity(intent);
            }
        });

        holder.postMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int position = holder.getAdapterPosition();
//                if (listener != null && position != RecyclerView.NO_POSITION) {
//                    listener.onItemClick(position);
//                }

                PopupMenu popupMenu = new PopupMenu(v.getContext(), holder.postMenu);
                popupMenu.inflate(R.menu.recyclerview_menu);

                popupMenu.setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.edit:
                            Intent intent = new Intent(context, AddNewPostActivity.class);
                            intent.putExtra("caption", currentPost.getCaption());
                            intent.putExtra("image", currentPost.getPhotoUrl());
                            intent.putExtra("edit", true);
                            intent.putExtra("uid", currentPost.getUidPost());
                            context.startActivity(intent);
//                            Toast.makeText(v.getContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
//                            viewModel.editPost(currentPost, context);
                            return true;

                        case R.id.delete:
//                            Toast.makeText(v.getContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                            viewModel.deletePost(currentPost, context);
                            postsList.remove(holder.getAdapterPosition());
                            notifyDataSetChanged();
//                            viewModel.readPosts(postsList, PostsAdapter.this, null);
                            return true;

                        default:
                            return false;
                    }
                });
                popupMenu.show();
            }
        });

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd MMM, yyyy");
        String currentDateTime = sdf.format(new Date());//hh:mm dd MMM, yyyy

        String currentDate = currentDateTime.substring(6);//dd MMM, yyyy
        String postTime = currentPost.getTimeOfPost();//hh:mm dd MMM, yyyy current post
        if (postTime != null) {
            if (!currentDate.equalsIgnoreCase(postTime.substring(9)))
                postTime = postTime.substring(9);//dd MMM, yyyy current post
            else
                postTime = postTime.substring(0, 5);//hh:mm
        }
        holder.time_place.setText(postTime + " | " + currentPost.getLocationOfPostGenerator());

    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public class PostsViewHolder extends RecyclerView.ViewHolder {

        ImageView profilePic, postPic;
        TextView adminName, time_place, caption;
        Button postMenu;
        String phone;

        public PostsViewHolder(@NonNull View itemView) {
            super(itemView);

            profilePic = itemView.findViewById(R.id.adminPicAdminPage);
            postPic = itemView.findViewById(R.id.post_pic);
            adminName = itemView.findViewById(R.id.adminName);
            time_place = itemView.findViewById(R.id.placeOfAdmin);
            caption = itemView.findViewById(R.id.caption_post);
            postMenu = itemView.findViewById(R.id.postMenu);

            sharedPreferences = itemView.getContext().getSharedPreferences("logindata", Context.MODE_PRIVATE);
            phone = sharedPreferences.getString("phone_num", "Number not found!");

            if(Objects.equals(phone, "8840849989") || Objects.equals(phone, "7052800709") || Objects.equals(phone, "1111111111")){
                postMenu.setVisibility(View.VISIBLE);
            }

        }
    }

}
