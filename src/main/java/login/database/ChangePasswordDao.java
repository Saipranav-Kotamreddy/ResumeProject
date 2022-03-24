package login.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import login.bean.ChangePasswordBean;

public class ChangePasswordDao {
	public boolean update(ChangePasswordBean changeData){
		boolean success=false;
		ConnectionDB database = new ConnectionDB();
		Connection conn = database.getConnection();
		String SQL = "UPDATE registration_table SET Password = ? where Username = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, changeData.getPassword());
			ps.setString(2, changeData.getUsername());
			int resultsVal = ps.executeUpdate();
			if(resultsVal>0){
				/*String SQL2 = "select company from registration_table where Username = ?";
				PreparedStatement ps2 = conn.prepareStatement(SQL);
				ps2.setString(1, loginData.getUsername());
				ResultSet rs = ps2.executeQuery();
				rs.next();*/
				success=true;
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
		return success;
	}
}
