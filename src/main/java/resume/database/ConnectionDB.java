package resume.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

public class ConnectionDB {
	Vector<String> login = new Vector<String>();
	
	public Connection getConnection(){
		try {
			File textFile = new File("SQLData.txt");
			Scanner input = new Scanner(textFile);
			while(input.hasNextLine()) {
				login.add(input.nextLine());
			}
			input.close();
			Class.forName(login.elementAt(3));
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn= null;
		try {
			conn = DriverManager.getConnection(login.elementAt(0), login.elementAt(1), login.elementAt(2));
			System.out.println("Connection made");
		} catch (SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
		return conn;
	}
}
