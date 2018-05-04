Solution for Used Books Web App

I created an admin (see extra functionality) user for easy testing for your sake.

Application is live (see extra functionality)

E-mail: admin@book.com
Password: admin

The solution is re-using a lot of the classes from the course.
Such as:
- WebSecurityConfig.
- RedirectForwardHandler
- SeleniumDriverHandler
- SeleniumDriverHandler
- SeleniumTestBase.
- LocalApplicationRunner.
- PageObject

- Uncertainties

I had to do a minor configuration on WebSecurityConfig to be able to redirect the user
to the book-detail.jsf page, from localhost:8080, which was not possible without adding "/*".
The user would then be redirected to login instead. The user would be able to go there
if (s)he was at localhost:8080/index.jsf or any other link.

I was unsure if a checkbox was desired for the exam, as you say mark. You did not mention if
it was a checkbox. I wanted to use a button for the case, but I kept a checkbox which will change with
the button as well. It will also give the user a visual feedback on if they have marked it as for sale.

I decided to make my HomeController ApplicationScoped, as to me, it made sense for the application.
If it was SessionScoped it would not be able to live forever, as the ApplicationScoped makes sure of.

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
------
- Sources
- Source used multiple places:
https://www.tutorialspoint.com/jsf/jsf_edit_datatable.htm
To place form outside of datatable to get it to work.
https://stackoverflow.com/questions/35126293/how-to-set-text-into-textarea-with-selenium-webdriver
- https://github.com/arcuri82/testing_security_development_enterprise_systems