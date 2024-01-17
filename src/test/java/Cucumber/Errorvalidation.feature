
@tag
Feature: ErrorValidation
  I want to use this template for my feature file



  @Errorvalidation
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce Page
    When Login with username <name> and Password <password>
    Then "Incorrect email password." Message is displayed

    Examples: 
       | name  										| password			| 
      | mamathasaran@gmail.com 		| Mamath@123		|
