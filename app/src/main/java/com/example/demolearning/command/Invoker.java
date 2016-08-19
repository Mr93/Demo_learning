package com.example.demolearning.command;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by DucDat on 8/12/2016.
 */
public class Invoker {
	private HashMap<Integer, ICommand> map;
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
	}

	public void addKey(Integer key, ICommand command){
		map.put(key, command);
	}

	public void invoker(Integer key) {
		ICommand iCommand = map.get(key);
		if (iCommand != null) {
			iCommand.execute();
		}
	}

	public void removeKey(Integer key) {
		map.remove(key);
	}

	public Context getContext(){
		return context;
	}
}
