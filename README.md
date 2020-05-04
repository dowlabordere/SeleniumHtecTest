# Project Title

dowlabordere's app for testing Htec Sandbox Qa application

## Getting Started and execute tests

After cloning project applications could be started in few ways:
- mvn clean install (will run methods from UseCasesPageTests class sorted by priority)
- run testng.xml file in root will also start methods by same priority
- it can be also started manualy with executing separate methods annotated with @Test annotation

## Useful notes

- deleted use cases can be retrieved from scenarios.json file
- there is also backup of scenarios.json file called scenariosBACKUP.json file
- webdriver will be closed after test is done

## Built with

- Java
- Selenium
- Maven
- TestNG
