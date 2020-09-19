Feature: Test Registration Functionality for automationpractice.com

    Background:
	    Given I Navigate to "AutomationPractice_Homepage"
	    When I click on "SignIn_Link"
    
    Scenario: New user registration process - Happy path validation
	  	
	    When I enter "newEmailAddress" text on "Email_Address_TextBox"
	    When I click on "Create_Account_Button"
	    Then I should navigate to Page having title "Login - My Store"
	    When I register new user
	    When I click on "Register_Button"
	    Then I should navigate to Page having title "My account - My Store"
	    Then I should see "name" text in "NameLink"
	    
	  Scenario: Mandatory fields error message validation on the new user registration form
	    When I enter "newEmailAddress" text on "Email_Address_TextBox"
	    When I click on "Create_Account_Button"
	    Then I should navigate to Page having title "Login - My Store"
	    When I click on "Register_Button"
	    Then I should see "There are 8 errors" text in "Errors"
    
    Scenario Outline: New user registration process with existing email should display appropriate error message
	    When I enter "<already_created_email>" text on "Email_Address_TextBox"
	    When I click on "Create_Account_Button"
	    Then I should see "<error_message>" text in "ErrorMessage_Label"
			Examples:
							|already_created_email		 | error_message |
							|a@a.com									 | An account using this email address has already been registered. Please enter a valid password or request a new one.|
   
   
	  Scenario Outline: Field validations for Email field
	    When I enter "<incorrect_format_email>" text on "Email_Address_TextBox"
	    When I click on "Create_Account_Button"
	    Then I should see "Invalid email address." text in "ErrorMessage_Label"
	    Examples: 
	       			|incorrect_format_email |
				 			|												|
							|a@a										|
							|a											|
							|a.co										|  
							|a@a.										|