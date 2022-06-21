package com.example.donoroncall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DonationRecordRecycleViewAdapter extends RecyclerView.Adapter<DonationRecordRecycleViewAdapter.recholder> {

    List<DonationRecordeModel> recordlist;
    Context context;

    public DonationRecordRecycleViewAdapter(List<DonationRecordeModel> recordlist, Context context) {
        this.recordlist = recordlist;
        this.context = context;
    }

    @NonNull
    @Override
    public recholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.oneitemdonationrecord, parent, false);
        recholder holder = new recholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull recholder holder, int position) {


        holder.recordid.setText(String.valueOf(recordlist.get(position).getRecordid()));
        holder.quanity.setText(String.valueOf(recordlist.get(position).getQuantity()));
        holder.recorddate.setText(recordlist.get(position).getRecorddate());
        holder.hospitalname.setText(recordlist.get(position).getHospitalname());
        holder.donername.setText(recordlist.get(position).getDonername());




    }

    @Override
    public int getItemCount() {
        return recordlist.size();
    }


    public class recholder extends RecyclerView.ViewHolder {



        TextView recordid, recorddate, quanity, donername, hospitalname;

        public recholder(@NonNull View itemView) {
            super(itemView);
            recordid = itemView.findViewById(R.id.recordid);
            recorddate = itemView.findViewById(R.id.recorddate);
            quanity = itemView.findViewById(R.id.quantity);
            donername = itemView.findViewById(R.id.donername);
            hospitalname = itemView.findViewById(R.id.hospitalname);


        }

    }
}

