
Feature: BMI Calculator

  Scenario: Calculate BMI successfully
    Given I open the BMI calculator page
    When I enter age "30" height "175" and weight "75"
    And I click Calculate
    Then I should see the BMI result displayed
