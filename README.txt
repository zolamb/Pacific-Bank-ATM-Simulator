Author - Zachary Lamb
Date Created - January, 2017
Description:
	This ATM simulator is a program that was written for my high school
	senior project. It is written in Java with use of SQL and a few
	open source Google API's. Any helper functions found on the internet
	and used for the purposes of this project were acknowledged in a comment
	at the top of the appropriate files.

ATM_Simulator:
	Main function that sets up the UI using 'PageOptions'

PageOptions:
	Contains all UI components/action event handlers and sets them up. 

Pages:
	Generates the pages that are created when a button is clicked.
	It uses UI components from PageOptions to do this.

MailOptions:
	Deals with sending the verification email.

DatabaseOptions:
	Deals with editing MySQL database. It reads and writes to the database
	as neccessary.

All other files handle smaller functionalities neccessary in the project