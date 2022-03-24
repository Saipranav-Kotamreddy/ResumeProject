package login.servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.bean.ChangePasswordBean;
import login.database.ChangePasswordDao;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordServlet() {
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
		ChangePasswordBean changeData = new ChangePasswordBean();
		changeData.setUsername(request.getParameter("username"));
		
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
			changeData.setPassword(hash);
		}
		catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		ChangePasswordDao passwordDao = new ChangePasswordDao();
		boolean success= passwordDao.update(changeData);
		if(success==true) {
			response.sendRedirect("changePasswordSuccess.jsp");
		}
		else {
			response.sendRedirect("login.jsp");
		}
	}
}
