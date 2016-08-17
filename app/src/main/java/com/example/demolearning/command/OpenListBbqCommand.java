package com.example.demolearning.command;

/**
 * Created by DucDat on 8/12/2016.
 */
public class OpenListBbqCommand  implements ICommand {

	Receiver receiver;

	public OpenListBbqCommand(Receiver receiver){
		this.receiver = receiver;
	}

	@Override
	public void execute() {
		receiver.openListBbq();
	}
}
