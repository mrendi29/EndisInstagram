package com.example.caushie.endisinstagram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";
    private EditText etDescription;
    private Button btnCaptureImage;
    private Button btnSubmitImage;
    private ImageView ivPostImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Pull references for member variables.
        etDescription = findViewById(R.id.etDescription);
        btnCaptureImage = findViewById(R.id.btnCapture);
        btnSubmitImage = findViewById(R.id.btnSubmit);
        ivPostImage = findViewById(R.id.ivPostImage);

        queryPost();


    }

    /**
     * We want to query all the posts.
     * Specify which class to query
     */
    private void queryPost() {
        // Specify which class to query
        ParseQuery<Post> postQuery = new ParseQuery<Post>(Post.class);

        /**
         * Since the user who created the post is not
         * included by default we need to specify it in the query
         */

        postQuery.include(Post.KEY_USER);

        /**
         * Execute the find asynchronously.
         * (We do not want expensive operations on the ui)
         */

        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {

                //In case something goes wrong.
                if (e != null) {
                    Log.e(TAG, "Could not get posts. Error with query.");
                    e.printStackTrace();
                    return;
                }
                for (int i = 0; i < posts.size(); ++i) {
                    Post post = posts.get(i);
                    Log.d(TAG, "Description: " + post.getDescription() + "User: " + post.getUser().getUsername());
                }
            }
        });


    }
}
