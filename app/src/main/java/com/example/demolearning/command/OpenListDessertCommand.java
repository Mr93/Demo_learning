package com.example.demolearning.command;

/**
 * Created by DucDat on 8/12/2016.
 */
public class OpenListDessertCommand implements ICommand {

	Receiver receiver;

	public OpenListDessertCommand(Receiver receiver) {
		this.receiver = receiver;
	}

	@Override
	public void execute() {
		receiver.openListDessert();
	}
}

