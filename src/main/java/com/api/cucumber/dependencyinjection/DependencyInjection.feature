Feature: Post feature of Facebook

Background: Common step
Given user should be logged in
And he lands on wall

Scenario: Post a text on user wall
Given When I type the text as "This is a sample post" in text box
And click on Post button
Then the message should get posted


