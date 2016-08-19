package com.example.demolearning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.example.demolearning.command.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

	MenuItem pizzaItem, bbqItem;
	Button btn1, btn2;
	Invoker invoker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		init();
	}

	private void init() {
		btn1.setText(getResources().getString(R.string.listPizza));
		btn2.setText(getResources().getString(R.string.listDrink));
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		invoker = Invoker.getInstance(this);
		invoker.addKey(R.id.btn1, OpenListPizzaCommand.getInstance(this));
		invoker.addKey(R.id.btn2, OpenListDrinkCommand.getInstance(this));
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main_activity, menu);
		pizzaItem = menu.findItem(R.id.pizza);
		bbqItem = menu.findItem(R.id.bbq);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.pizza) {
			invoker.addKey(R.id.btn1, OpenListPizzaCommand.getInstance(this));
			invoker.addKey(R.id.btn2, OpenListDrinkCommand.getInstance(this));
			btn1.setText(R.string.listPizza);
			btn2.setText(R.string.listDrink);
		}else if (id == R.id.bbq){
			invoker.addKey(R.id.btn1, OpenListBbqCommand.getInstance(this));
			invoker.addKey(R.id.btn2, OpenListDessertCommand.getInstance(this));
			btn1.setText(R.string.listBbq);
			btn2.setText(R.string.listDessert);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View view) {
		Integer id = view.getId();
		invoker.invoker(id);
	}
}
