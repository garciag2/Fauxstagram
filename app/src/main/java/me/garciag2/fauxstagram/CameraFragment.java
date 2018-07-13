package me.garciag2.fauxstagram;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;

import me.garciag2.fauxstagram.model.Post;

public class CameraFragment extends Fragment {
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.

    public Button createButton;
    public EditText descriptionInput;
    public ImageView ivPreview;
    File photoFile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_camera, parent, false);

    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v,savedInstanceState);

        ivPreview = v.findViewById(R.id.ivPreview);
        createButton = v.findViewById(R.id.createBtn);
        descriptionInput = v.findViewById(R.id.etDescription);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String description = descriptionInput.getText().toString();
                final ParseUser user = ParseUser.getCurrentUser();
                final ParseFile parseFile = new ParseFile(photoFile);
                createPost(description, parseFile, user);
            }
    });
    }

    private void createPost(String description, ParseFile imageFile, ParseUser user){
        final Post newPost = new Post();
        newPost.setDescription(description);
        newPost.setImage(imageFile);
        newPost.setUser(user);

        descriptionInput.clearFocus();

        newPost.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    Log.d("PostingActivity", "Success");
                    //TODO ADD LISTENER SO GOES TO TIMELINE
                }else{
                    e.printStackTrace();
                }
            }
        });
    }

}