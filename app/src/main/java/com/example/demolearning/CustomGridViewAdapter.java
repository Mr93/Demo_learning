package com.example.demolearning;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by mamram on 8/14/2016.
 */
public class CustomGridViewAdapter extends ArrayAdapter<Item> {
    private static final String TAG = CustomGridViewAdapter.class.getSimpleName();
    Context context;
    int layoutResourceId;
    ArrayList<Item> data = new ArrayList<>();

    public CustomGridViewAdapter(Context context, int resource, ArrayList<Item> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResourceId = resource;
        this.data = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RecordHolder recordHolder = null;

        if(row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId,parent,false);
            recordHolder = new RecordHolder();
            recordHolder.imageView = (ImageView) row.findViewById(R.id.item_img);
            recordHolder.button = (Button)row.findViewById(R.id.item_button);
            row.setTag(recordHolder);
        }else {
            recordHolder = (RecordHolder)row.getTag();
        }
        Item item = data.get(position);
        recordHolder.imageView.setImageBitmap(item.getImageView());
        return row;
    }

    class RecordHolder {
        ImageView imageView;
        Button button;
    }
}
