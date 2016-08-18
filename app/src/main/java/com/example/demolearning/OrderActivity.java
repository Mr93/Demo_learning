package com.example.demolearning;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
    private ArrayList<Drawable> itemArrayList = new ArrayList<>();
    CustomGridViewAdapter customGridViewAdapter;
    public static final String TYPE = "type";
    public static final String PIZZA = "PIZZA";
	public static final String DRINK = "DRINK";
	public static final String BBQ = "BBQ";
	public static final String DESSERT = "DESSERT";
    private Toolbar toolbar;
    String[] urlsPizza = {"http://thisbeautifuldayblog.com/wp-content/uploads/2015/12/chicken-pizza-120x120.jpg",
            "http://anekatempatwisata.com/wp-content/uploads/2014/01/beeferoni-mushroom-pino-pizza-120x120.jpg",
            "http://www.poweredbybling.com/wp-content/uploads/2016/01/BBQ-Chicken-Pizza-120x120.jpg",
            "https://insideretail.sg/wp-content/uploads/2015/05/Firagos-pizza-120x120.png",
            "http://www.eatwellwithjanel.com/wp-content/uploads/2013/02/pizza-120x120.jpg",
            "http://www.genuinekentucky.com/wp-content/uploads/2013/09/Coppertop-Old-World-Style-Pizza-120x120.png"};

//	String[] urlsPizza = {"http://itspizzatime.ca/wp-content/uploads/2015/09/4791207-9790062099-Pizza1.jpg",
//			"http://nitzapizza.com/wp-content/uploads/2011/07/maxresdefault-1.jpg",
//			"https://i.kinja-img.com/gawker-media/image/upload/wafswectpmbr0zmug9ly.jpg",
//			"http://www.athenaspizzaamherst.com/wp-content/uploads/2013/05/front-1-1008x500.jpg",
//			"http://www.barrospizza.com/wp-content/uploads/2013/10/menu-pizza.png",
//			"http://www.pizzahut-tt.com/wp-content/uploads/2013/06/pizza-hut-trinidad-and-tobago-pepperoni-lovers-pizza.png"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(this.getResources().getColor(R.color.textColorPrimary));
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        Intent intent = getIntent();
        String type = intent.getStringExtra(TYPE);
//        createData(type);
	    classifyData(type);
        gridView = (GridView)findViewById(R.id.gridView);
        customGridViewAdapter = new CustomGridViewAdapter(this, R.layout.layout_item_gridview, itemArrayList);
        gridView.setAdapter(customGridViewAdapter);
    }

	private void createData(String type){
		setTitle(type);
		if(type.equalsIgnoreCase(PIZZA)){
			createPizzaData(urlsPizza);
		}else if (type.equalsIgnoreCase(DRINK)){
			createHotDrinkData();
		}else if (type.equalsIgnoreCase(BBQ)){
			createBBQData();
		}else {
			createDessertData();
		}
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

	private void setTitle(String title){
		Log.d(TAG, "setTitle:  = " + title);
		getSupportActionBar().setTitle(title);
	}

	private void createPizzaData(String[] urls){
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

    private void createHotDrinkData(){

    }

    private void createBBQData(){

    }

    private void createDessertData(){

    }


}
