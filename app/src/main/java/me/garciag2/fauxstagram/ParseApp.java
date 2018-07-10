package me.garciag2.fauxstagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import me.garciag2.fauxstagram.model.Post;
public class ParseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        final Parse.Configuration configuration = new Parse.Configuration.Builder(this)
                .applicationId("garciag")
                .clientKey("garciag2")
                .server("http://garciag-fbu-fauxstagram.herokuapp.com/parse")
                .build();

        Parse.initialize(configuration);
    }
}
