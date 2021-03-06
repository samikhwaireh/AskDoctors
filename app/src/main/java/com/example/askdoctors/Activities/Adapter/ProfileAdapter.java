package com.example.askdoctors.Activities.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.askdoctors.Activities.Activity.ProfileActivity;
import com.example.askdoctors.Activities.Model.Doctors;
import com.example.askdoctors.Activities.Model.User;
import com.example.askdoctors.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    private Context mContext;
    private List<Doctors> mDoctors;
    private boolean isChat;

    public ProfileAdapter(Context mContext, List<Doctors> mDoctors, boolean isChat){
        this.mContext = mContext;
        this.mDoctors = mDoctors;
        this.isChat = isChat;
    }

    @NonNull
    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ViewHolder holder, int position) {
        final Doctors doctors = mDoctors.get(position);
        holder.username.setText(doctors.getFirstName()+doctors.getLastName());

        if (!TextUtils.isEmpty(doctors.getProfileImage())){
            Picasso.get().load(doctors.getProfileImage()).into(holder.profile_image);
        } else {
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        }


        if (isChat){
            if (doctors.getOnline().equals("online")){
                holder.on_img.setVisibility(View.VISIBLE);
                holder.off_img.setVisibility(View.GONE);
            }
            else {
                holder.on_img.setVisibility(View.GONE);
                holder.off_img.setVisibility(View.VISIBLE);
            }
        } else {
            holder.on_img.setVisibility(View.GONE);
            holder.off_img.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext , ProfileActivity.class);
                intent.putExtra("accType", "Doctors");
                intent.putExtra("userId" , doctors.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDoctors.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView profile_image;
        public TextView username;
        private ImageView on_img, off_img;


        ViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username_browse);
            profile_image = itemView.findViewById(R.id.profile_img);
            on_img = itemView.findViewById(R.id.on_img);
            off_img = itemView.findViewById(R.id.off_img);
        }
    }
}
