package com.training.testapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.training.testapp.R;
import com.training.testapp.models.Comments;
import com.training.testapp.models.Data;
import com.training.testapp.models.Images;
import com.training.testapp.models.StandardResolution;
import com.training.testapp.util.Util;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsHolder> {

    private List<Data> postList;
    private Context context;

    public PostsAdapter(Context context, List<Data> postList) {
        this.context = context;
        this.postList = postList;
    }


    @Override
    public PostsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout, null);
        return new PostsHolder(view);
    }

    @Override
    public void onBindViewHolder(PostsHolder holder, int position) {
        Data data = postList.get(position);
        if (data != null) {
            Images images = data.getImages();
            if (images != null) {
                StandardResolution standardResolutionImg = images.getStandardResolution();
                if (standardResolutionImg != null) {
                    Util.loadImageWithCustomResize(context.getApplicationContext(), standardResolutionImg.getUrl(),
                            holder.postImage, standardResolutionImg.getWidth(), standardResolutionImg.getHeight());
                }
            }
            Comments comments = data.getComments();
            if (comments != null && comments.getCount() > 0) {
                int count = comments.getCount();
                if (count == 1) {
                    holder.postComments.setText(String.format(context.getString(R.string.single_comment), count));
                } else {
                    holder.postComments.setText(String.format(context.getString(R.string.multi_comments), count));
                }
            } else {
                holder.postComments.setText(context.getString(R.string.no_comments));
            }
        }
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class PostsHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.postImage)
        ImageView postImage;

        @Bind(R.id.postComments)
        TextView postComments;

        PostsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
