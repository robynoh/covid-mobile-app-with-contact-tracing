package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomVideoListAdaptor extends ArrayAdapter<video_list> {

    ArrayList<video_list> products;
    Context context;
    int resource;

    public CustomVideoListAdaptor(Context context, int resource, ArrayList<video_list> products) {
        super(context, resource, products);
        this.products = products;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.video_list_layout, null, true);

        }
        video_list product = getItem(position);

        ImageView image = (ImageView) convertView.findViewById(R.id.imageView);
        Picasso.with(context).load("https://www.bysgcovid19library.ng/mgt/video_photos/"+product.getImage()).into(image);

        TextView textViewName = (TextView) convertView.findViewById(R.id.textViewName);
        textViewName.setText(product.getTitle());

        TextView textViewDesc = (TextView) convertView.findViewById(R.id.textViewDesc);
        textViewDesc.setText(product.getDate());

        return convertView;
    }
}
