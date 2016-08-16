package com.example.demolearning;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
public class CustomGridViewAdapter extends ArrayAdapter<Drawable> {
    private static final String TAG = CustomGridViewAdapter.class.getSimpleName();
    Context context;
    int layoutResourceId;
    ArrayList<Drawable> data = new ArrayList<>();

    public CustomGridViewAdapter(Context context, int resource, ArrayList<Drawable> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResourceId = resource;
        this.data = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RecordHolder recordHolder = null;

        if(row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId,parent,false);
            recordHolder = new RecordHolder();
            recordHolder.imageView = (ImageView) row.findViewById(R.id.item_img);
            recordHolder.button = (Button)row.findViewById(R.id.item_button);
            row.setTag(recordHolder);
            Log.d(TAG, "getView: " + data.get(position));
        }else {
            recordHolder = (RecordHolder)row.getTag();
        }
        recordHolder.imageView.setImageDrawable(data.get(position));
        final RecordHolder finalRecordHolder = recordHolder;
        recordHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalRecordHolder.imageView.setImageDrawable(data.get(position));
                Log.d(TAG, "onClick: " + data.get(position));
            }
        });
        return row;
    }

    class RecordHolder {
        ImageView imageView;
        Button button;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
