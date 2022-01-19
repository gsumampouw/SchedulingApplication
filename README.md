## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [How to run](#how-to-run)

## General info
* Title: Desktop Scheduling Application
* Purpose: To create a GUI based scheduling desktop application.
* Author: Gaby Sumampouw
* Contact Info: Email - gsumampo@wgu.edu
* Date: 01/13/2022

## Technologies
* IDE: IntelliJ Community 2021.1.3
* JDK: JDK 11
* JAVAFX:javafx-sdk-11.0.2
* MySQL: mysql-connector-java-8.0.26
* JUnit-jupiter-5.8.2
* Mockito
* Log4J 2

## How to run
Install JDK11, javafx-sdk-11.0.2, IntelliJ Community 2021.1.3 and mysql-connector-java-8.0.26. If you start the application, you will see the login page. 
To create a new user, click the "New User" button in the top right corner. Login with the new user credentials to go to the home page. The application logs user login attempts to a login_activity.txt file and alert the user if an appointment is starting within 15 minutes of login. 
The home page shows all customers in the database.
From the home page you can add, update or delete a customer. You must update a customer in order to add, update or delete an appointment. Appointments are shown according to user local time. 
From the home page you can navigate to three different reports. The "Report: Customer Appointments" button will take you to a report that shows the total number of customer appointments by type and month.
The "Report: Contact Schedule" button will take you to a report that shows a list of appointments depending on the contact selected. 
The "Report: User Schedule" button will take you to a report that shows a list of appointments depending on the user selected. 
This project utilized junit and mockito for unit testing of select methods. Right click on the "test.schedulemanager.services" package and click "Run..." to run unit tests.



