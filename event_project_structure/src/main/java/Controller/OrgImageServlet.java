package Controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import Service.OrgLoginService;
import ServiceImpl.OrgLoginServiceImpl;
public class OrgImageServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private OrgLoginService orgLoginService = new OrgLoginServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int organizerId = Integer.parseInt(request.getParameter("id"));
		
		
		String imageData = orgLoginService.getOrganizerImageById(organizerId);
		
		if (imageData != null) {

			String absolutePath  = "C:/Users/Swetha Nagarajan/eclipse-workspace/event_project_structure/WebContent/"+imageData;
			File imageFile = new File(absolutePath);

			if (imageFile.exists()) {

				String mimeType = getServletContext().getMimeType(imageFile.getName());
				response.setContentType(mimeType != null ? mimeType : "image/jpeg");


				try (FileInputStream fis = new FileInputStream(imageFile);
					 OutputStream os = response.getOutputStream()) 
				{
					byte[] buffer = new byte[4096];
					int bytesRead;
					while ((bytesRead = fis.read(buffer)) != -1) 
					{
						os.write(buffer, 0, bytesRead);
					}
				}
			} 
			else 
			{
				// If file not found, show a default image
				response.sendRedirect("default-profile.jpg");
			}
		} 
		else 
		{
			// If no image path found for organizer
			response.sendRedirect("default-profile.jpg");
		}
	}
	
	

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
