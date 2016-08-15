package com.example.demolearning;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by mamram on 8/14/2016.
 */
public class OrderActivity extends AppCompatActivity {
    private static final String TAG = OrderActivity.class.getSimpleName() ;
    private GridView gridView;
    private ArrayList<Item> itemArrayList = new ArrayList<>();
    CustomGridViewAdapter customGridViewAdapter;
    private static final String PIZZA = "PIZZA";
    private static final String HOT_POT = "HOT_POT";
    private static final String BBQ = "BBQ";
    private static final String BEVERAGE = "BEVERAGE";
    private Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(this.getResources().getColor(R.color.textColorPrimary));
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        createData(type);
        gridView = (GridView)findViewById(R.id.gridView);
        customGridViewAdapter = new CustomGridViewAdapter(this, R.layout.layout_item_gridview, itemArrayList);
        gridView.setAdapter(customGridViewAdapter);
    }

    private void createData(String type){
        if(type.equalsIgnoreCase(PIZZA)){
            createPizzaData();
            toolbar.setTitle(PIZZA);
        }else if (type.equalsIgnoreCase(HOT_POT)){
            createHotPotData();
            toolbar.setTitle(HOT_POT);
        }else if (type.equalsIgnoreCase(BBQ)){
            createBBQData();
            toolbar.setTitle(BBQ);
        }else {
            createBeverageData();
            toolbar.setTitle(BEVERAGE);
        }
    }

    private void createPizzaData(){
        String[] urls = {"http://itspizzatime.ca/wp-content/uploads/2015/09/4791207-9790062099-Pizza1.jpg",
                "http://nitzapizza.com/wp-content/uploads/2011/07/maxresdefault-1.jpg",
                "https://i.kinja-img.com/gawker-media/image/upload/wafswectpmbr0zmug9ly.jpg",
                "http://www.athenaspizzaamherst.com/wp-content/uploads/2013/05/front-1-1008x500.jpg",
                "http://www.barrospizza.com/wp-content/uploads/2013/10/menu-pizza.png",
                "http://www.pizzahut-tt.com/wp-content/uploads/2013/06/pizza-hut-trinidad-and-tobago-pepperoni-lovers-pizza.png"};
        for(int i = 0; i<urls.length; i++){
            Bitmap imageView = BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher);
            Item item = new Item(imageView);
            itemArrayList.add(item);
        }
        for(int i = 0; i < urls.length; i++){
            Log.d(TAG, "createPizzaData: " + itemArrayList.get(i));
            new DownloadImageTask(this, itemArrayList.get(i)).execute(urls[i]);
        }
    }

    private void createHotPotData(){
        Bitmap imageView = BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher);

        itemArrayList.add(new Item(imageView));
        itemArrayList.add(new Item(imageView));
        itemArrayList.add(new Item(imageView));
    }

    private void createBBQData(){

    }

    private void createBeverageData(){

    }

    class DownloadImageTask extends AsyncTask<String, Void, Bitmap>{

        Context context;
        Item item;

        public DownloadImageTask(Context context, Item item) {
            this.context = context;
            this.item = item;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {

            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
                in.close();
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            int index = itemArrayList.indexOf(item);
            itemArrayList.remove(item);
            itemArrayList.add(index, new Item(bitmap));
            Log.d(TAG, "onPostExecute: " + item.toString());
            customGridViewAdapter.notifyDataSetChanged();
        }
    }
}
