package com.example.donoroncall;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {
    List<announcmentModel> annlist;
    Context context;

    public RecycleViewAdapter(List<announcmentModel> annlist, Context context) {
        this.annlist = annlist;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_announcement_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.annid.setText(String.valueOf(annlist.get(position).getID()));
        holder.anndate.setText(annlist.get(position).getAnnouncement_date());
        holder.blodtype.setText(annlist.get(position).getBloodtype());
        holder.hname.setText(annlist.get(position).getH_name());
        holder.quantity.setText(String.valueOf(annlist.get(position).getQuantity()));
        holder.hlocation.setText(annlist.get(position).getH_location());
       // holder.statusofannouncement.setText(annlist.get(position).getAnnouncement_status());
        if(annlist.get(position).getAnnouncement_status().matches("Closed"))
        {
            holder.green.setImageResource(R.drawable.lineshape_gray);
        }
        else
        {
            holder.green.setImageResource(R.drawable.lineshape_green);
        }



        holder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,AddRequest.class);
                intent.putExtra("annid",annlist.get(position).getID());
                intent.putExtra("adate",annlist.get(position).getAnnouncement_date());
                intent.putExtra("hname",annlist.get(position).getH_name());
                intent.putExtra("hlocation",annlist.get(position).getH_location());
                intent.putExtra("blodtype",annlist.get(position).getBloodtype());
                intent.putExtra("quantity",annlist.get(position).getQuantity());
                intent.putExtra("statusofannouncement",annlist.get(position).getAnnouncement_status());

                context.startActivity(intent);





            }
        });


    }

    @Override
    public int getItemCount() {
        return annlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView annid, anndate, hname, hlocation, blodtype, quantity,statusofannouncement;
        RelativeLayout parentlayout;
        ImageView green;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            annid = itemView.findViewById(R.id.anid);
            anndate = itemView.findViewById(R.id.anndate);
            hname = itemView.findViewById(R.id.hname);
            hlocation = itemView.findViewById(R.id.hlocation);
            blodtype = itemView.findViewById(R.id.blodtype);
            quantity = itemView.findViewById(R.id.quantity);
            green=itemView.findViewById(R.id.lineshape);
            //statusofannouncement=itemView.findViewById(R.id.statusofannouncemnt);
            parentlayout = itemView.findViewById(R.id.oneitemannouncementlayout);


        }
    }
}
