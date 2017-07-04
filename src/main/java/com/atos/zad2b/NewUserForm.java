package com.atos.zad2b;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class NewUserForm {
	Sha3 sha3 = new Sha3();
	final DatabaseControler controler = new DatabaseControler();
	Stage secondStage = new Stage();

	public void startNewForm()
	{
		//Panel drugiego okna
		GridPane secondForm = new GridPane();
		secondForm.setPadding(new Insets(0,10,10,10));
		secondForm.setVgap(5);
		secondForm.setHgap(5);
		secondForm.setAlignment(Pos.CENTER);

		Scene secondScene = new Scene(secondForm, 250, 300);

		secondStage.setTitle("New User");
		secondStage.setScene(secondScene);

		//Label login
		Label loginLabel = new Label("Login");
		loginLabel.setMaxWidth(Double.MAX_VALUE);
		loginLabel.setAlignment(Pos.CENTER);
		GridPane.setConstraints(loginLabel, 0, 0);

		//Pole tekstowe logowania
		final TextField loginTextField = new TextField();
		loginTextField.setPromptText("Enter your new login");
		GridPane.setConstraints(loginTextField, 0, 1);

		//Label hasło
		Label passwordLabel = new Label("Password");
		passwordLabel.setMaxWidth(Double.MAX_VALUE);
		passwordLabel.setAlignment(Pos.CENTER);
		GridPane.setConstraints(passwordLabel, 0, 2);		

		//Pole tekstowe hasła
		final TextField passwordTextField = new TextField();
		passwordTextField.setPromptText("Enter your new password");
		GridPane.setConstraints(passwordTextField, 0,3);

		//Label description
		Label descriptionLabel = new Label("Description");
		descriptionLabel.setMaxWidth(Double.MAX_VALUE);
		descriptionLabel.setAlignment(Pos.CENTER);
		GridPane.setConstraints(descriptionLabel, 0, 4);		

		//Pole tekstowe hasła
		final TextField descriptionTextField = new TextField();
		descriptionTextField.setPromptText("Please provide user description");
		GridPane.setConstraints(descriptionTextField, 0,5);

		//Add New User Button
		Button newUserButton = new Button("Add New User");
		newUserButton.setMaxWidth(Double.MAX_VALUE);
		newUserButton.setAlignment(Pos.CENTER);
		newUserButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				Alert alert = new Alert(Alert.AlertType.NONE, null);
				alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
				
				
				if(verifyDuplicates(loginTextField.getText()) == true)
				{
					User user = new User(loginTextField.getText(), sha3.encrypt(passwordTextField.getText()), descriptionTextField.getText());
					controler.insertUser(user);
					alert.setContentText("Created new user");

					alert.showAndWait();
					secondStage.close();
				}
				else
				{
					alert.setContentText("User with such login already exist");
					alert.showAndWait();
				}
				

			}
		});
		GridPane.setConstraints(newUserButton, 0,9);

		//Cancel Button
		Button cancelButton = new Button("Cancel");
		cancelButton.setMaxWidth(Double.MAX_VALUE);
		cancelButton.setAlignment(Pos.CENTER);
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				secondStage.close();
			}
		});
		GridPane.setConstraints(cancelButton, 0,10);

		secondForm.getChildren().addAll(
				loginLabel,
				loginTextField,
				passwordLabel,
				passwordTextField,
				descriptionLabel,
				descriptionTextField,
				newUserButton,
				cancelButton
				);
		secondForm.requestFocus();
		secondStage.showAndWait();
	}
	
	private boolean verifyDuplicates(String userName)
	{
		DatabaseControler controler = new DatabaseControler();
		try{
			List<User> users = controler.getAll();
			for(int i=0; i<users.size(); i++)
			{
				if(userName.equals(users.get(i).getUserName()))
				{
					return false;
				}
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return true;
	}

}
