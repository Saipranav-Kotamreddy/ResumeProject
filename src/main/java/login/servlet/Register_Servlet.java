package login.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.bean.RegisterBean;
import login.database.RegisterDao;

/**
 * Servlet implementation class Register_Servlet
 */
@WebServlet("/register")
public class Register_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RegisterBean registerData= new RegisterBean();
		RegisterDao registerDao = new RegisterDao();
		registerData.setEmail(request.getParameter("email"));
		registerData.setFirst_name(request.getParameter("first_name"));
		registerData.setLast_name(request.getParameter("last_name"));
		registerData.setUsername(request.getParameter("username"));
		registerData.setCompanyName(request.getParameter("email"));
		//registerData.setCompanyName(request.getParameter("company"));
		
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
			registerData.setPassword(hash);
		}
		catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		
		int registerId = registerDao.insertTable(registerData);
		if(registerId>0) {
			/*response.setContentType("text/html");
			PrintWriter output= response.getWriter(); 
			output.println("Successfully registered! Your ID is " + registerId);*/
			request.setAttribute("id", registerId);
			request.getRequestDispatcher("registerSuccess.jsp").forward(request,response);
			//response.sendRedirect("registerSuccess.jsp");
		}
		else {
			request.setAttribute("errorFlag", "true");
			request.getRequestDispatcher("register.jsp").forward(request,response);		}
	}

}
