package login.servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.bean.LoginBean;
//import login.database.ConnectionDB;
import login.database.LoginDao;

/**
 * Servlet implementation class Login_Servlet
 */
@WebServlet("/login")
public class Login_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login_Servlet() {
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
		
		LoginBean loginData = new LoginBean();
		loginData.setUsername(request.getParameter("username"));
		
		String inputPassword = request.getParameter("password");
		StringBuilder hashedPassword= new StringBuilder();
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(inputPassword.getBytes());
			byte[] bytes = digest.digest();
			
			for(byte a : bytes) {
				hashedPassword.append(String.format("%02x", a));
			}
			
			String hash=hashedPassword.toString();
			loginData.setPassword(hash);
		}
		catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		
		LoginDao loginDao = new LoginDao();
		String[] companyData = loginDao.validate(loginData);
		String companyId = companyData[0];
		String companyName = companyData[1];
		if(companyName!=null) {
			
			
			
			Cookie loginCookie = new Cookie("loginCookie", loginData.getUsername());
			Cookie companyCookie = new Cookie("companyCookie", companyName);
			Cookie companyIdCookie = new Cookie("companyIdCookie", companyId);
			
			loginCookie.setMaxAge(300);
			companyCookie.setMaxAge(300);
			companyIdCookie.setMaxAge(300);

			
			response.addCookie(loginCookie);
			response.addCookie(companyCookie);
			response.addCookie(companyIdCookie);

			
			/*System.out.println("Printing cookie: "+ request.getCookies());
			Cookie[] cookies = request.getCookies();
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("loginCookie")) {
					System.out.println("Logincookie: " + cookie.getValue());
				}
			}*/
			
			
			request.setAttribute("username", loginData.getUsername());
			request.setAttribute("company", companyName);
			request.getRequestDispatcher("loginSuccess.jsp").forward(request,response);
		}
		else {
			request.setAttribute("errorFlag", "true");
			request.getRequestDispatcher("login.jsp").forward(request,response);
		}
	}

}
