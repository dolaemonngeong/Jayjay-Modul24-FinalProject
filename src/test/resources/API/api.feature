@api
Feature: Automation REST API Gorest

  @test-positive
  Scenario: Get all users
    Given user sends GET request to get all users
    Then response status code should be 200
    And response body should match json schema "getAllUsers.json"

  @test-positive
  Scenario: Get detail user from id
    Given user sends GET request using id 8318861
    Then response status code should be 200
    And response body should match json schema "getOneUser.json"

  @test-positive
  Scenario: Update user status
    Given user sends PATCH request to update user with id 8318861 and status "inactive"
#    When user input status "inactive"
    Then response status code should be 200
    And response body should match json schema "getOneUser.json"

  @test-positive
  Scenario: Update all information
    Given user sends PUT request to update all the information for user with id 8318861
    Then response status code should be 200
    And response body should match json schema "getOneUser.json"

  @test-positive
  Scenario: Get all posts
    Given user sends GET request to get all posts
    Then response status code should be 200
    And response body should match json schema "getAllPosts.json"

  @test-positive
  Scenario: Get all comments
    Given user sends GET request to get all comments
    Then response status code should be 200
    And response body should match json schema "getAllComments.json"