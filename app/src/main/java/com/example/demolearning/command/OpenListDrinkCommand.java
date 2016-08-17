package com.example.demolearning.command;

import android.content.Context;

/**
 * Created by DucDat on 8/12/2016.
 */
public class OpenListDrinkCommand implements ICommand {

	Receiver receiver;
	Context context;

	public OpenListDrinkCommand(Receiver receiver, Context context){
		this.context = context;
		this.receiver = receiver;
	}

	@Override
	public void execute() {
		receiver.openListDrink(context);
	}
}

