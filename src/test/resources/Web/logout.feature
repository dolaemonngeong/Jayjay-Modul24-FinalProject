@web
Feature: Logout
  Background:
    Given user is on login page
    And user input username with "standard_user"
    And user input password with "secret_sauce"
    And user click login button

  @test-positive
  Scenario: logout from the website
    Given user is on home page
    When user click burger menu
    And logout button displayed
    And user click logout button
    Then user should be directed to login page
