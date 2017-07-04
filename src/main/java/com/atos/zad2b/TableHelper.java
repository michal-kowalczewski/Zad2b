package com.atos.zad2b;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class TableHelper {
	final DatabaseControler controler = new DatabaseControler();
	public TableView<User> refreshTable(TableView<User> table)
	{	
		List<User> usersList = new ArrayList<User>();
		usersList = controler.getAll(); 
		ObservableList<User> observableList = FXCollections.observableArrayList(usersList);	
		table.setItems(observableList);	
		return table;
	}

}
