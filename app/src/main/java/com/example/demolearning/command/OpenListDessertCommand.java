package com.example.demolearning.command;

import android.content.Context;

/**
 * Created by DucDat on 8/12/2016.
 */
public class OpenListDessertCommand implements ICommand {

	Context context;
	public static OpenListDessertCommand instance;

	public static OpenListDessertCommand getInstance(Context context){
		if (instance == null){
			instance = new OpenListDessertCommand(context);
		}
		return instance;
	}

	public OpenListDessertCommand(Context context){
		this.context = context;
	}

	@Override
	public void execute() {
		Receiver.getInstance().openListDessert(context);
	}
}

