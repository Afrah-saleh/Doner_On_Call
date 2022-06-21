package com.example.donoroncall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class hospitalAdapter extends ArrayAdapter<hospitalModel> {
    private Context context;
    private List<hospitalModel> list;


    public hospitalAdapter(Context context, List<hospitalModel> list) {
        super(context, 0, list);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        hospitalModel current = getItem(position);
        ViewHolder viewHolder = new ViewHolder();
        if(convertView==null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.hospitals_list_item, parent, false);
            ((TextView) convertView.findViewById(R.id.textView)).setText(current.getName());
            ((TextView) convertView.findViewById(R.id.textView2)).setText(current.getName2());
            ((TextView) convertView.findViewById(R.id.textView3)).setText(current.getName3());
            convertView.setTag(viewHolder);
        }else{
            convertView.getTag();
        }
        ((TextView) convertView.findViewById(R.id.textView)).setText(current.getName());
        ((TextView) convertView.findViewById(R.id.textView2)).setText(current.getName2());
        ((TextView) convertView.findViewById(R.id.textView3)).setText(current.getName3());
        return convertView;

    }



    public static class ViewHolder {
        TextView text;

    }
}