package com.example.sheikh.sendsms;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Contact> mData;
    Dialog myDialog;

    public RecyclerViewAdapter(Context context, List<Contact> data) {
        this.mContext = context;
        this.mData = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_contact,parent,false);
        final MyViewHolder vHolder = new MyViewHolder(v);

        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.dialog_contact);




        vHolder.item_contact.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Test Click"+String.valueOf(vHolder.getAdapterPosition()),Toast.LENGTH_SHORT).show();

                TextView dialogName = (TextView) myDialog.findViewById(R.id.dialog_name);
                final TextView dialogPhoneNumber = (TextView) myDialog.findViewById(R.id.dialog_phonenumber);
                String name1 = mData.get(vHolder.getAdapterPosition()).getFirstName();
                String name2 = mData.get(vHolder.getAdapterPosition()).getLastName();
                String name = name1 + "  " + name2;

                dialogName.setText(name);
                dialogPhoneNumber.setText(mData.get(vHolder.getAdapterPosition()).getPhoneNumber());

                Button dialogButton = (Button) myDialog.findViewById(R.id.dialog_button);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "i am"+ mData.get(vHolder.getAdapterPosition()).getPhoneNumber(),Toast.LENGTH_SHORT).show();
                    }
                });
                myDialog.show();
            }
        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.fistName.setText(mData.get(position).getFirstName());
        holder.lastName.setText(mData.get(position).getLastName());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout item_contact;
        private TextView fistName;
        private TextView lastName;
        public MyViewHolder(View itemView) {
            super(itemView);

            item_contact = (LinearLayout) itemView.findViewById(R.id.contact_item_id);
            fistName = (TextView) itemView.findViewById(R.id.view_first_name);
            lastName = (TextView) itemView.findViewById(R.id.view_last_name);
        }
    }
}
