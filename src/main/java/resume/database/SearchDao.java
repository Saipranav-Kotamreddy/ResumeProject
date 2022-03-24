package resume.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;


public class SearchDao {
	public static List<JSONObject> getjsonFormatResult(ResultSet rs)
	{
		List<JSONObject> resList = new ArrayList<JSONObject>();
		try {
			ResultSetMetaData rsMeta = rs.getMetaData();
			int columnCnt = rsMeta.getColumnCount();
			List<String> columnNames = new ArrayList<String>();
		
			for (int i=1; i<=columnCnt; i++)
			{
				columnNames.add(rsMeta.getColumnName(i).toUpperCase());
			}
			while (rs.next())
			{
				JSONObject obj = new JSONObject();
				for (int i=1; i<=columnCnt; i++)
				{
					if(i!=8&&i!=10) {
						String key = columnNames.get(i - 1);
						String value = rs.getString(i);
						obj.put(key, value);
					}
				}
				resList.add(obj);
				//System.out.println("aaaaaaaaaA"+resList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//System.out.println(resList);
		return resList;
	}

	public int getRowCount(String companyId) {
		int rows=0;
		boolean status;
		ConnectionDB database = new ConnectionDB();
		Connection conn = database.getConnection();
		String SQL = "SELECT COUNT(*) FROM resume_table where companyId= \"" + companyId + "\"" ;
		try {
			Statement query = conn.createStatement();
			ResultSet results = query.executeQuery(SQL);
			status=results.next();
			if(status) {
				rows = results.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rows;
	}
	public Vector<String> getSkillRows(String companyId) {
		Vector<String> strings = new Vector<String>();
		boolean status;
		ConnectionDB database = new ConnectionDB();
		Connection conn = database.getConnection();
		//Select group of 10 rows from resume_table
		String SQL = "SELECT id,skillList FROM resume_table WHERE companyID = \"" + companyId + "\"";
		try {
			Statement query = conn.createStatement();
			ResultSet results = query.executeQuery(SQL);
			status=results.next();
			while(status) {
				//Place the skillList column of these 10 rows into an array of strings
				strings.add(results.getString("id") + "&," + results.getString("skillList"));
				
				status=results.next();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strings;
	}
	
	public List<JSONObject> getRecords(Vector<Integer> recordIDs){
		List<JSONObject> recordList = new ArrayList<JSONObject>();
		//boolean status;
		int startPoint=0;
		int endPoint=recordIDs.size();
		//int pos=0;
		int[] idList = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		ConnectionDB database = new ConnectionDB();
		Connection conn = database.getConnection();
		//Select group of 10 rows from resume_table
		for(int i=startPoint; i<endPoint; i++) {
				idList[i]=recordIDs.elementAt(i);
		}
		String SQL = "SELECT * FROM resume_table WHERE id IN (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setInt(1, idList[0]);
			ps.setInt(2, idList[1]);
			ps.setInt(3, idList[2]);
			ps.setInt(4, idList[3]);
			ps.setInt(5, idList[4]);
			ps.setInt(6, idList[5]);
			ps.setInt(7, idList[6]);
			ps.setInt(8, idList[7]);
			ps.setInt(9, idList[8]);
			ps.setInt(10, idList[9]);
			ps.setInt(11, idList[10]);
			ps.setInt(12, idList[11]);
			ps.setInt(13, idList[12]);
			ps.setInt(14, idList[13]);
			ps.setInt(15, idList[14]);
			ps.setInt(16, idList[15]);
			ps.setInt(17, idList[16]);
			ps.setInt(18, idList[17]);
			ps.setInt(19, idList[18]);
			ps.setInt(20, idList[19]);
			ps.setInt(21, idList[20]);
			ps.setInt(22, idList[21]);
			ps.setInt(23, idList[22]);
			ps.setInt(24, idList[23]);
			ps.setInt(25, idList[24]);
			ps.setInt(26, idList[25]);
			ps.setInt(27, idList[26]);
			ps.setInt(28, idList[27]);
			ps.setInt(29, idList[28]);
			ps.setInt(30, idList[29]);
			ps.setInt(31, idList[30]);
			ps.setInt(32, idList[31]);
			ps.setInt(33, idList[32]);
			ps.setInt(34, idList[33]);
			ps.setInt(35, idList[34]);
			ps.setInt(36, idList[35]);
			ps.setInt(37, idList[36]);
			ps.setInt(38, idList[37]);
			ps.setInt(39, idList[38]);
			ps.setInt(40, idList[39]);
			ps.setInt(41, idList[40]);
			ps.setInt(42, idList[41]);
			ps.setInt(43, idList[42]);
			ps.setInt(44, idList[43]);
			ps.setInt(45, idList[44]);
			ps.setInt(46, idList[45]);
			ps.setInt(47, idList[46]);
			ps.setInt(48, idList[47]);
			ps.setInt(49, idList[48]);
			ps.setInt(50, idList[49]);

			ResultSet results = ps.executeQuery();
			/*status=results.next();
			if(status) {
				recordList = getjsonFormatResult(results);
			}*/
			recordList = getjsonFormatResult(results);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(recordList);
		return recordList;
	}
}
