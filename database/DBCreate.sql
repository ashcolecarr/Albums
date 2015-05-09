/* SQL to create the album and tracklist tables. */

CREATE TABLE Album(
	AlbumID		INTEGER			NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	AlbumName	VARCHAR(150)	NOT NULL,
	ArtistName	VARCHAR(150)	NOT NULL,
	PubYear		DATE			NOT NULL,
	CONSTRAINT	AlbumPK			PRIMARY KEY(AlbumID),
	CONSTRAINT	AlbumAK			UNIQUE(AlbumName, ArtistName));
	
CREATE TABLE Tracklist(
	TrackID		INTEGER			NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	TrackName	VARCHAR(150)	NOT NULL,
	TrackNo		INTEGER			NOT NULL,
	AlbumID		INTEGER			NOT NULL,
	CONSTRAINT	TrackPK			PRIMARY KEY(TrackID),
	CONSTRAINT	AlbumFK			FOREIGN KEY(AlbumID) REFERENCES Album(AlbumID) ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT	ValidTrackNo	CHECK(TrackNo > 0));
	
/* Statement to insert test data into the tables. */

INSERT INTO Album (AlbumName, ArtistName, PubYear) VALUES ('Dummy', 'Test', '1979-01-01');
INSERT INTO Tracklist (TrackName, TrackNo, AlbumID) VALUES ('Dummy Track', 1, 8); 

INSERT INTO Album (AlbumName, ArtistName, PubYear) VALUES ('Dummy', 'Data', '1979-01-01');
INSERT INTO Album (AlbumName, ArtistName, PubYear) VALUES ('Data', 'Test', '1979-01-01'); 

/* Test data was entered correctly. */

SELECT * FROM Album;
SELECT * FROM Tracklist;

DELETE FROM Tracklist;
DELETE FROM Album;
