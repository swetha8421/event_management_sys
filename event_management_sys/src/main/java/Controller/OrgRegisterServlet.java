package Controller;

import java.io.IOException;
import java.io.InputStream;

import Dao.OrgRegisterDao;
import Model.Organizer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig
public class OrgRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public OrgRegisterServlet() {
        super();

    }
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String organizationName = request.getParameter("organization");
		Part image = request.getPart("image");
		
		
		if(name == null || email == null || password == null ||
				phone == null || organizationName == null ) 
		{
			response.sendRedirect("orgRegister.jsp?error=emptyFields");
	        return;
		}
		
		 if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
		        response.sendRedirect("orgRegister.jsp?error=invalidEmail");
		        return;
		    }
		 if (!phone.matches("\\d{10}")) {
		        response.sendRedirect("orgRegister.jsp?error=invalidPhone");
		        return;
		    }
		
		
		
		 InputStream imageStream = null;
		    if (image != null && image.getSize() > 0) {
		        String contentType = image.getContentType();
		        long size = image.getSize();

		        if (!contentType.startsWith("image/")) {
		            response.sendRedirect("orgRegister.jsp?error=invalidImageType");
		            return;
		        }
		        if (size > 2 * 1024 * 1024) { // 2MB limit
		            response.sendRedirect("orgRegister.jsp?error=imageTooLarge");
		            return;
		        }
		        imageStream = image.getInputStream();
		    }
		
		
		
		
		Organizer organizer = new Organizer();

		organizer.setName(name);
		organizer.setEmail(email);
		organizer.setPassword(password);
		organizer.setPhone(phone);
		organizer.setOrganizationName(organizationName);
		
		
		OrgRegisterDao dao = new OrgRegisterDao();
		boolean result = dao.insertOrganizer(organizer,imageStream);

		if(result) {
			response.sendRedirect("orgLogin.jsp");
		}
		else {
			response.sendRedirect("orgRegister.jsp?error=failed");
		}


	}

}
