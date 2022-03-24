package resume.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import resume.bean.ResumeBean;
import resume.database.ResumeDao;

/**
 * Servlet implementation class BatchResumeServlet
 */
@WebServlet("/batch")
@MultipartConfig
public class BatchResumeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BatchResumeServlet() {
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
		String company=null;
		String username=null;
		//System.out.println("Input jsp cookie: " + request.getCookies());
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("loginCookie")) {
				username = cookie.getValue();
			}
			if(cookie.getName().equals("companyIdCookie")) {
				company = cookie.getValue();
			}
		}
		ArrayList<ResumeBean> batchData = new ArrayList<ResumeBean>();
		Part csv = request.getPart("batchUpload");
		InputStream input = csv.getInputStream();
		String result = new String(input.readAllBytes(), StandardCharsets.UTF_8);
		String[] lines = result.split(System.lineSeparator());
		for(int i=0; i<lines.length; i++) {
			//Delimiter taken from the internet to split string without interfering with fields with commas
			String[] parameters = lines[i].split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
			String fixedSkills = parameters[5].substring(1,parameters[5].length()-1);
			batchData.add(new ResumeBean(parameters[0],parameters[1],parameters[2],parameters[3],parameters[4],fixedSkills));
		}
		ResumeDao resumeDao = new ResumeDao();
		int numberInserted = resumeDao.batchInsert(batchData, company, username);
		
		response.sendRedirect("input.jsp");
	}

}
