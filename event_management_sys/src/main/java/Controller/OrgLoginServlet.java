package Controller;

import java.io.IOException;

import Dao.OrgLoginDao;
import Model.Organizer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;




public class OrgLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrgLoginServlet()
	{
        super();

    }
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try 
		{
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			  if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
		            response.sendRedirect("orgLogin.jsp?error=invalidEmail");
		            return;
		        }

			System.out.println("email "+email);
			System.out.println("password "+password);
			OrgLoginDao obj = new OrgLoginDao();
			
			Organizer result = obj.loginByEmailRole(email);
			if(result==null|| !result.getPassword().equals(password)) 
			{
				System.out.println("failed");
			response.sendRedirect("orgLogin.jsp?error=invalid");
			return;
			
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("organizer_id", result.getId());
			session.setAttribute("organizer_name", result.getName());
			session.setAttribute("organizer_email", result.getEmail());
			session.setAttribute("organizer_phone", result.getPhone());
			session.setAttribute("organization_name", result.getOrganizationName());
			
			response.sendRedirect("orgHomePage.jsp");
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			response.sendRedirect("orgLogin.jsp?error=invalid");
		}
		





	}

}
