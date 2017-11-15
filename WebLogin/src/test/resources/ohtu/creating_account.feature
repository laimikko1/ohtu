Feature: A new user account can be created if a proper username and password are given

  Scenario: creation is successfull with a valid password and username
    Given command new user is selected
    When a valid username "liisa" and password "salainen1" and matching password confirmation are entered
    Then a new user is created

  Scenario: creation fails with too short username and valid password
    Given command new user is selected
    When a too short username "aa" and valid password "salainen1" and matching password confirmation are entered
    Then user is not created and error "username should have at least 3 characters" is reported


  Scenario: creation fails with correct username and too short password
    Given command new user is selected
    When  a valid username "mikko" and too short password "sal123" and matching password confirmation are entered
    Then user is not created and error "password should have at least 8 characters" is reported

  Scenario: creation fails with already taken username and valid password
    Given command new user is selected
    When  a taken username "liisa" and valid password "salasana!" and matching password confirmation are entered
    Then user is not created and error "username is already taken" is reported

  Scenario: creation fails when password and password confirmation do not match
    Given command new user is selected
    When  a valid username "mikko" and valid password "salasana1" and nonmatching password confirmation "hienoMutteiSama1" are entered
    Then user is not created and error "password and password confirmation do not match" is reported