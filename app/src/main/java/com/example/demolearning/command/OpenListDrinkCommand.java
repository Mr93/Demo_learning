package com.example.demolearning.command;

import android.content.Context;

/**
 * Created by DucDat on 8/12/2016.
 */
public class OpenListDrinkCommand implements ICommand {

	Context context;
	public static OpenListDrinkCommand instance;

	public static OpenListDrinkCommand getInstance(Context context){
		if (instance == null){
			instance = new OpenListDrinkCommand(context);
		}
		return instance;
	}

	public OpenListDrinkCommand(Context context){
		this.context = context;
	}

	@Override
	public void execute() {
		Receiver.getInstance().openListDrink(context);
	}
}

