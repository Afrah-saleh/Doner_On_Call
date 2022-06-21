package com.example.donoroncall;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class myAdapter extends ArrayAdapter<com.example.donoroncall.announcementlModel> {
    private Context context;
    private List<com.example.donoroncall.announcementlModel> list;


    public myAdapter(Context context, List<com.example.donoroncall.announcementlModel> list) {
        super(context, 0, list);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        com.example.donoroncall.announcementlModel current = getItem(position);
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        convertView = layoutInflater.inflate(R.layout.item, parent, false);

        ((TextView) convertView.findViewById(R.id.textView)).setText(current.getName2());
        ((TextView) convertView.findViewById(R.id.textView2)).setText(current.getName());
        ((TextView) convertView.findViewById(R.id.textView3)).setText(current.getName4());
        Log.d("TAG", "getView: "+current.getName3());
        if(current.getName3().matches("Active"))
        {
            ((ImageView) convertView.findViewById(R.id.lineshape)).setImageResource(R.drawable.lineshape_green);
        }
        else
        {
            ((ImageView) convertView.findViewById(R.id.lineshape)).setImageResource(R.drawable.lineshape_gray);
        }

        return convertView;}

    public static class ViewHolder {
        TextView text;
        ImageView image;
    }
}