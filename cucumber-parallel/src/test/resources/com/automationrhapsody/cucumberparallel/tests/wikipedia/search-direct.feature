Feature:

  Scenario: direct search for good article
    Given Enter search term 'Freddie Mercury'
    When Do search
    Then Single result is shown for 'Freddie Mercury'
    And This is good article

  Scenario: direct search for not good article
    Given Enter search term 'Cucumber'
    When Do search
    Then Single result is shown for 'Cucumber'
    And This is not good article