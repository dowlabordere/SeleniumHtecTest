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

## Expected behaviour

- There are 13 use cases written in sandbox application (8 of them are login use cases not automated 
and 5 of them are related to exam and they are automated)
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
