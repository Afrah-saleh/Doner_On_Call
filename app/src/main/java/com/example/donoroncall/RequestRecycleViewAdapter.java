package com.example.donoroncall;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RequestRecycleViewAdapter extends RecyclerView.Adapter<RequestRecycleViewAdapter.viewholder> {

    List<RequestModel> requestlist;
    Context context;

    public RequestRecycleViewAdapter(List<RequestModel> requestlist, Context context) {
        this.requestlist = requestlist;
        this.context = context;
    }


    @NonNull
    @Override
    public RequestRecycleViewAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.onerequestitem, parent, false);
        viewholder holder = new viewholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RequestRecycleViewAdapter.viewholder holder, @SuppressLint("RecyclerView") int position) {

        holder.requestid.setText(String.valueOf(requestlist.get(position).getRequestid()));
        holder.requestdate.setText(requestlist.get(position).getRequestdate());
        holder.announcementid.setText(String.valueOf(requestlist.get(position).getAnnouncementid()));
        holder.requeststatus.setText(requestlist.get(position).getRequeststatus());
        holder.requesttype.setText(requestlist.get(position).getRequesttype());
        holder.donername.setText(requestlist.get(position).getDonername());
        holder.donationdate.setText(requestlist.get(position).getDonationdate());



       holder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,HospitalEditRequest.class);
                intent.putExtra("requestid",requestlist.get(position).getRequestid());
                intent.putExtra("requestdate",requestlist.get(position).getRequestdate());
                intent.putExtra("announcementid",requestlist.get(position).getAnnouncementid());
                intent.putExtra("requeststatus",requestlist.get(position).getRequeststatus());
                intent.putExtra("requesttype",requestlist.get(position).getRequesttype());
                intent.putExtra("donername",requestlist.get(position).getDonername());
                intent.putExtra("donationdate",requestlist.get(position).getDonationdate());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return requestlist.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {


        TextView requestid, requestdate, announcementid, donername, requesttype, requeststatus,donationdate;
        RelativeLayout parentlayout;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            requestid = itemView.findViewById(R.id.requestid);
            requestdate = itemView.findViewById(R.id.requestdate);
            announcementid = itemView.findViewById(R.id.announcementid);
            donername = itemView.findViewById(R.id.donername);
            requesttype = itemView.findViewById(R.id.requesttype);
            requeststatus = itemView.findViewById(R.id.requeststatus);
            donationdate=itemView.findViewById(R.id.donationdate);
            parentlayout = itemView.findViewById(R.id.oneitemrequestlayout);

        }
    }



}
