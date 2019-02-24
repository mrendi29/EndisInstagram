package com.example.caushie.endisinstagram.fragments;

import android.util.Log;

import com.example.caushie.endisinstagram.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


public class ProfileFragment extends PostsFragment {
    private static final String TAG = "PostsFragment";

    protected void queryPost() {
        // Specify which class to query
        ParseQuery<Post> postQuery = new ParseQuery<Post>(Post.class);

        /**
         * Since the user who created the post is not
         * included by default we need to specify it in the query
         */

        postQuery.include(Post.KEY_USER);
        postQuery.addDescendingOrder(Post.KEY_TIME);
        //Sets limit to only 20 last posts.
        postQuery.setLimit(20);
        //Since we now are in ProfileFragment we want to add a filter to only show posts by the user.
        postQuery.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());


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
                mPosts.addAll(posts);
                adapter.notifyDataSetChanged();
                for (int i = 0; i < posts.size(); ++i) {
                    Post post = posts.get(i);
                    Log.d(TAG, "Description: " + post.getDescription() + "User: " + post.getUser().getUsername());
                }

            }
        });
    }
}
