package com.uit.instagram.message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uit.instagram.R;
import com.uit.instagram.model.MessageList;
import com.uit.instagram.model.User;
import com.uit.instagram.model.UserAccountSettings;
import com.uit.instagram.utils.UserAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <VuKhanh> [18520903@gm.uit.edu.vn]
 * @since 11/29/2020
 */
public class MessageFragment extends Fragment {

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<UserAccountSettings> mUsers;

    FirebaseUser fuser;
    DatabaseReference reference;

    private List<MessageList> usersList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fuser = FirebaseAuth.getInstance().getCurrentUser();

        usersList = new ArrayList<>();


        reference = FirebaseDatabase.getInstance().getReference(getString(R.string.dbname_messages_list)).child(fuser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usersList.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    MessageList chatlist = dataSnapshot.getValue(MessageList.class);
                    usersList.add(chatlist);
                }
                messageList();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;


    }

    private void messageList() {
        mUsers = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference(getString(R.string.dbname_user_account_settings));
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mUsers.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    UserAccountSettings user = dataSnapshot.getValue(UserAccountSettings.class);
                    for(MessageList message: usersList){
                        if(user.getUser_id().equals(message.getId()) ){
                            mUsers.add(user);
                        }
                    }
                }
                userAdapter = new UserAdapter(getContext(), mUsers, true);
                recyclerView.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}