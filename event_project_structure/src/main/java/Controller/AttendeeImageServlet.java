package Controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import Service.AttendeeLoginService;
import ServiceImpl.AttendeeLoginServiceImpl;



public class AttendeeImageServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private AttendeeLoginService attendeeLoginService = new AttendeeLoginServiceImpl();
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
		int attendeeId = Integer.parseInt(request.getParameter("id"));
		
		
		String imageData = attendeeLoginService.getAttendeeImageById(attendeeId);
		System.out.println(imageData);

		if (imageData != null) {

			String absolutePath  = "C:/Users/Swetha Nagarajan/eclipse-workspace/event_project_structure/WebContent/"+imageData;
			System.out.println("abs"+ absolutePath);
			File imageFile = new File(absolutePath);

			if (imageFile.exists()) {

				String mimeType = getServletContext().getMimeType(imageFile.getName());
				response.setContentType(mimeType != null ? mimeType : "image/jpeg");


				try (FileInputStream fis = new FileInputStream(imageFile);
					 OutputStream os = response.getOutputStream()) 
				{
					byte[] buffer = new byte[4096];
					int bytesRead;
					System.out.println("Entered");
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
    
}
