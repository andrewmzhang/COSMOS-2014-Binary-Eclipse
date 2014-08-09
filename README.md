===============
Authors
===============
	
	(Coded at COSMOS UCSC '14)
	Original Java Code by Louis Lu (nickasds@hotmail.com)
	Debug, Revised, and Improved by Andrew Zhang (andrewmzhang12345@gmail.com)

===============
Description
===============
	
	This code, when run, allows one to generate a light curve of a binary star.
	Cicular eclipses are assumed, time is in period phases. Temperature input
	is in kelvins, radius is arbitrary (units can be whatever you want it to be)
	
 	Be sure to choose a valid folder loaction above browse, or the graph will
 	not generate. (Default location is C:\). 
 	
===============
Acknowlegements
===============
	Eclipsing Binaries Group:
		Darragh Hettrick
		Rohit Chopra
		Andrew Zhang
		Louis Lu
		
	Instructors:
		Professor of Astronomy and Astrophysics, Puragra "Raja" Guhathakurta
		Professor of Computer Engineering Tracy Larrabee
		Meredith Muller
		Shivaram Yellamilli
		Jane Li
		John Wright
		Astronomers of the Lick Observatory
	
	COSMOS UCSC 2014 Cluster 7: Astronomy:
		Sumin You
		Eric Wu
		Emily Villa
		Joseph Engelhart
		Jessica Lee
		Irene Duarte
		Timothy Mitchell Chue
		Gina Condotti
		Tiffany Madruga
		Kristi Richter
		Sasha Ruszczyk
		Anooshree Sengupta
		Hariharan Sezhiyan
		Kirby Choy
		Emily Shiang
		Justin Sidhu
		Alyssandra Valenzuela
		Jake Velez
		Cardenas, Andrew
	
		Raffelina Grano
		Dustin Serrano

===============
Developing
===============

	Andrew is currently working:
		Python version of the java code
		jar version of the java code [done]
		exe version of the java code
		Fixing Bugs in the java code (see below)

===============
Known Bugs
===============
	
	There are no currently known bugs. If you find one, or have questions, please 
	email andrewmzhang12345@gmail.com or nickasds@hotmail.com

===============
BUG FIXES
===============

8/6/14: Fixed Graph generating negative percent when run twice

8/6/14: Fixed Graph generating the different graph upon hitting generate twice

8/7/14: Fixed Mathematical Error that caused incorrect slice height generation

8/8/14: Improved a Math equation. Turns out dividing totalMagnitude by 4 yields slightly more accurate 
		values than adding all the vector components of all the faces of a polygon-sphere, whilst kind of
		avoiding Trignometry and complex loops (Andy is very proud of figuring out this simplification) 


