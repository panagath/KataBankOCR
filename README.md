# KataBankOCR
Java Solution to the KataBankOCR problem

-------------------------------------------------------------------------------
User Story 1
-------------------------------------------------------------------------------

You work for a bank, which has recently purchased an ingenious machine to
assist in reading letters and faxes sent in by branch offices. The machine
scans the paper documents, and produces a file with a number of entries which
each look like this:


	  _  _     _  _  _  _  _
	| _| _||_||_ |_   ||_||_|
	||_  _|  | _||_|  ||_| _|
		

Each entry is 4 lines long, and each line has 27 characters. The first 3
lines of each entry contain an account number written using pipes and
underscores, and the fourth line is blank. Each account number should have
9 digits, all of which should be in the range 0-9. A normal file contains
around 500 entries.

Your first task is to write a program that can take this file and parse it
into actual account numbers.

-------------------------------------------------------------------------------
Solution
-------------------------------------------------------------------------------
In this solution I take as granted that we have read the file and have all its
lines put in a List<String>.

You can use the Test class to see how this works.

-------------------------------------------------------------------------------
Contact
-------------------------------------------------------------------------------
Panos

Contact me at ecepanos@gmail.com