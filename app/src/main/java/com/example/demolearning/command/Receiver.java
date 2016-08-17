package com.example.demolearning.command;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.example.demolearning.OrderActivity;

/**
 * Created by DucDat on 8/12/2016.
 */
public class Receiver {

	private static final String TAG = Receiver.class.getSimpleName();

	public void openListPizza(Context context) {
		Log.d(TAG, "openListPizza: ");
		startActivity(context, OrderActivity.PIZZA);
	}

	public void openListDrink(Context context) {
		Log.d(TAG, "openListDrink: ");
		startActivity(context, OrderActivity.DRINK);
	}

	public void openListBbq(Context context) {
		Log.d(TAG, "openListBbq: ");
		startActivity(context, OrderActivity.BBQ);
	}

	public void openListDessert(Context context) {
		Log.d(TAG, "openListDessert: ");
		startActivity(context, OrderActivity.DESSERT);
	}

	public void startActivity(Context context, String type){
		Intent intent = new Intent(context, OrderActivity.class);
		intent.putExtra(OrderActivity.TYPE, type);
		context.startActivity(intent);
	}
}
