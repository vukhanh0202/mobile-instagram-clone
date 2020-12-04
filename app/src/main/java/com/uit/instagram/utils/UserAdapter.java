package com.uit.instagram.utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.uit.instagram.R;
import com.uit.instagram.message.ContentActivity;
import com.uit.instagram.model.Message;
import com.uit.instagram.model.UserAccountSettings;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context mContext;
    private List<UserAccountSettings> mUsers;
    private boolean ischat;
    String theLastMessage;

    public UserAdapter(Context mContext, List<UserAccountSettings> mUsers, boolean ischat) {
        this.mContext = mContext;
        this.mUsers = mUsers;
        this.ischat = ischat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_message_user, parent, false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final UserAccountSettings user = mUsers.get(position);
        holder.username.setText(user.getUsername());
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(mContext);

        ImageLoader.getInstance().init(universalImageLoader.getConfig());
        UniversalImageLoader.setImage(user.getProfile_photo(), holder.profile_image, null, "");

        if (ischat) {
            lastMessage(user.getUser_id(), holder.last_msg);
        } else {
            holder.last_msg.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ContentActivity.class);
                intent.putExtra("userid", user.getUser_id());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView username;
        public ImageView profile_image;

        private TextView last_msg;


        public ViewHolder(View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            profile_image = itemView.findViewById(R.id.profile_image);
            last_msg = itemView.findViewById(R.id.last_msg);
        }
    }

    private void lastMessage(final String userid, final TextView last_msg) {
        theLastMessage = "default";
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("messages");
        if(firebaseUser!=null){
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Message chat = dataSnapshot.getValue(Message.class);
                        if (chat.getReceiver().equals(firebaseUser.getUid()) && chat.getSender().equals(userid) ||
                                chat.getReceiver().equals(userid) && chat.getSender().equals(firebaseUser.getUid())) {
                            theLastMessage = chat.getMessage();
                        }
                    }

                    switch (theLastMessage) {
                        case "default":
                            last_msg.setText("No Message");
                            break;
                        default:
                            last_msg.setText(theLastMessage);
                            break;
                    }

                    theLastMessage = "default";
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }
}
