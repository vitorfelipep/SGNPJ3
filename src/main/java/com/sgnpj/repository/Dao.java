package com.sgnpj.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dao {
	protected Connection con; //Conecta com Oracle (Banco)

	protected PreparedStatement stmt;

	//Acessa a Tabela (crud)

	protected ResultSet rs;

	//Consulta
	public void open() throws Exception{

			Class.forName("com.mysql.jdbc.Driver");

			//Classe para se Connectar ao Oracle

			//OracleDriver

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/npj_bd01", "root", "");

	}

	//1521 (porta) numero da porta

	//nome do banco

	public void close() throws Exception{
		con.close();

	}

	public static void main(String[] args) {

		Dao d = new Dao();

		try{

			d.open();

			d.close();

			System.out.println("Conexao Segura OK");

		}catch(Exception ex){

			ex.printStackTrace();

		}

	}
}
