package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ArrayList;

/**
 * Class to store and retrieve from the database.
 * @author Ashley Coleman
 *
 */
public class MusicDatabaseAccess 
{
	private Connection connect;
	private Statement statement;
	private PreparedStatement prepared;
	private ResultSet result;
		
	/**
	 * Constructor for the MusicDatabaseAccess class.
	 */
	public MusicDatabaseAccess()
	{
		initializeDatabase();
	}
	
	/**
	 * Initializes the database connection.
	 */
	public void initializeDatabase()
	{
		try
		{
			String url;
			String username;
			String password;
		
			String driver = "org.apache.derby.jdbc.EmbeddedDriver";
			url = "jdbc:derby:C:\\Java Code\\JavaAppFinal\\database;create=true";
			if(driver != null)
			{
				Class.forName(driver);
			}
			username = "";
			password = "";
		
			connect = DriverManager.getConnection(url, username, password);
			
		}
		catch(ClassNotFoundException e)
		{
			System.err.println(e.getMessage());
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Inserts new album and track records into their respective tables.
	 * @param albumInfo the album information
	 */
	public void insertAlbumInfo(AlbumInfo albumInfo)
	{
		try
		{
			int albumID = 0;
			
			// Insert into the album table
			String insertAlbum = "INSERT INTO Album (AlbumName, ArtistName, PubYear) VALUES (?, ?, ?)";
			prepared = connect.prepareStatement(insertAlbum);
			prepared.setString(1, albumInfo.getAlbumName());
			prepared.setString(2, albumInfo.getArtistName());
			prepared.setString(3, albumInfo.getYear());
			prepared.executeUpdate();
			
			// Get the key autonumber from the album table
			String queryAlbum = "SELECT AlbumID FROM Album WHERE AlbumName = ?";
			prepared = connect.prepareStatement(queryAlbum);
			prepared.setString(1, albumInfo.getAlbumName());
			result = prepared.executeQuery();
			if(result.next())
			{
				albumID = result.getInt("AlbumID");
			}
			
			// Insert into the tracklist table
			String insertTracks = "INSERT INTO Tracklist (TrackName, TrackNo, AlbumID) VALUES (?, ?, ?)";
			prepared = connect.prepareStatement(insertTracks);
			
			for(int i = 0; i < albumInfo.getTracks().length; i++)
			{
				prepared.setString(1, albumInfo.getTracks()[i]);
				prepared.setInt(2, i + 1);
				prepared.setInt(3, albumID);
				prepared.executeUpdate();
			}
		}
		catch(SQLException e)
		{
			
		}
	}
	
	/**
	 * Gets all the records in the database.
	 * @return the albumInfo found in the database
	 * @throws SQLException for database errors
	 */
	public ArrayList<AlbumInfo> getAlbums()
	{
		try
		{
			ArrayList<AlbumInfo> albums = new ArrayList<AlbumInfo>();
			AlbumInfo album = new AlbumInfo();
			int albumID = 0;
			String[] tracks;
			int[] trackNumbers;
			int rowCount = 0;
			
			ResultSet subResult;
			ResultSet countResult;
			PreparedStatement subStatement;
			PreparedStatement countStatement;
			
			statement = connect.createStatement();
			String trackSearch = "SELECT TrackName, TrackNo FROM Tracklist WHERE AlbumID = ?";
			String countRows = "SELECT COUNT(*) As RowCount FROM Tracklist WHERE AlbumID = ?";
			subStatement = connect.prepareStatement(trackSearch);
			countStatement = connect.prepareStatement(countRows);
			result = statement.executeQuery("SELECT * FROM Album ORDER BY AlbumName");
			
			while(result.next())
			{
				albums.add(0, new AlbumInfo());
				albumID = result.getInt("AlbumID");
				
				albums.get(0).setAlbumName(result.getString("AlbumName"));
				albums.get(0).setArtistName(result.getString("ArtistName"));
				albums.get(0).setYear(result.getString("PubYear").substring(0, result.getString("PubYear").indexOf("-")));
				
				countStatement.setInt(1, albumID);
				countResult = countStatement.executeQuery();
				if(countResult.next())
				{
					rowCount = countResult.getInt("RowCount");
				}
				
				tracks = new String[rowCount];
				trackNumbers = new int[rowCount];
				
				subStatement.setInt(1, albumID);
				subResult = subStatement.executeQuery();
				int i = 0;
				while(subResult.next())
				{
					tracks[i] = subResult.getString("TrackName");
					trackNumbers[i] = subResult.getInt("TrackNo");
					i++;
				}
				
				albums.get(0).setTracks(tracks);
				albums.get(0).setTrackNumbers(trackNumbers);
				
				System.out.println(album.getAlbumName());
			}
									
			return albums;
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Edits a record in the database.
	 * @param albumInfo the album information to edit
	 * @param albumName the album name to search for
	 * @return an album found in the database
	 */
	public String editAlbum(AlbumInfo albumInfo, String albumName)
	{
		try
		{
			int albumID = 0;
						
			PreparedStatement subStatement1;
			PreparedStatement subStatement2;
						
			statement = connect.createStatement();
			String deleteTracks = "DELETE FROM Tracklist WHERE AlbumID = ?";
			String deleteAlbum = "DELETE FROM Album WHERE AlbumID = ?";
			subStatement1 = connect.prepareStatement(deleteTracks);
			subStatement2 = connect.prepareStatement(deleteAlbum);
									
			String searchString = "SELECT * FROM Album WHERE AlbumName = ?";
			prepared = connect.prepareStatement(searchString);
			prepared.setString(1, albumName);
			result = prepared.executeQuery();
			
			if(result.next())
			{
				albumID = result.getInt("AlbumID");
				
				subStatement1.setInt(1, albumID);
				subStatement1.execute();
				subStatement2.setInt(1, albumID);
				subStatement2.execute();
				
				insertAlbumInfo(albumInfo);
				
				return "Album updated successfully.";
			}
			else
			{
				return "Album not found--no album updated.";
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
