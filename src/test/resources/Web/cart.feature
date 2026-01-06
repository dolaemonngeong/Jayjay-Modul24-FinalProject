@web
Feature: Cart
  Background:
    Given user is on login page
    And user input username with "standard_user"
    And user input password with "secret_sauce"
    And user click login button

  @test-positive
  Scenario: Successfully add an item to the cart
    Given user is on home page
    When user clicks add to cart button on the first item
    Then add to cart button changes to remove button
    And cart icon shows item count

  @test-positive
  Scenario: Successfully remove an item from the cart
    Given user is on home page
    And user clicks cart icon button on the first item
    And user clicks cart icon button
    And user is on cart page
    When user clicks remove button
    Then the cart item "Sauce Labs Backpack" should not be visible
