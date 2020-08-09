Feature: To test the post method

@LaptopPost
Scenario: Add a laptop detail to laptop bag
Given Accept the content in JSON format
And Content type as JSON
When Post request is performed with BrandName as "Dell", Features as "8GB RAM, 1TB Hard Drive", Laptop as "Orange"
Then status code "200" should return
And response should have the integer Id

@LaptopPostTransform
Scenario: Add a laptop detail to laptop bag
Given Accept the content in JSON format
And Content type as JSON
When Post request is performed with details "Dell,8GB RAM,1TB Hard Drive,Orange"
Then status code "200" should return
And response should have the integer Id

Scenario: Add a laptop detail to laptop bag with invalid JSON
Given Accept the content in JSON format
And Content type as JSON
But invalid JSON body is supplied
When perform the Post request
Then status code "400" should return

#test scenario
@Test
Scenario: Post a video on user wall
Given user enters the url as "http://www.google.com/"