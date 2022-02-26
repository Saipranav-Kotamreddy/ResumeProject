package login.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import login.bean.RegisterBean;

public class RegisterDao {
	public int insertTable(RegisterBean registerData) {
		int id=0;
		int companyId=-1;
		ConnectionDB database = new ConnectionDB();
		Connection conn = database.getConnection();
		String companyCheck= "select * from saipranav_project.company_table where companyName = ?";
		String SQL = "insert into saipranav_project.registration_table (email_address, first_name, last_name, username, password, companyID) VALUES(?,?,?,?,?,?)";
		try {
			PreparedStatement psCompany = conn.prepareStatement(companyCheck);
			psCompany.setString(1, registerData.getCompanyName());
			ResultSet results = psCompany.executeQuery();
			boolean status=results.next();
			if(status) {
				companyId=results.getInt("companyID");
			}
			else {
				String companyInsert = "insert into saipranav_project.company_table (companyName) VALUES(?)";
				PreparedStatement newCompany = conn.prepareStatement(companyInsert);
				newCompany.setString(1, registerData.getCompanyName());
				int companyVal= newCompany.executeUpdate();
				if(companyVal>0) {
					String SQL3 = "SELECT * from saipranav_project.company_table where companyName = ?";
					PreparedStatement ps2 = conn.prepareStatement(SQL3);
					ps2.setString(1, registerData.getCompanyName());
					ResultSet res = ps2.executeQuery();
					if(res.next()){
						companyId = res.getInt(1);
					}
				}
			}
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, registerData.getEmail());
			ps.setString(2, registerData.getFirst_name());
			ps.setString(3, registerData.getLast_name());
			ps.setString(4, registerData.getUsername());
			ps.setString(5, registerData.getPassword());
			ps.setInt(6, companyId);
			int resultsVal = ps.executeUpdate();
			//System.out.println(results);
			if(resultsVal>0) {
				String SQL2 = "SELECT * from saipranav_project.registration_table where username = ?";
				PreparedStatement ps2 = conn.prepareStatement(SQL2);
				ps2.setString(1, registerData.getUsername());
				ResultSet rs = ps2.executeQuery();
				if(rs.next()){
					id = rs.getInt(1);
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
		return id;
	}
}
