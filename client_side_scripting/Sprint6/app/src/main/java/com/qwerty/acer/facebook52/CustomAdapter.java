package com.qwerty.acer.facebook52;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by acer on 12/13/2016.
 */
public class CustomAdapter extends ArrayAdapter<String>
{

    Context c;
    ArrayList<String> status;
    //String name;

    public CustomAdapter(Context context, ArrayList<String> resource) {
        super(context, R.layout.listing_status,R.id.textView37,resource);
        this.c=context;
        this.status=resource;
        //this.name=name;
    }
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        LayoutInflater li=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=li.inflate(R.layout.listing_status,parent,false);

        TextView lv_status=(TextView)row.findViewById(R.id.textView37);
        //TextView name1=(TextView)row.findViewById(R.id.textView36);
        lv_status.setText(status.get(position));
        //name1.setText(name);
        return row;
    }
}
