package data;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;

/**
 * Reads the album name from the edit form.
 * @author Ashley Coleman
 *
 */
@WebServlet("/changealbuminfo")
public class EditAlbumData extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		AlbumInfo albumInfo = new AlbumInfo();
		MusicDatabaseAccess mdb = new MusicDatabaseAccess();
		String[] tracklist;
		String status = "";
		
		if(request.getParameter("artistname") == "")
		{
			response.setContentType("text/html");
		    PrintWriter out = response.getWriter();
		    String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " + "Transitional//EN\">\n";
		    out.println(docType +
		                "<HTML>\n" +
		                "<HEAD>" + 
		                "<BODY BGCOLOR=\"#FFFFFF\">\n" +
		                "<ul><li><a href=\"cdinput.jsp\">Enter CD Information</a><li><a href=\"viewcds.jsp\">View CDs</a></ul>" +
		                "<P>\n" +
		                "The artist field did not contain any information.  Please try again." + 
		                "</BODY></HTML>");
		}
		else if(request.getParameter("albumname") == "")
		{
			response.setContentType("text/html");
		    PrintWriter out = response.getWriter();
		    String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " + "Transitional//EN\">\n";
		    out.println(docType +
		                "<HTML>\n" +
		                "<HEAD>" + 
		                "<BODY BGCOLOR=\"#FFFFFF\">\n" +
		                "<ul><li><a href=\"cdinput.jsp\">Enter CD Information</a><li><a href=\"viewcds.jsp\">View CDs</a></ul>" +
		                "<P>\n" +
		                "The album field did not contain any information.  Please try again." + 
		                "</BODY></HTML>");
		}
		else if(request.getParameter("year") == "")
		{
			response.setContentType("text/html");
		    PrintWriter out = response.getWriter();
		    String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " + "Transitional//EN\">\n";
		    out.println(docType +
		                "<HTML>\n" +
		                "<HEAD>" + 
		                "<BODY BGCOLOR=\"#FFFFFF\">\n" +
		                "<ul><li><a href=\"cdinput.jsp\">Enter CD Information</a><li><a href=\"viewcds.jsp\">View CDs</a></ul>" +
		                "<P>\n" +
		                "The year field did not contain any information.  Please try again." + 
		                "</BODY></HTML>");
		}
		else if(request.getParameter("tracklist") == "")
		{
			response.setContentType("text/html");
		    PrintWriter out = response.getWriter();
		    String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " + "Transitional//EN\">\n";
		    out.println(docType +
		                "<HTML>\n" +
		                "<HEAD>" + 
		                "<BODY BGCOLOR=\"#FFFFFF\">\n" +
		                "<ul><li><a href=\"cdinput.jsp\">Enter CD Information</a><li><a href=\"viewcds.jsp\">View CDs</a></ul>" +
		                "<P>\n" +
		                "No tracks were added to the tracklist.  Please try again." + 
		                "</BODY></HTML>");
		}
		else 
		{
			albumInfo.setArtistName(request.getParameter("artistname"));
			albumInfo.setAlbumName(request.getParameter("albumname"));
			albumInfo.setYear(request.getParameter("year") + "-01-01");
			
			tracklist = request.getParameter("tracklist").split("\n");
			albumInfo.setTracks(tracklist);
			
			status = mdb.editAlbum(albumInfo, request.getParameter("oldalbum"));
			
			response.setContentType("text/html");
		    PrintWriter out = response.getWriter();
		    String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " + "Transitional//EN\">\n";
		    out.println(docType +
		                "<HTML>\n" +
		                "<HEAD>" + 
		                "<BODY BGCOLOR=\"#FFFFFF\">\n" +
		                "<ul><li><a href=\"cdinput.jsp\">Enter CD Information</a><li><a href=\"viewcds.jsp\">View CDs</a></ul>" +
		                status +
		                "<P>\n" +
		                "CD successfully added." + 
		                "</BODY></HTML>");
		}
	}
}
