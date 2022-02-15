package com.example.githubapp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.githubapp.R;
import com.example.githubapp.model.FollowModel;
import com.example.githubapp.model.GitHubUserDetailModel;
import com.example.githubapp.utils.Util;

import java.text.SimpleDateFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FollowListAdapter extends RecyclerView.Adapter<FollowListAdapter.ImageViewHolder> {
        private List<FollowModel> followList;
        private final OnItemClickListener listener;

        public FollowListAdapter(List<FollowModel> followList, OnItemClickListener listener) {
            this.followList= followList;
            this.listener = listener;
        }

        public void updateFollowList(List<FollowModel> newFollowList) {
            followList.clear();
            followList.addAll(newFollowList);
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_follow, parent, false);
            return new ImageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
            holder.bind(followList.get(position), listener);

        }

        @Override
        public int getItemCount() {
            return followList.size();
        }

        public class ImageViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.userAvatarList)
            ImageView userAvatarList;

            @BindView(R.id.followUserName)
            TextView followUserName;


            public ImageViewHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }

            void bind(FollowModel followItem, final OnItemClickListener listener) {
                followUserName.setText(followItem.getLogin());
                Util.loadImageCenterCrop(userAvatarList, followItem.getAvatarUrl(), Util.getProgressDrawable(userAvatarList.getContext()));
                itemView.setOnClickListener(v -> listener.onItemClick(followItem));
            }

        }


    }




