package login.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;

import login.bean.LoginBean;

public class LoginDao{
	public String[] validate(LoginBean loginData){
		boolean status=false;
		String companyResponse=null;
		String companyName = null;
		ConnectionDB database = new ConnectionDB();
		Connection conn = database.getConnection();
		//Security Vulnerable, due to SQL injection through string concatenation such as 1 'or' 1=1
		/*String SQL = "Select * from login_table where Username='" + loginData.getUsername() + "' AND Password='" + loginData.getPassword()+ "'";

		try {
			Statement s = conn.createStatement();
			ResultSet results=s.executeQuery(SQL);
			status=results.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		//More secure code
		String SQL = "select * from registration_table where Username = ? and Password = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, loginData.getUsername());
			ps.setString(2, loginData.getPassword());
			ResultSet results = ps.executeQuery();
			status = results.next();
			if(status) {
				/*String SQL2 = "select company from registration_table where Username = ?";
				PreparedStatement ps2 = conn.prepareStatement(SQL);
				ps2.setString(1, loginData.getUsername());
				ResultSet rs = ps2.executeQuery();
				rs.next();*/
				companyResponse=results.getString("companyID");
				String SQL2 = "select companyName from company_table where companyID = ?";
				PreparedStatement ps2 = conn.prepareStatement(SQL2);
				ps2.setString(1, companyResponse);
				ResultSet rs = ps2.executeQuery();
				boolean status2 = rs.next();
				if(status2) {
					companyName=rs.getString("companyName");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(conn!=null) {
				try {
					conn.close();
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		String[] companyData = new String[2];
		companyData[0] = companyResponse;
		companyData[1] = companyName;
		return companyData;
	}
}
