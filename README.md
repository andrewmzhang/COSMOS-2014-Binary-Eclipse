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
	is in kelvins, radius is arbitrary (units can be whatever you want it to be).
	
	This light curve simulator is better than a lot of those found on the internet,
	as this one takes into account a phenomena called the 'Limb Darkening Effect'. The
	Limb Darkening Effect is an optical effect seen in stars, that causes the center
	part of the disk (imagine a star being a circle) appears brighter than its edge
	or limb. When an eclipsing binary light curve simulator does not take into account
	limb darkening effect, their graphs will not be accurate, especially when the 
	two stars vary greatly in size (radius wise). Try using (4000,3000,3000,4000) as a
	set of inputs, respectively. Notice how in the light curve there is a curved dip
	and a flat-bottomed dip. This is limb darkening effect kicking in. Other online
	simulators will not generate this effect. 
	
	This simulator does not take in other variables that affect binary stars. First
	of all, the orbits are assumed to be circular, and spaces such that each dip will
	be 1/4th of the total period. Again, the point of this simulator was to visually 
	illustrate binary light curves, not to be the ultimate binary eclipse simulator. 
	
 	Be sure to choose a valid folder location using 'Browse', or the graph will
 	not generate. (Default location is C:/). 
 	
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
	
	My Lord the math!! It's horribly wrong! Hopefully ill get around to fixing it, but its pretty bad.
	
	Browse button does not work for MAC OSX
	Program will not load input boxes correctly on laptops with small screens. 
	
	
	If you find a bug, or have questions, please 
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


