package com.xlzhang.android.contact;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

public class Contact {
    private static final String JSON_FIRSTNAME = "first_name";
    private static final String JSON_LASTNAME = "last_name";
    private static final String JSON_IMAGE = "avatar_filename";
    private static final String JSON_TITLE = "title";
    private static final String JSON_INTRODUCTION = "introduction";

    private UUID mId;

    private String mFirstName;
    private String mLastName;
    private String mImageFile;
    private String mTitle;
    private String mIntroduction;

    public Contact(){
        mId = UUID.randomUUID();
    }

    public Contact(JSONObject jsonObject) throws JSONException {
        mId = UUID.randomUUID();
        if(jsonObject.has(JSON_FIRSTNAME))
            mFirstName = jsonObject.getString(JSON_FIRSTNAME);
        mLastName = jsonObject.getString(JSON_LASTNAME);
        mImageFile = jsonObject.getString(JSON_IMAGE);
        mTitle = jsonObject.getString(JSON_TITLE);
        mIntroduction = jsonObject.getString(JSON_INTRODUCTION);
    }

    public UUID getId() {
        return mId;
    }

    public String getImageFile() {
        return mImageFile;
    }

    public void setImage(String imageFile) {
        mImageFile = imageFile;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getIntroduction() {
        return mIntroduction;
    }

    public void setIntroduction(String introduction) {
        mIntroduction = introduction;
    }
}
