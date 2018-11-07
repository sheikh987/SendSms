package com.example.sheikh.sendsms;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentOtp extends android.support.v4.app.Fragment {

    View v;
    private RecyclerView myRecyclerView;
    private List<Message> lstMessage;
    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;

    LinearLayoutManager linearLayoutManager;

    public FragmentOtp() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.otp_fragment,container,false);
        myRecyclerView = (RecyclerView) v.findViewById(R.id.otp_recyclerview);
        RecyclerViewAdapterOtp recyclerViewAdapter =new RecyclerViewAdapterOtp(getContext(),lstMessage);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        myRecyclerView.setLayoutManager(linearLayoutManager);
        myRecyclerView.setAdapter(recyclerViewAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstMessage = new ArrayList<>();

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("prefId", Context.MODE_PRIVATE);
        String text = sharedPreferences.getString("jsonArray", null);
        if (text!=null) {
            try {
                JSONArray jsonArray = new JSONArray(text);
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject object = jsonArray.getJSONObject(i);
                    String name = object.getString("name");
                    String otp = object.getString("otp");
                    String dateTime = object.getString("date");

                    lstMessage.add(new Message(name,otp,dateTime));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }




    }


}
