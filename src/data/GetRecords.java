package data;

import java.util.ArrayList;
import java.io.IOException;

/**
 * Gets all the records and prints them out.
 * @author Ashley Coleman
 *
 */
public class GetRecords 
{
	public void printRecords(javax.servlet.jsp.JspWriter out) throws IOException
	{
		MusicDatabaseAccess mdb = new MusicDatabaseAccess();
		ArrayList<AlbumInfo> allCDs = mdb.getAlbums();
		
		for(int i = allCDs.size() - 1; i >= 0; i--)
		{
			out.println("<b>Album: </b>" + allCDs.get(i).getAlbumName() + "<br>");
			out.println("<b>Artist: </b>" + allCDs.get(i).getArtistName() + "<br>");
			out.println("<b>Year: </b>" + allCDs.get(i).getYear() + "<br>");
			out.println("<b>Tracklist: </b><br>");
			
			for(int j = 0; j < allCDs.get(i).getTracks().length; j++)
			{
				out.println(allCDs.get(i).getTrackNumbers()[j] + ". ");
				out.println(allCDs.get(i).getTracks()[j] + "<br>");
			}
			
			out.println("<br>");
		}
	}
}
