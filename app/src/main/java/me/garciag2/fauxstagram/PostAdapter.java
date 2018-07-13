package me.garciag2.fauxstagram;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.Date;
import java.util.List;

import me.garciag2.fauxstagram.model.Post;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private List<Post> mPosts;
    Context context;

    public PostAdapter(List<Post> posts) {
        mPosts = posts;
    }


    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.item_post, parent, false);
        ViewHolder viewHolder = new ViewHolder(postView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int i) {
        Post post = mPosts.get(i);
        holder.tvUsername.setText(post.getUser().getUsername().toString());
        holder.tvDescription.setText(post.getDescription().toString());
        holder.tvTimeStamp.setText(getRelativeTimeAgo(post.getCreatedAt()));

         Glide.with(context).load(post.getParseFile("Image").getUrl()).into(holder.ivImage);
         Glide.with(context).load(post.getUser().getParseFile("profileImage").getUrl()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.ivProfileImage);


    }

    public void clear() {
        mPosts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        mPosts.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
         return mPosts == null ? 0 : mPosts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivProfileImage;
        public ImageView ivImage;
        public TextView tvUsername;
        public TextView tvDescription;
        public TextView tvTimeStamp;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            ivProfileImage = (ImageView) itemView.findViewById(R.id.ivProfileImage);
            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
            tvUsername = (TextView) itemView.findViewById(R.id.tvUsername);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            tvTimeStamp = (TextView) itemView.findViewById(R.id.tvTimestamp);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Post post = mPosts.get(position);
                    Intent i = new Intent(context, DetailsActivity.class);
                    i.putExtra("post", post);
                    context.startActivity(i);
                }
            });


        }
    }

    public static String getRelativeTimeAgo(Date date) {
        String relativeDate;
        long dateMillis = date.getTime();
        relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        return relativeDate;
    }
}