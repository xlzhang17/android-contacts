package com.xlzhang.android.contact;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.UUID;

public class ContactFragment extends Fragment {
    public static final String EXTRA_CONTACT_ID = "com.xlzhang.android.contact.contact_id";
    private Contact mContact;
    private TextView mFirstNameField;
    private TextView mLastnameField;
    private TextView mTitleField;
    private TextView mIntroductionField;

    public static ContactFragment newInstance(UUID id){
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CONTACT_ID, id);
        ContactFragment fragment = new ContactFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID contactId = (UUID) getArguments().getSerializable(EXTRA_CONTACT_ID);
        mContact = ContactLab.get(getActivity()).getContact(contactId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_contact, container, false);
        mFirstNameField = v.findViewById(R.id.contact_first_name);
        mFirstNameField.setText(mContact.getFirstName());
        mLastnameField = v.findViewById(R.id.contact_last_name);
        mLastnameField.setText(mContact.getLastName());
        mTitleField = v.findViewById(R.id.contact_title);
        mTitleField.setText(mContact.getTitle());
        mIntroductionField = v.findViewById(R.id.contact_introduction);
        mIntroductionField.setText(mContact.getIntroduction());
        return v;
    }
}
