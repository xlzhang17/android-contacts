package com.xlzhang.android.contact;

import java.util.UUID;

public class Contact {
    private UUID mId;
    private int mImage;

    public int getImage() {
        return mImage;
    }

    public void setImage(int image) {
        mImage = image;
    }

    private String mName;
    private String mLocation;
    private String mOccupation;
    private String mDiscription;

    public Contact(){
        mId = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return mName;
    }

    public UUID getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public String getOccupation() {
        return mOccupation;
    }

    public void setOccupation(String occupation) {
        mOccupation = occupation;
    }

    public String getDiscription() {
        return mDiscription;
    }

    public void setDiscription(String discription) {
        mDiscription = discription;
    }

}
