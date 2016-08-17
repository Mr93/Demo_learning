package com.example.demolearning.command;

/**
 * Created by DucDat on 8/12/2016.
 */
public class OpenListPizzaCommand implements ICommand {

	Receiver receiver;

	public OpenListPizzaCommand(Receiver receiver){
		this.receiver = receiver;
	}

	@Override
	public void execute() {
		receiver.openListPizza();
	}
}
