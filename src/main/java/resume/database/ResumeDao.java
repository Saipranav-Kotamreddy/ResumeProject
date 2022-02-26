package resume.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;

import resume.bean.ResumeBean;

public class ResumeDao {
	public boolean insertTable(ResumeBean resumeData, String company, String username) {
		//int id=0;
		boolean insertCheck=false;
		ConnectionDB database = new ConnectionDB();
		Connection conn = database.getConnection();
		String SQL = "insert into saipranav_project.resume_table (name, phone_number, email, resume_file, applicantPic, skillList, companyID, uploader, date_uploaded) VALUES(?,?,?,?,?,?,?,?,?)";
		try {
			InputStream stream = resumeData.getResumePdf().getInputStream();
			InputStream stream2 = resumeData.getPicture().getInputStream();
			
			
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, resumeData.getName());
			ps.setString(2, resumeData.getPhoneNumber());
			ps.setString(3, resumeData.getEmailAddress());
			ps.setBinaryStream(4, stream);
			ps.setBinaryStream(5, stream2);
			/*for(int i=0; i<resumeData.getSkillNumber(); i++){
				ps.setString(5+i, resumeData.getSkill(i));
			}*/
			ps.setString(6, resumeData.getSkillListString());
			ps.setString(7, company);
			ps.setString(8, username);
			ps.setString(9, LocalDate.now().toString());
			int results = ps.executeUpdate();
			//System.out.println(results);
			if(results>0) {
				/*String SQL2 = "select id from saipranav_project.resume_table where name = ?";
				PreparedStatement ps2 = conn.prepareStatement(SQL2);
				ps2.setString(1, resumeData.getName());
				ResultSet rs = ps2.executeQuery();
				if(rs.next()){
					id = rs.getInt(1);
				}*/
				insertCheck=true;
			}
		} catch (SQLException | IOException e) {
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
		return insertCheck;
	}
}
