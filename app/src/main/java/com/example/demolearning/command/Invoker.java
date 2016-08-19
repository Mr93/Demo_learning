package com.example.demolearning.command;

import android.content.Context;

import java.util.HashMap;

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
	Context context;
	private static Invoker instance;

	public static Invoker getInstance(Context context){
		if (instance == null){
			instance = new Invoker(context);
		}
		return instance;
	}

	private Invoker(Context context) {
		this.context = context;
		map = new HashMap<>();
		receiver = new Receiver();
		listPizzaCommand = new OpenListPizzaCommand(receiver, context);
		listDrinkCommand = new OpenListDrinkCommand(receiver, context);
		listBbqCommand = new OpenListBbqCommand(receiver, context);
		listDessertCommand = new OpenListDessertCommand(receiver, context);
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
