package com.example.caushie.endisinstagram;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * A model that represents our data.
 */
@ParseClassName("Post")
public class Post extends ParseObject {

    public final static String KEY_DESCRIPTION = "description";
    public final static String KEY_USER = "user";
    public final static String KEY_IMAGE = "imageResources";

    // Use getString and others to access fields
    public String getDescription() {
        return KEY_DESCRIPTION;
    }

    // Use put to modify field values
    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }

    // Get the user for this item
    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    // Associate each item with a user
    public void setUser(ParseUser parseUser) {
        put(KEY_USER, parseUser);
    }


    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile parseFile) {
        put(KEY_IMAGE, parseFile);
    }


}
