package me.garciag2.fauxstagram;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import me.garciag2.fauxstagram.model.Post;

public class DetailsActivity extends AppCompatActivity{
    Post post;
    TextView tvUsername;
    TextView tvDescription;
    TextView tvTimestamp;
    ImageView ivImage;
    ImageView ivProfileImage;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        post = (Post) getIntent().getParcelableExtra("post");


        tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvTimestamp = (TextView) findViewById(R.id.tvTimestamp);
        ivImage = (ImageView) findViewById(R.id.ivImage);
        ivProfileImage= (ImageView) findViewById(R.id.ivProfileImage);

        tvUsername.setText(post.getUser().getUsername().toString());
        tvDescription.setText(post.getDescription().toString());
        tvTimestamp.setText(PostAdapter.getRelativeTimeAgo(post.getCreatedAt()));

        Glide.with(context).load(post.getParseFile("Image").getUrl()).into(ivImage);
        Glide.with(context).load(post.getUser().getParseFile("profileImage").getUrl()).into(ivProfileImage);

    }
}
