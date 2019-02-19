package com.example.caushie.endisinstagram;

import android.text.format.DateUtils;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A model that represents our data.
 */
@ParseClassName("Post")
public class Post extends ParseObject {

    public final static String KEY_DESCRIPTION = "description";
    public final static String KEY_USER = "user";
    public final static String KEY_IMAGE = "imageResources";
    public final static String KEY_TIME = "createdAt";

    // Use getString and others to access fields
    public String getDescription() {
        return getString(KEY_DESCRIPTION);
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

    @Override
    public Date getCreatedAt() {
        return super.getCreatedAt();
    }

    public String dateToString() {
        Date date = getCreatedAt();
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);

        String relativeDate = "";
        try {
            long dateMillis = dateFormat.parse(date.toString()).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS, DateUtils.FORMAT_ABBREV_RELATIVE).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;

    }

    //    public String getTimestamp(){
//
//    }


}
