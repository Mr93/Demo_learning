package com.example.demolearning.command;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DucDat on 8/12/2016.
 */
public class Invoker {
	private HashMap<Integer, ICommand> map;
	Receiver receiver;
	OpenListPizzaCommand listPizzaCommand;
	OpenListDrinkCommand listDrinkCommand;
	OpenListBbqCommand listBbqCommand;
	OpenListDessertCommand listDessertCommand;

	public Invoker() {
		map = new HashMap<>();
		receiver = new Receiver();
		listPizzaCommand = new OpenListPizzaCommand(receiver);
		listDrinkCommand = new OpenListDrinkCommand(receiver);
		listBbqCommand = new OpenListBbqCommand(receiver);
		listDessertCommand = new OpenListDessertCommand(receiver);
	}

	public void invoker(Integer key) {
		ICommand iCommand = map.get(key);
		if (iCommand != null) {
			iCommand.execute();
		}
	}

	public void addPizzaForKey(Integer key) {
		map.put(key, listPizzaCommand);
	}

	public void addDrinkForKey(Integer key) {
		map.put(key, listDrinkCommand);
	}

	public void addBbqForKey(Integer key) {
		map.put(key, listBbqCommand);
	}

	public void addDessertForKey(Integer key) {
		map.put(key, listDessertCommand);
	}

	public void removeKey(Integer key) {
		map.remove(key);
	}
}
