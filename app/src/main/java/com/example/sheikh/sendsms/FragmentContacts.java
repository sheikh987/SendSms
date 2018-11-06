package com.example.sheikh.sendsms;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FragmentContacts extends android.support.v4.app.Fragment{

    View v;
    private RecyclerView myRecyclerView;
    private List<Contact> lstContact;

    public FragmentContacts() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.contacts_fragment,container,false);
        myRecyclerView = (RecyclerView) v.findViewById(R.id.contact_recyclerview);
        RecyclerViewAdapter recyclerViewAdapter =new RecyclerViewAdapter(getContext(),lstContact);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(recyclerViewAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstContact = new ArrayList<>();

//        lstContact.add(new Contact("sheikh","arham"));
//        lstContact.add(new Contact("sheikh","raahim"));
//        lstContact.add(new Contact("sheikh","arham"));
//        lstContact.add(new Contact("sheikh","raahim"));


        InputStream inputStream = getResources().openRawResource(R.raw.contacts);
        String json = new Scanner(inputStream).useDelimiter("\\A").next();
        JSONArray array = null;
        try {
            array = new JSONArray(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < array.length(); i++) {
            try {
                JSONObject object = array.getJSONObject(i);
                String firstName = object.getString("firstName");
                String lastname = object.getString("lastName");
                Log.i("firstName: ",firstName);
                lstContact.add(new Contact(firstName,lastname));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
