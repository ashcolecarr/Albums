package data;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.springframework.context.*;
import org.springframework.web.context.support.*;

public class InputForm extends HttpServlet 
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext((getServletContext()));
		context.getBean("CDData"); 
		String address = "/cdinput.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
}
