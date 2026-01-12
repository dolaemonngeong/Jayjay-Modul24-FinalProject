@api
Feature: Automation REST API DummyAPI

  @test-positive
  Scenario: Get list of users
    Given user sends GET request to get all users
    Then response status code should be 200
    And response body should match json schema "getAllUsers.json"

  @test-positive
  Scenario: Get detail user from id
    Given user sends GET request using id "69648355fbf24345c1ba1d71"
    Then response status code should be 200
    And response body should match json schema "userDetail.json"

  @test-positive
  Scenario: Create a new user
    Given user sends POST request to create an account
    Then response status code should be 200
    And response body should match json schema "userDetail.json"

  @test-positive
  Scenario: Update all user information
    Given user sends PUT request to update all the information for user with id "69648355fbf24345c1ba1d71"
    Then response status code should be 200
    And response body should match json schema "userDetail.json"

  @test-positive
  Scenario: Delete a user
    Given user sends DELETE request using id "69649b59d325b30764fdfbd2"
    Then response status code should be 200
    And response body should contain deleted user id "69649b59d325b30764fdfbd2"


  @test-positive
  Scenario: Get list of tags
    Given user sends GET request to get list of tags
    Then response status code should be 200
    And response body should contain a data array
