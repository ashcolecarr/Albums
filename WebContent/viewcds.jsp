<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="data.GetRecords"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View CDs</title>
</head>
<body>
<center><h1>CD Information</h1></center>
<ul>
<li><a href="cdinput.jsp">Enter CD Information</a>
<li><a href="viewcds.jsp">View CDs</a>
</ul>
<p/>
<fieldset>
	<legend>Enter the title of the album you wish to edit.</legend>
  	<form action="changealbuminfo" method="post">
    	<center>
    	<label>Enter Album Name to Edit:</label>    
    	    <input type="text" name="oldalbum"/>
    	</label><br/>
    	<br>
    	<label>Artist: 
      		<input type="text" name="artistname"/>
    	</label><br/>
    	<label>Album: 
      		<input type="text" name="albumname"/>
    	</label><br/>
    	<label>Year: 
      		<input type="text" name="year"/>
    	</label><br/>
    	<label>Tracks (each track on a new line):<br/>
    		<textarea rows="20" cols="75" name="tracklist"></textarea>
    	</label><br/>
    		<input type="submit" value="Create New Album"/>
    	</center>
  	</form>
</fieldset>
<p/>

<%! GetRecords get = new GetRecords(); %>
<% get.printRecords(out); %>
</body>
</html>