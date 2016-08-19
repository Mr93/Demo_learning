package com.example.demolearning.command;

import android.content.Context;

/**
 * Created by DucDat on 8/12/2016.
 */
public class OpenListBbqCommand  implements ICommand {

	Context context;
	public static OpenListBbqCommand instance;

	public static OpenListBbqCommand getInstance(Context context){
		if (instance == null){
			instance = new OpenListBbqCommand(context);
		}
		return instance;
	}

	public OpenListBbqCommand(Context context){
		this.context = context;
	}

	@Override
	public void execute() {
		Receiver.getInstance().openListBbq(context);
	}
}
