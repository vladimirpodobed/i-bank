Feature: Calculate home insurance price
  User would like to calculate home insurance monthly payment amount for wooden building

   Scenario: calculation
	  Given I am on the home insurance page
      Then I should see home insurance calculator
	  And should be able to select residential building type
	  And should be able to select wooden building
	  And should be able to enter total area '100'
	  And should be able to calculate monthly payment amount
