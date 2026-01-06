@web
Feature: Login

  @test-negative
  Scenario: Login using invalid username and password
    Given user is on login page
    And user input username with "std"
    And user input password with "secretsauce"
    When user click login button
    Then user see error message "Epic sadface: Username and password do not match any user in this service"

  @test-negative
  Scenario: Login using user that has been locked out
    Given user is on login page
    And user input username with "locked_out_user"
    And user input password with "secret_sauce"
    When user click login button
    Then user see error message "Epic sadface: Sorry, this user has been locked out."

  @test-boundary
  Scenario: Login using empty username and password
    Given user is on login page
    And user input username with ""
    And user input password with ""
    When user click login button
    Then user see error message "Epic sadface: Username is required"

  @test-positive
  Scenario Outline: Login using valid username and password
    Given user is on login page
    And user input username with "<username>"
    And user input password with "<password>"
    When user click login button
    Then user should be directed to home page
    Examples:
      | username                | password      |
      | standard_user           | secret_sauce  |
      | problem_user            | secret_sauce  |
      | performance_glitch_user | secret_sauce  |
      | error_user              | secret_sauce  |
      | visual_user             | secret_sauce  |