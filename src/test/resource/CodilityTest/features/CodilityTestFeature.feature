Feature: Codility Test Feature

  Scenario: Validate the products in the cart
    Given I add four different products to my wish list
    When I view my wishlist table
    Then I find total four selected items in my Wishlist
    When I search for lowest price product	
    And I am able to add lowest price items to my cart
    Then I am able to verify the item to my cart
   