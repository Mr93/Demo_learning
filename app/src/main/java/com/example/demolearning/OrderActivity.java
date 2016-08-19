package com.example.demolearning;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
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

import java.io.File;
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
	public static boolean cancelAsync = false;
    String[] urlsPizza = {"http://thisbeautifuldayblog.com/wp-content/uploads/2015/12/chicken-pizza-120x120.jpg",
            "http://anekatempatwisata.com/wp-content/uploads/2014/01/beeferoni-mushroom-pino-pizza-120x120.jpg",
            "http://www.poweredbybling.com/wp-content/uploads/2016/01/BBQ-Chicken-Pizza-120x120.jpg",
            "https://insideretail.sg/wp-content/uploads/2015/05/Firagos-pizza-120x120.png",
            "http://www.eatwellwithjanel.com/wp-content/uploads/2013/02/pizza-120x120.jpg",
            "http://www.genuinekentucky.com/wp-content/uploads/2013/09/Coppertop-Old-World-Style-Pizza-120x120.png",};
	String[] urlsDrink = {"http://www.shelbycraftbeerandbeverages.com/wp-content/uploads/2015/10/EnergyDrink_20PK1-120x120.jpg",
            "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSMBGXrnNr7BgWMqWYGEMXsCSeMhGAR4tfRRwP631uIGEfMtxux",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcoPSJZn_1OCkO2-rbXQBPUg7GEI2l8Xo7as5_v_0FDz6E6zze",
            "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQqjJOLOFhwhorQilgkwkcSiKfqNiDdc7-F2TBXC7OyHrfZQYx0",
            "http://sc01.alicdn.com/kf/HTB1kNxRIXXXXXbOXpXXq6xXFXXXF/round-barrel-cooler-50L-Commercial-Electric-Beverage.jpg_120x120.jpg",
            "https://www.usawdistributing.com/image/cache/data/products/554212-120x120.jpg"};
	String[] urlsBbq = {"http://infinitelegroom.com/wp-content/uploads/2015/06/bbq-00-120x120.jpg",
            "http://pendletoncc.com/wp-content/uploads/2015/06/BBQ-Ribs-Image-120x120.jpg",
            "http://iwamsn2012.ac.vn/wp-content/uploads/bbq1-120x120.jpg",
            "http://www.columbusunderground.com/wp-content/uploads/2016/06/obb-01-120x120.jpg",
            "http://mayvatlongga.vn/wp-content/uploads/2015/10/ga-nuong-bbq-120x120.png",
            "http://cmd-ks.biz/wp-content/uploads/2016/01/backyardbbqokc_0791260001452282605-120x120.jpg"};
	String[] urlsDessert = {"http://www.genuinekentucky.com/wp-content/uploads/2012/01/Olive-Garden-Owensboro-desserts-120x120.png",
			"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTSzF0XTL1_jfTMb_oR4puDTxWTfNArNMOcJRYG_SuQFvvIXnGp",
			"https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQNolzJRiDSJkcxJfrYjG5lotOVHJnYxSpit-6W_i8iq8r7UyeL",
			"https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcTv7mMOFeLIR1n1WrxrL12A5PAEcFmKQGdeDOj0lUehowu5___q",
			"http://assets.inhabitat.com/wp-content/blogs.dir/1/files/2015/11/End-of-Summer-Plum-and-Blackberry-Crisp-by-Gena-Hamshaw-120x120.jpg",
			"https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQvaQbXiDzEZYLBid8BTruJHJgTiNXqYdLJ1rCh4oJVFCdX1dCT"};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent = getIntent();
        String type = intent.getStringExtra(TYPE);
	    classifyData(type);
        gridView = (GridView)findViewById(R.id.gridView);
        customGridViewAdapter = new CustomGridViewAdapter(this, R.layout.layout_item_gridview, itemArrayList);
        gridView.setAdapter(customGridViewAdapter);
    }

	private void classifyData(String type){
		setTitle(type);
		if(type.equalsIgnoreCase(PIZZA)){
			createData(urlsPizza, PIZZA);
		}else if (type.equalsIgnoreCase(DRINK)){
			createData(urlsDrink, DRINK);
		}else if (type.equalsIgnoreCase(BBQ)){
			createData(urlsBbq, BBQ);
		}else if (type.equalsIgnoreCase(DESSERT)) {
			createData(urlsDessert, DESSERT);
		}
	}

	private void setTitle(String title){
		Log.d(TAG, "setTitle:  = " + title);
		getSupportActionBar().setTitle(title);
	}

	private void createData(String[] urls, String type){
		for(int i = 0; i<urls.length; i++){
			Drawable drawable;
			drawable = new DrawableProxy(this, urls[i], new CustomCallBack() {
				@Override
				public void loaded(Drawable drawable, int position) {
					customGridViewAdapter.loaded(drawable, position);
				}
			}, i, type);
			itemArrayList.add((drawable));
		}
	}

	/**
	 * Dispatch onPause() to fragments.
	 */
	@Override
	protected void onPause() {
		super.onPause();
		cancelAsync = true;
	}

	/**
	 * Dispatch onResume() to fragments.  Note that for better inter-operation
	 * with older versions of the platform, at the point of this call the
	 * fragments attached to the activity are <em>not</em> resumed.  This means
	 * that in some cases the previous state may still be saved, not allowing
	 * fragment transactions that modify the state.  To correctly interact
	 * with fragments in their proper state, you should instead override
	 * {@link #onResumeFragments()}.
	 */
	@Override
	protected void onResume() {
		super.onResume();
		cancelAsync = false;
	}
}
