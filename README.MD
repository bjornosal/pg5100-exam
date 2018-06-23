# PG5100 - Exam Sprint 2018

[The exam text can be read here.](/PG5100-exam-2018.pdf "Exam text")

All exercises have been finished, including extra. See extra functionality.

Comments to solution for Used Books Web App

The solution is re-using a lot of the classes from the course.
Such as:
- WebSecurityConfig.
- RedirectForwardHandler
- SeleniumDriverHandler
- SeleniumDriverHandler
- SeleniumTestBase.
- LocalApplicationRunner.
- PageObject

- Backend
-- Entities

- Book
I keep a list of sellers per book, as a user does not need to say they have a book.
- Message
I keep information regarding the sender and receiver on the message itself.
- User
A user will keep information on which messages they have sent and received.

-- Services

- DefaultDataInitializerService
I am using the same concept as in the course. I attempted to set up a create script with flyway,
but I was unable to do so and decided this was the easiest solution for this task.

- Backend Testing
Passed 70% coverage on backend in JaCoCo.

- Frontend
All functionality from tasks in E3 has been added.
Including error messages to users if no books available instead of a table etc.

- Frontend Testing (Selenium)
All tests has been written as per description.
All tests are located in SeleniumTestBase so that
I can implement them with Docker at a later time.
Could have been refactored a lot.
Code coverage of 91%.

- Extra Functionality
-----
Added possibility of creating an admin user. Method is called createAdmin and can be found in
UserService. The unit test can be found in UserServiceTest.

Added possibility of registering books from GUI, only for administrators.
The Selenium test for it is called testAddBook() and can be found in SeleniumTestBase

Added possibility of deleting books from GUI, only for administrators.
The Selenium test for it is called testDeleteBook() and can be found in SeleniumTestBase

Added some general CSS to make it look nicer (I realise I'm not a designer now).

Deployed application to Heroku, is fully operational on:
https://pg5100-704148.herokuapp.com
