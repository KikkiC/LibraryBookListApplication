# My Personal Project

#### Library Catalog

###### Description of Project:
- Application will check out books and adds it to the list of checked out books. It can also return books and
removes it from the list of checked out books. Latest update includes saving the list of checked out books and 
being able to access it again.

- It will allow users to check out or return books. 

- This project is of interest to me as I have borrowed many books at my local library during my childhood.

------------------------------------------------------------------------------------------------------------------------
###### User Stories:
- As a user, I want to borrow books and add them to the list of checked out books.
- As a user, I want to be able to see **titles of the books** I have borrowed and their **page count**.
- As a user, I want to **check out** books.
- As a user, I want to **return** books.
- As a user, I want to be able to **save** my checked-out books to file.
- As a user, I want to be able to **reload** my checked-out books from file.

------------------------------------------------------------------------------------------------------------------------
###### Information for TA's (How to interact with my application):
- Once launching the application, it will load the information from the books.txt file and will be displayed in the 
table. After that, you can type in the title and pagecount of the book you are borrowing and press the button labelled
as "Borrow". When pressing the "Borrow" button, a sound of a doorbell will come out.
If you want to return a book, select a book item from the table and press the button labelled as "Return". 
Finally, after you have finished borrowing/returning your books, you can press the "Save" button and it will save 
what the table has stored into the books.txt file.
------------------------------------------------------------------------------------------------------------------------
###### Phase 4: Task 2
- I completed the testing and design of CheckReturnBooks. In CheckReturnBooks, the method returnCheckedOutBook throws an
exception called NotCheckedOut whenever the user hasn't borrowed that book. The testing is shown in CheckReturnBooksTest.
------------------------------------------------------------------------------------------------------------------------
###### Phase 4: Task 3
- There is high cohesion in the LibraryCatalogApplication in the ui class, because it has way too much responsibilities.
One of the changes that I have made to the LibraryCatalogApplication is that I extracted out the playSound method to a 
new class and it will call on it when the Borrow button is pressed. The other change that I have made is extracting out
the part of the method that adds JLabels and JTextFields into a panel in the addLabelTextFields method in the 
LibraryCatalogApplication to a new class called AddLabelsTextFields.