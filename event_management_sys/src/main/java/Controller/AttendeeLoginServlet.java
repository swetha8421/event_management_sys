package Controller;

import java.io.IOException;

import Dao.AttendeeLoginDao;
import Model.Attendee;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class AttendeeLoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

        public AttendeeLoginServlet() {
        super();

    }
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		 if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
	            response.sendRedirect("attendeeLogin.jsp?error=invalidEmail");
	            return;
	        }


		System.out.println("email "+email);
		System.out.println("password "+password);
		AttendeeLoginDao obj = new AttendeeLoginDao();
		Attendee result = obj.loginByEmailRole(email);
		
		 response.setContentType("text/html;charset=UTF-8");


		if(result == null || !result.getPassword().equals(password))
		{
			System.out.println("failed");
			response.sendRedirect("attendeeLogin.jsp?error=invalid");
			return;

		}
		HttpSession session = request.getSession();
		session.setAttribute("attendee_id", result.getId());
		session.setAttribute("attendee_name", result.getName());
		session.setAttribute("attendee_email", result.getEmail());
		session.setAttribute("attendee_mobile", result.getMobile());
		
		
		response.sendRedirect("attendeeHomePage.jsp");





	}

}
