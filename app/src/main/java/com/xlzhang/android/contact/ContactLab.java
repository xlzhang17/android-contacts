package com.xlzhang.android.contact;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.UUID;

public class ContactLab {
    private static final String TAG = "ContactLab";
    private static ContactLab sContactLab;
    private Context mAppContext;
    private ArrayList<Contact> mContacts;

    private ContactLab(Context appContext) {
        mAppContext = appContext;
        try {
            mContacts = loadContacts();
        }catch (Exception e){
            mContacts = new ArrayList<>();
            Log.e(TAG,"Error loading Contacts:", e);
        }
    }

    private ArrayList<Contact> loadContacts() throws IOException, JSONException {
        ArrayList<Contact> contacts = new ArrayList<>();
        BufferedReader reader = null;
        try {
            InputStream input = mAppContext.getResources().openRawResource(R.raw.contacts);
            reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            for (int i = 0; i < array.length(); i++) {
                contacts.add(new Contact(array.getJSONObject(i)));
            }
        } catch (FileNotFoundException e) {
            Log.e(TAG, "Error File:", e);
        } finally {
            if (reader != null)
                reader.close();
        }
        return contacts;
    }

    public String[] getImages() {
        String[] images = new String[mContacts.size()];
        for (int i = 0; i < mContacts.size(); i++) {
            images[i] = mContacts.get(i).getImageFile();
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
