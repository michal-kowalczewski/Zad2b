package com.atos.zad2b;

import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;



public class LoginForm extends Application{
	Sha3 sha3;
	TableView<User> table;
	TableHelper tableHelper;
	public String description;
	@SuppressWarnings("unchecked")
	@Override
	public void start(final Stage primaryStage) throws Exception{
		sha3 = new Sha3();

		//Panel Aplikacji
		GridPane form = new GridPane();
		form.setPadding(new Insets(0,10,10,10));
		form.setVgap(5);
		form.setHgap(5);
		form.setAlignment(Pos.CENTER);
		Scene scene = new Scene (form, 450, 450);

		//Label User Name
		Label userNameLabel = new Label("Login");
		userNameLabel.setMaxWidth(Double.MAX_VALUE);
		userNameLabel.setAlignment(Pos.CENTER);
		GridPane.setConstraints(userNameLabel, 0, 0);

		//Pole tekstowe logowania
		final TextField userNameTextField = new TextField();
		userNameTextField.setPromptText("Enter your login");
		GridPane.setConstraints(userNameTextField, 0, 1);

		//Label has³o
		Label passwordLabel = new Label("Password");
		passwordLabel.setMaxWidth(Double.MAX_VALUE);
		passwordLabel.setAlignment(Pos.CENTER);
		GridPane.setConstraints(passwordLabel, 0, 2);		

		//Pole tekstowe has³a
		final PasswordField passwordTextField = new PasswordField();
		passwordTextField.setPromptText("Enter your password");
		GridPane.setConstraints(passwordTextField, 0,3);

		//Tabela z danymi u¿ytkowników - kolumne z idUser zostawiam w kodzie, ale u¿ytkownik nie musi znaæ swojego ID tak wiêc zostawiam j¹ zakomentowan¹
		table = new TableView<User>();
		//TableColumn<User, Integer> userIdsColumn = new TableColumn<User,Integer>("ID");
		//userIdsColumn.setMinWidth(100);
		TableColumn<User, String> usersNameColumn = new TableColumn<User,String>("User Name");
		usersNameColumn.setMinWidth(200);	
		//userIdsColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("idUser"));
		usersNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
		table.getColumns().addAll(usersNameColumn);	
		tableHelper = new TableHelper();
		table = tableHelper.refreshTable(table);
		GridPane.setConstraints(table, 0, 5);
		table.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					userNameTextField.setText(table.getSelectionModel().getSelectedItem().getUserName());                
				}
			}
		});

		//Login Button
		Button loginButton = new Button("Log In");
		loginButton.setMaxWidth(Double.MAX_VALUE);
		loginButton.setAlignment(Pos.CENTER);
		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				Alert alert = new Alert(Alert.AlertType.NONE, null);
				alert.getDialogPane().getButtonTypes().add(ButtonType.OK);

				String userName, password;
				userName = userNameTextField.getText();
				password = sha3.encrypt(passwordTextField.getText());

				if(verifyUser(userName, password) == true)
				{
					alert.setContentText(description);
				}
				else
				{
					alert.setContentText("Wrong Credentials");
				}	
				alert.showAndWait();

			}
		});
		GridPane.setConstraints(loginButton, 0,8);

		//New User Button
		Button newUserButton = new Button("Create New User");
		newUserButton.setMaxWidth(Double.MAX_VALUE);
		newUserButton.setAlignment(Pos.CENTER);
		newUserButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				NewUserForm newForm = new NewUserForm();
				newForm.startNewForm();
				table.getItems().removeAll();
				table = tableHelper.refreshTable(table);				
			}
		});
		GridPane.setConstraints(newUserButton, 0,9);

		//Cancel Button
		Button cancelButton = new Button("Cancel");
		cancelButton.setMaxWidth(Double.MAX_VALUE);
		cancelButton.setAlignment(Pos.CENTER);
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				primaryStage.close();
			}
		});
		GridPane.setConstraints(cancelButton, 0,10);

		//Dodawanie wszystkiego do panelu
		form.getChildren().addAll(
				userNameLabel,
				userNameTextField,
				passwordLabel,
				passwordTextField, 
				loginButton,
				newUserButton,
				cancelButton,
				table
				);

		primaryStage.setTitle("Zad 2b");
		primaryStage.setScene(scene);
		primaryStage.show();
		form.requestFocus();
	}	

	private boolean verifyUser(String userName, String password)
	{
		DatabaseControler controler = new DatabaseControler();
		try{
			List<User> users = controler.getAll();
			for(int i=0; i<users.size(); i++)
			{
				if(userName.equals(users.get(i).getUserName()) && password.equals(users.get(i).getUserPassword()))
				{
					description = users.get(i).getUserDescription();
					return true;
				}
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return false;
	}


	public static void main(String[] args){
		launch(args);
	}
}
