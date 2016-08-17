package com.example.demolearning.command;

/**
 * Created by DucDat on 8/12/2016.
 */
public class OpenListDrinkCommand implements ICommand {

	Receiver receiver;

	public OpenListDrinkCommand(Receiver receiver){
		this.receiver = receiver;
	}

	@Override
	public void execute() {
		receiver.openListDrink();
	}
}

