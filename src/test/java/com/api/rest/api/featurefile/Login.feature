Feature: User Login feature

Scenario: User login scenario
Given User is on the login page
When user logs in with the following username and password
|username|password|
|usernameOne|passwordOne|
|usernameTwo|passwordTwo|
|usernameThree|passwordThree|
|usernameFour|passwordFour|


Scenario Outline: scenario description
Given a precondition has value "<param_1>"
And something with "<param_2>"
Then check <"param_3"> is the output
Examples:
|param_1|param_2|param_3|
|value1_1|value1_2|value1_3|
|value2_1|value2_2|value2_3|
|value3_1|value3_2|value3_3|