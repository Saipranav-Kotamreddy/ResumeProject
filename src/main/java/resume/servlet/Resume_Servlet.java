package resume.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resume.bean.ResumeBean;
import resume.database.ResumeDao;

/**
 * Servlet implementation class Resume_Servlet
 */
@WebServlet("/resume")
@MultipartConfig
public class Resume_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Resume_Servlet() {
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
		
		System.out.println(request.getParameter("name"));
		System.out.println(request.getParameter("phone_number"));
		System.out.println(request.getParameter("skills"));
		
		ResumeBean resumeData = new ResumeBean();
		ResumeDao resumeDao = new ResumeDao();
		resumeData.setName(request.getParameter("name"));
		resumeData.setPhoneNumber(request.getParameter("phone_number"));
		resumeData.setEmailAddress(request.getParameter("email"));
		resumeData.setResumePdf(request.getPart("resume_link"));
		resumeData.setPicture(request.getPart("picture"));
		resumeData.setSkillList(request.getParameter("skills"));
		boolean check=true;
		check = resumeDao.insertTable(resumeData, company, username);
		//System.out.println(check);
				
		if(check==true) {
			//Add Success html page
			response.sendRedirect("input.jsp");
		}
		else {
			//Add Failure html Page
			request.setAttribute("errorFlag", "true");
			request.getRequestDispatcher("input.jsp").forward(request,response);	
		}
	}

}
