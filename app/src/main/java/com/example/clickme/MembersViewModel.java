package com.example.clickme;

import android.app.Application;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.clickme.adapters.MembersAdapter;
import com.example.clickme.firebase.Database;
import com.example.clickme.models.Member;

import java.util.ArrayList;

public class MembersViewModel extends AndroidViewModel {

    private Database memberDatabase;

    public MembersViewModel(@NonNull Application application) {
        super(application);
        memberDatabase = new Database();
    }

    public void readMembers(ArrayList<Member> membersList, MembersAdapter membersAdapter, ProgressBar memberLoaderBar) {
        memberDatabase.readMembers(membersList, membersAdapter, memberLoaderBar);
    }

}
