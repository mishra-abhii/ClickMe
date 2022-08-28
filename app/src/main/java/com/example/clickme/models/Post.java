package com.example.clickme.models;

public class Post {

    private String caption;
    private String photoUrl;
    private String timeOfPost;
    private String locationOfPostGenerator;
    private Member user; // Who posted the post. Admin or Secretaries
    private String uidPost; //Unique id for each post!

    public Post(){}

    public Post(String caption, String photoUrl, Member user, String time, String location) {
        this.caption = caption;
        this.photoUrl = photoUrl;
        this.user = user;
        this.timeOfPost = time;
        this.locationOfPostGenerator = location;
        this.uidPost = time + user.getName();
    }

    public String getCaption() {
        return caption;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public Member getUser() {
        return user;
    }

    public String getTimeOfPost() {
        return timeOfPost;
    }

    public String getLocationOfPostGenerator() {
        return locationOfPostGenerator;
    }

    public String getUidPost() {
        return uidPost;
    }
}
