Feature: Feature File for Put method

Scenario: Post and then update the details
Given Accept the content in JSON format
And Content type as JSON
When Post request is performed with BrandName as "Dell", Features as "8GB RAM, 1TB Hard Drive", Laptop as "Orange"
Then status code "200" should return
And response should have the integer Id
When Put request is performed with id and details like BrandName as "Dell", Features as "8GB RAM, 1TB Hard Drive, TouchPad", Laptop as "Orange"
Then status code "200" should return
And details should get updated