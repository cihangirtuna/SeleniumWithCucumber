Feature: Login Action

Scenario: Search with has no response key
	Given User open search page
	When User write to "NoFound" search field
	And User tab to search button
	And Wait "4000" milisec
	And Check zero result message with "NoFound"
	Then Stop test
	
Scenario: Check Result Link Is Clickable
	Given User open search page
	When User write to "television" search field
	And User tab to search button
	And Wait "4000" milisec
	And Get result link after search
	And Check response links are clickable
	Then Stop test
	
Scenario: Check Enter key is working
	Given User open search page
	When User write to "television" search field
	And User tab to enter button
	And Wait "4000" milisec
	And Check current Url is "television"
	Then Stop test
	
Scenario: Check Url after search
	Given User open search page
	When User write to "television" search field
	And User tab to search button
	And Wait "4000" milisec
	And Check current Url is "television"
	Then Stop test
	
Scenario: Check Search More Than One Word
	Given User open search page
	When User write to "4K TV" search field
	And User tab to search button
	And Wait "4000" milisec
	And Check current Url is "4K+TV"
	Then Stop test
	
Scenario: Check Search More Than One Time
	Given User open search page
	When User write to "television" search field
	And User tab to search button
	And Wait "4000" milisec
	And Clear Search Field
	And User write to "radio" search field
	And User tab to search button
	And Wait "4000" milisec
	And Get result link after search
	And Check response links are clickable
	And Check current Url is "radio"
	Then Stop test

Scenario: Check Narrow link is working
	Given User open search page
	When User write to "television" search field
	And User tab to search button
	And Wait "4000" milisec
	And Get result count
	And Click first narrow link set result count
	And Wait "4000" milisec
	And Check result count after narrow
	And Click x button near narrow link
	And Wait "4000" milisec
	And Check Result Count
	Then Stop test
	
Scenario: Check Load More button is working
	Given User open search page
	When User write to "television" search field
	And User tab to search button
	And Wait "4000" milisec
	And Get result link after search
	And Click Load More button
	And Wait "4000" milisec
	And Get result link after click Load More button
	And Check results size is greater after click Load More button
	Then Stop test
	
Scenario: Check search with product name
	Given User open search page
	When User write to "X850D" search field
	And User tab to search button
	And Wait "4000" milisec
	And Check product name is seen at result "X850D"
	Then Stop test