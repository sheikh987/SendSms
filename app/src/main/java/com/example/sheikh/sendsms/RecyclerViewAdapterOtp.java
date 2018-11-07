package com.example.sheikh.sendsms;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapterOtp extends RecyclerView.Adapter<RecyclerViewAdapterOtp.MyViewHolder> {

    Context mContext;
    List<Message> mData;

    public RecyclerViewAdapterOtp(Context mContext, List<Message> mData) {
        this.mContext = mContext;
        this.mData = mData;

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_otp,parent,false);
        MyViewHolder vHolder = new MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(mData.get(position).getmName());
        holder.otp.setText(mData.get(position).getmOtp());
        holder.dateTime.setText(mData.get(position).getmDateTime());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView otp;
        private TextView dateTime;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.view_name);
            otp = (TextView) itemView.findViewById(R.id.view_otp);
            dateTime = (TextView) itemView.findViewById(R.id.view_date_time);
        }
    }
}