package Controller;

import java.io.File;
import java.io.IOException;


import Service.OrgRegisterService;
import ServiceImpl.OrgRegisterServiceImpl;
import Model.Organizer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig
public class OrgRegisterServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private OrgRegisterService orgRegisterService = new OrgRegisterServiceImpl();

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
			response.sendRedirect("Pages/orgRegister.jsp?error=emptyFields");
	        return;
		}
		
		 if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
		        response.sendRedirect("Pages/orgRegister.jsp?error=invalidEmail");
		        return;
		    }
		 if (!phone.matches("\\d{10}")) {
		        response.sendRedirect("Pages/orgRegister.jsp?error=invalidPhone");
		        return;
		    }
		if (image != null && image.getSize() > 0) {
		        String contentType = image.getContentType();
		        long size = image.getSize();

		        if (!contentType.startsWith("image/")) {
		            response.sendRedirect("Pages/orgRegister.jsp?error=invalidImageType");
		            return;
		        }
		        if (size > 2 * 1024 * 1024) { // 2MB limit
		            response.sendRedirect("Pages/orgRegister.jsp?error=imageTooLarge");
		            return;
		        }
		        
		    }
		
		
		
		String fileName = image.getSubmittedFileName().toString();
		System.out.println("fileName "+fileName);
		
		String uploadsFolder = "C:/Users/Swetha Nagarajan/eclipse-workspace/event_project_structure/WebContent/uploads";
	    System.out.println("Target Save Path: " + uploadsFolder);
	    
	    File uploadsDir = new File(uploadsFolder);
	    if (!uploadsDir.exists()) {
	        uploadsDir.mkdirs(); // create the folder if it doesn't exist
	    }
	    
	    File savedFile = new File(uploadsDir, fileName);
	    image.write(savedFile.getAbsolutePath()); // write the file to disk

	    System.out.println("File saved at: " + savedFile.getAbsolutePath());

	    
	    Organizer organizer = new Organizer();

		organizer.setName(name);
		organizer.setEmail(email);
		organizer.setPassword(password);
		organizer.setPhone(phone);
		organizer.setOrganizationName(organizationName);
		organizer.setFileName(fileName);
		 organizer.setImagePath("uploads/" + fileName); 
		
		
		boolean result = orgRegisterService.insertOrganizer(organizer);

		if(result) {
			response.sendRedirect("Pages/orgLogin.jsp");
		}
		else {
			response.sendRedirect("Pages/orgRegister.jsp?error=failed");
		}


	}

}
