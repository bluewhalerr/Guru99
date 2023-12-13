Feature: Login in to Guru99 Demo website

  
  Background:
  Given the user in on the login page of Guru99
  
# Positive Testcase - Successfully logged in as Manager with valid credentials
@validCredentials
Scenario: Login Successfully with valid test data
  When the user enters the valid UserID "mngr543347"
  And the user enters the valid Password "mYqAvup"
  And the user clicks on the Login button
  Then the user should be logged in successfully and navigates to the Manager Page

#Negative Testcase-Unsuccessfully logged in as Manager with Invalid credentials
@invalidCredentials
Scenario Outline: Login Unsucesfully with Invalid test data
When the user enters the <UserID> and <Password>
And the user clicks on the Login button
Then the pop-up appears user or password is invalid is shown

Examples:

|UserID       |Password  |
|"mgr538889"  |"gepaqyb" |
|"mngr538889" |"gepaqy"  |
|"mgr538889"  |"gepaqy"  |


@missingCredentials
Scenario Outline: Login Unsucesfully with missing credentials
  When the user enters the valid UserID " " and password as "gepaqyb"
  And the user clicks on the Login button
  Then the pop-up appears user or password is invalid is shown
