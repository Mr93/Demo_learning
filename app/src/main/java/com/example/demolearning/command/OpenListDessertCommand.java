package com.example.demolearning.command;

import android.content.Context;

/**
 * Created by DucDat on 8/12/2016.
 */
public class OpenListDessertCommand implements ICommand {

	Receiver receiver;
	Context context;

	public OpenListDessertCommand(Receiver receiver, Context context) {
		this.context = context;
		this.receiver = receiver;
	}

	@Override
	public void execute() {
		receiver.openListDessert(context);
	}
}

