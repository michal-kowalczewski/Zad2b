package com.atos.zad2b;

public class User {

	private int idUser;
	private String userName, userPassword, userDescription;

	public User(){}

	public User(String userName, String userPassword, String userDescription)
	{
		this.userName = userName;
		this.userPassword = userPassword;
		this.userDescription = userDescription;
	}

	public User(int idUser, String userName, String userPassword, String userDescription)
	{
		this(userName, userPassword, userDescription);
		this.idUser = idUser;
	}

	public int getIdUser(){
		return idUser;
	}

	public void setIdUser(int idUser){
		this.idUser = idUser;
	}

	public String getUserName(){
		return userName;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserPassword(){
		return userPassword;
	}

	public void setUserPassword(String userPassword){
		this.userPassword = userPassword;
	}

	public String getUserDescription(){
		return userDescription;
	}

	public void setUserDescription(String userDesciption){
		this.userDescription = userDesciption;
	}

	@Override
	public String toString(){
		return  "Id User: " + idUser + 
				"\n User Name: " + userName +
				"\n User Password: " + userPassword +
				"\n User Description" + userDescription;
	}


}
