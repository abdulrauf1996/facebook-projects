package com.qwerty.acer.facebook52;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by acer on 12/28/2016.
 */

public class ImageCustom extends ArrayAdapter<String> {

    Context con;
    ArrayList<String> image;

    public ImageCustom(Context context, ArrayList<String> resource) {
        super(context,R.layout.listing_img,R.id.imageView9);
        this.con=context;
        this.image=resource;
    }
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        LayoutInflater layoutInflater=(LayoutInflater)con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rows=layoutInflater.inflate(R.layout.listing_img,parent,false);

        ImageView imageView=(ImageView)rows.findViewById(R.id.imageView9);
        Picasso.with(getContext())
                .load(image.get(position))
                .resize(330,330)
                .centerCrop()
                .into(imageView);


        return rows;
    }
}
