@web
Feature: Checkout
  Background:
    Given the user is logged in
    And user clicks cart icon button on the first item


  @test-positive
  Scenario: Successfully checkout with valid inputs
    Given user is on home page
    And user clicks cart icon button
    And user is on cart page
    When user clicks the checkout button
    And user is on checkout first page
    And user input first name with "Davina"
    And user input last name with "Wijaya"
    And user input Zip code with "123456"
    And user clicks the continue button
    Then user is on checkout second page
    And user sees the total price and the items to checkout
    When user clicks the finish button
    Then user sees the checkout complete message

  @test-negative
  Scenario: Validate user cancels checkout
    Given user is on home page
    And user clicks cart icon button
    And user is on cart page
    When user clicks the continue shopping button
    Then user should be directed to home page

  @test-boundary
  Scenario Outline: Validate user cannot checkout with empty fields
    Given user is on home page
    And user clicks cart icon button
    And user is on cart page
    When user clicks the checkout button
    And user is on checkout first page
    And user input first name with "<firstName>"
    And user input last name with "<lastName>"
    And user input Zip code with "<zip>"
    And user clicks the continue button
    Then the system displays the error message "<expectedResult>"

    Examples:
      | firstName | lastName | zip   | expectedResult                     |
      |           |          |       | Error: First Name is required      |
      | Davina    |          |       | Error: Last Name is required       |
      | Davina    | Wijaya   |       | Error: Postal Code is required     |