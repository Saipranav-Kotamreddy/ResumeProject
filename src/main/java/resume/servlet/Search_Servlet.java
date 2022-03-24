package resume.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resume.bean.SearchBean;
import resume.database.SearchDao;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/**
 * Servlet implementation class Search_Servlet
 */
@WebServlet("/search")
public class Search_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		SearchBean searchData= new SearchBean();
		searchData.setSkillCriteria(request.getParameter("criteria"));
		String company = null;
		Vector<Integer> vec = new Vector<Integer>();
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("companyIdCookie")) {
				company = cookie.getValue();
			}
		}
		
		SearchDao searchDao = new SearchDao();
		Vector<String> databaseRecords;
		int searchedRecords=0;
		databaseRecords = searchDao.getSkillRows(company);

		while(searchedRecords<databaseRecords.size()) {
			//System.out.println(databaseRecords.size());
			//System.out.println("Record#" + searchedRecords + ": " + databaseRecords.elementAt(searchedRecords));
			if(searchData.findCriteria(databaseRecords.elementAt(searchedRecords))) {
				searchData.addValidResumes(databaseRecords.elementAt(searchedRecords));
			}
			searchedRecords++;
		}
		if(request.getParameter("criteria")!="") {
			vec = searchData.getValidResumeID();
			Vector<String> vec2 = searchData.getValidResumes();
			for(int i=0; i<vec.size();i++) {
				System.out.println("Record #" + vec.elementAt(i) + " is " + vec2.elementAt(i));
			}
		}
		else {
			int totalRecords= searchDao.getRowCount(company);
			for(int i=0; i<totalRecords;i++) {
				String[] results = databaseRecords.elementAt(i).split("&,");
				System.out.println(results[0]);
				vec.add(Integer.valueOf(results[0]));
				System.out.println(vec.elementAt(i));
			}
		}
		List<JSONObject> recordList = new ArrayList<JSONObject>();
		recordList = searchDao.getRecords(vec);
		//System.out.println(recordList);
		List<JSONObject> newRecordList = new ArrayList<JSONObject>();
		boolean check=false;
		int iter=0;
		for(int i=0; i<recordList.size();i++) {
			while(check==false && iter<recordList.size()) {
				System.out.println("Looking for: " +vec.elementAt(i).toString() + " Got: "+ recordList.get(iter).get("ID"));
				if(vec.elementAt(i).toString().equals(recordList.get(iter).get("ID"))) {
					newRecordList.add(recordList.get(iter));
					check=true;
				}
				iter++;
			}
			System.out.println(newRecordList.get(i).get("NAME"));
			iter=0;
			check=false;
		}
		System.out.println("End of Servlet " + newRecordList.size());
		request.setAttribute("records", newRecordList);
		/*Add page cookie for which page of records we're on and cookie storing vector of ids we got above
		Redirect to results page with next and previous buttons that either go to page+1 or page-1, previous gives same page
		when on page 1, next gives same page when on last page of records*/
		request.getRequestDispatcher("display.jsp").forward(request,response);
		
	}

}
