package data;

/**
 * Class to represent artist information to be stored in the database.
 * @author Ashley Coleman
 *
 */
public class AlbumInfo
{
	private String artistName;
	private String albumName;
	private String year;
	private String[] tracks;
	private int[] trackNumbers;
	
	public AlbumInfo()
	{
		
	}
	
	public AlbumInfo(String artistName, String albumName, String year)
	{
		this.artistName = artistName;
		this.albumName = albumName;
		this.year = year;
	}
	
	public String getArtistName() 
	{
		return artistName;
	}
	
	public void setArtistName(String artistName) 
	{
		this.artistName = artistName;
	}
	
	public void setArtistName(String firstName, String lastName)
	{
		this.artistName = lastName + ", " + firstName;
	}
	
	public String getAlbumName() 
	{
		return albumName;
	}
	
	public void setAlbumName(String albumName) 
	{
		this.albumName = albumName;
	}
	
	public String getYear() 
	{
		return year;
	}
	
	public void setYear(String year) 
	{
		this.year = year;
	}
	
	public String[] getTracks() 
	{
		return tracks;
	}
	
	public void setTracks(String[] tracks) 
	{
		this.tracks = tracks;
	}

		public int[] getTrackNumbers() 
	{
		return trackNumbers;
	}

	public void setTrackNumbers(int[] trackNumbers) 
	{
		this.trackNumbers = trackNumbers;
	}
}
