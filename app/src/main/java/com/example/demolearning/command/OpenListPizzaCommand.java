package com.example.demolearning.command;

import android.content.Context;

/**
 * Created by DucDat on 8/12/2016.
 */
public class OpenListPizzaCommand implements ICommand {

	Context context;
	public static OpenListPizzaCommand instance;

	public static OpenListPizzaCommand getInstance(Context context){
		if (instance == null){
			instance = new OpenListPizzaCommand(context);
		}
		return instance;
	}

	public OpenListPizzaCommand(Context context){
		this.context = context;
	}

	@Override
	public void execute() {
		Receiver.getInstance().openListPizza(context);
	}
}

