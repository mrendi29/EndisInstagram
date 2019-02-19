package com.example.caushie.endisinstagram.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.caushie.endisinstagram.Post;
import com.example.caushie.endisinstagram.PostsAdapter;
import com.example.caushie.endisinstagram.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class PostsFragment extends Fragment {
    private static final String TAG = "PostsFragment";
    private RecyclerView rvPosts;
    private List<Post> mPosts;
    private PostsAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        rvPosts = view.findViewById(R.id.rvPosts);

        //create data source
        mPosts = new ArrayList<>();
        //Create Adapter
        adapter = new PostsAdapter(getContext(), mPosts);
        //Set the adapter on the recycler view
        rvPosts.setAdapter(adapter);
        //set the layout manager on the recyclerview
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));

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
        postQuery.addDescendingOrder(Post.KEY_TIME);

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
