
@tag
Feature: Purchase the order form Ecommerce Website
  I want to use this template for my feature file

	Background:
	Given I landed on Ecommerce Page
	

  @Regression
  Scenario Outline: Positive test of Submitting the order
    Given Login with username <name> and Password <password>
    When Add the valid Product <productName> to the  cart
    And Checkout <productName> and Submit the order
    Then "THANKYOU FOR THE ORDER." message is Displayed on ConfirmationPage

    Examples: 
      | name  										| password			| productName			|
      | mamathasaran@gmail.com 		| Mamatha@123		| ZARA COAT 3			|
   
