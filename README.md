# Project Title

dowlabordere's app for testing Htec Sandbox Qa application

## Getting Started and execute tests

After cloning project applications could be started in few ways:
- mvn clean install
- run testng.xml file in root will also start tests

## Useful notes

- deleted use cases can be retrieved from scenarios.json file
- there is also backup of scenarios.json file called scenariosBACKUP.json file
- webdriver will be closed after test is done

## Expected behaviour

- All of use cases will be copied to json file and then they will be deleted
- From json file use cases will be recovered
- Use cases will be edited with randomly selected string
- Edited use cases will be then deleted (due to fact that every use case is edited then all use cases will be deleted)
- Exit (driver.close())

## Built with

- Java
- Selenium
- Maven
- TestNG
