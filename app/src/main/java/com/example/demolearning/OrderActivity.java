package com.example.demolearning;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by mamram on 8/14/2016.
 */
public class OrderActivity extends AppCompatActivity {
    private static final String TAG = OrderActivity.class.getSimpleName() ;
    private GridView gridView;
    private ArrayList<Drawable> itemArrayList = new ArrayList<>();
    CustomGridViewAdapter customGridViewAdapter;
    private static final String PIZZA = "PIZZA";
    private static final String HOT_POT = "HOT_POT";
    private static final String BBQ = "BBQ";
    private static final String BEVERAGE = "BEVERAGE";
    private Toolbar toolbar;
    String[] urlsPizza = {"http://thisbeautifuldayblog.com/wp-content/uploads/2015/12/chicken-pizza-120x120.jpg",
            "http://anekatempatwisata.com/wp-content/uploads/2014/01/beeferoni-mushroom-pino-pizza-120x120.jpg",
            "http://www.poweredbybling.com/wp-content/uploads/2016/01/BBQ-Chicken-Pizza-120x120.jpg",
            "https://insideretail.sg/wp-content/uploads/2015/05/Firagos-pizza-120x120.png",
            "http://www.eatwellwithjanel.com/wp-content/uploads/2013/02/pizza-120x120.jpg",
            "http://www.genuinekentucky.com/wp-content/uploads/2013/09/Coppertop-Old-World-Style-Pizza-120x120.png"};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(this.getResources().getColor(R.color.textColorPrimary));
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        classifyData(type);
        gridView = (GridView)findViewById(R.id.gridView);
        customGridViewAdapter = new CustomGridViewAdapter(this, R.layout.layout_item_gridview, itemArrayList);
        gridView.setAdapter(customGridViewAdapter);
    }

    private void classifyData(String type){
        if(type.equalsIgnoreCase(PIZZA)){
            createData(urlsPizza);
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

    private void createData(String[] urls){
        for(int i = 0; i<urls.length; i++){
            Drawable drawable = new DrawableProxy(this, urls[i], new CustomCallBack() {
                @Override
                public void loaded(Drawable drawable, int position) {
                    customGridViewAdapter.loaded(drawable, position);
                }
            }, i);
            itemArrayList.add((drawable));
        }
    }

    private void createHotPotData(){

    }

    private void createBBQData(){

    }

    private void createBeverageData(){

    }


}
