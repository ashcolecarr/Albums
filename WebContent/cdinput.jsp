<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Enter Album Information</title>
</head>
<body>
<center><h1>New CD</h1></center>
<p/>
<ul>
<li><a href="cdinput.jsp">Enter CD Information</a>
<li><a href="viewcds.jsp">View CDs</a>
</ul>
<fieldset>
	<legend>Enter Album Information</legend>
  	<form action="newalbuminfo" method="post">
    	<center>
    	<label>Artist: 
      		<input type="text" name="artistname" value="${CDData.artistName}"/>
    	</label><br/>
    	<label>Album: 
      		<input type="text" name="albumname" value="${CDData.albumName}"/>
    	</label><br/>
    	<label>Year: 
      		<input type="text" name="year" value="${CDData.year}"/>
    	</label><br/>
    	<label>Tracks (each track on a new line):<br/>
    		<textarea rows="20" cols="75" name="tracklist"></textarea>
    	</label><br/>
    		<input type="submit" value="Create New Album"/>
    	</center>
  	</form>
</fieldset>
<p/>

<br/><br/><br/><br/><br/>

</body></html>