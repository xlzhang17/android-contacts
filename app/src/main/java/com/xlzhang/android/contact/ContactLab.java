package com.xlzhang.android.contact;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

public class ContactLab {
    private static ContactLab sContactLab;
    private Context mAppContext;
    private ArrayList<Contact> mContacts;

    private int[] imageLab = {
            R.mipmap.ic_launcher_round,
            R.mipmap.image6,
            R.mipmap.images,
            R.mipmap.image10,
            R.mipmap.images2,
            R.mipmap.images4,
            R.mipmap.images12,
            R.mipmap.images14,
            R.mipmap.images16,
            R.mipmap.images18,
            R.mipmap.images20
    };

    private ContactLab(Context appContext) {
        mAppContext = appContext;
        mContacts = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < imageLab.length; j++){
                Contact c = new Contact();
                c.setImage(imageLab[j]);
                c.setName("name #" + i * imageLab.length + j);
                c.setLocation("Road #A" + i * imageLab.length + j);
                c.setOccupation("Teacher #" + i * imageLab.length + j);
                c.setDiscription("I'm NO." + i * imageLab.length + j);
                mContacts.add(c);
            }

        }
    }

    public int[] getImages(){
        int[] images = new int[mContacts.size()];
        for (int i = 0; i < mContacts.size(); i++){
            images[i] = mContacts.get(i).getImage();
        }
        return images;
    }
    public static ContactLab get(Context c) {
        if (sContactLab == null)
            sContactLab = new ContactLab(c.getApplicationContext());
        return sContactLab;
    }

    public ArrayList<Contact> getContacts() {
        return mContacts;
    }

    public Contact getContact(UUID id) {
        for (Contact c : mContacts) {
            if (c.getId().equals(id))
                return c;
        }
        return null;
    }
}
