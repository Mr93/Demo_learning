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
	public static boolean cancelAsync = false;
    String[] urlsPizza = {"http://thisbeautifuldayblog.com/wp-content/uploads/2015/12/chicken-pizza-120x120.jpg",
            "http://anekatempatwisata.com/wp-content/uploads/2014/01/beeferoni-mushroom-pino-pizza-120x120.jpg",
            "http://www.poweredbybling.com/wp-content/uploads/2016/01/BBQ-Chicken-Pizza-120x120.jpg",
            "https://insideretail.sg/wp-content/uploads/2015/05/Firagos-pizza-120x120.png",
            "http://www.eatwellwithjanel.com/wp-content/uploads/2013/02/pizza-120x120.jpg",
            "http://www.genuinekentucky.com/wp-content/uploads/2013/09/Coppertop-Old-World-Style-Pizza-120x120.png",
		    "http://www.genuinekentucky.com/wp-content/uploads/2013/09/Coppertop-Old-World-Style-Pizza-120x120.png",
		    "http://www.genuinekentucky.com/wp-content/uploads/2013/09/Coppertop-Old-World-Style-Pizza-120x120.png",
		    "http://www.genuinekentucky.com/wp-content/uploads/2013/09/Coppertop-Old-World-Style-Pizza-120x120.png",
		    "http://www.genuinekentucky.com/wp-content/uploads/2013/09/Coppertop-Old-World-Style-Pizza-120x120.png",
		    "http://www.genuinekentucky.com/wp-content/uploads/2013/09/Coppertop-Old-World-Style-Pizza-120x120.png",
		    "http://www.genuinekentucky.com/wp-content/uploads/2013/09/Coppertop-Old-World-Style-Pizza-120x120.png",
		    "http://www.genuinekentucky.com/wp-content/uploads/2013/09/Coppertop-Old-World-Style-Pizza-120x120.png",
		    "http://www.genuinekentucky.com/wp-content/uploads/2013/09/Coppertop-Old-World-Style-Pizza-120x120.png",
		    "http://www.genuinekentucky.com/wp-content/uploads/2013/09/Coppertop-Old-World-Style-Pizza-120x120.png",
		    "http://www.genuinekentucky.com/wp-content/uploads/2013/09/Coppertop-Old-World-Style-Pizza-120x120.png"};
	String[] urlsDrink = {"http://thisbeautifuldayblog.com/wp-content/uploads/2015/12/chicken-pizza-120x120.jpg",
            "http://anekatempatwisata.com/wp-content/uploads/2014/01/beeferoni-mushroom-pino-pizza-120x120.jpg",
            "http://www.poweredbybling.com/wp-content/uploads/2016/01/BBQ-Chicken-Pizza-120x120.jpg",
            "https://insideretail.sg/wp-content/uploads/2015/05/Firagos-pizza-120x120.png",
            "http://www.eatwellwithjanel.com/wp-content/uploads/2013/02/pizza-120x120.jpg",
            "http://www.genuinekentucky.com/wp-content/uploads/2013/09/Coppertop-Old-World-Style-Pizza-120x120.png"};
	String[] urlsBbq = {"http://thisbeautifuldayblog.com/wp-content/uploads/2015/12/chicken-pizza-120x120.jpg",
            "http://anekatempatwisata.com/wp-content/uploads/2014/01/beeferoni-mushroom-pino-pizza-120x120.jpg",
            "http://www.poweredbybling.com/wp-content/uploads/2016/01/BBQ-Chicken-Pizza-120x120.jpg",
            "https://insideretail.sg/wp-content/uploads/2015/05/Firagos-pizza-120x120.png",
            "http://www.eatwellwithjanel.com/wp-content/uploads/2013/02/pizza-120x120.jpg",
            "http://www.genuinekentucky.com/wp-content/uploads/2013/09/Coppertop-Old-World-Style-Pizza-120x120.png"};
	String[] urlsDessert = {"http://thisbeautifuldayblog.com/wp-content/uploads/2015/12/chicken-pizza-120x120.jpg",
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
        String type = intent.getStringExtra(TYPE);
	    classifyData(type);
        gridView = (GridView)findViewById(R.id.gridView);
        customGridViewAdapter = new CustomGridViewAdapter(this, R.layout.layout_item_gridview, itemArrayList);
        gridView.setAdapter(customGridViewAdapter);
    }

	private void classifyData(String type){
		setTitle(type);
		if(type.equalsIgnoreCase(PIZZA)){
			createData(urlsPizza);
		}else if (type.equalsIgnoreCase(DRINK)){
			createData(urlsDrink);
		}else if (type.equalsIgnoreCase(BBQ)){
			createData(urlsBbq);
		}else if (type.equalsIgnoreCase(DESSERT)) {
			createData(urlsDessert);
		}
	}

	private void setTitle(String title){
		Log.d(TAG, "setTitle:  = " + title);
		getSupportActionBar().setTitle(title);
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
