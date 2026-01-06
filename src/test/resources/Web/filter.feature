@web
Feature: Filter
  Background:
    Given user is on login page
    And user input username with "standard_user"
    And user input password with "secret_sauce"
    And user click login button

  @test-positive
  Scenario: Show products sorted by name (A to Z)
    Given user is on home page
    When user clicks "Name (A to Z)"
    Then user can see the first product named "Sauce Labs Backpack"
    And user can see the last product named "Test.allTheThings() T-Shirt (Red)"

  @test-positive
  Scenario: Show products sorted by name (Z to A)
    Given user is on home page
    When user clicks "Name (Z to A)"
    Then user can see the first product named "Test.allTheThings() T-Shirt (Red)"
    And user can see the last product named "Sauce Labs Backpack"

  @test-positive
  Scenario: Show products sorted by price (low to high)
    Given user is on home page
    When user clicks "Price (low to high)"
    Then user can see the first product with price "$7.99"
    And user can see the last product with price "$49.99"

  @test-positive
  Scenario: Show products sorted by price (high to low)
    Given user is on home page
    When user clicks "Price (high to low)"
    Then user can see the first product with price "$49.99"
    And user can see the last product with price "$7.99"