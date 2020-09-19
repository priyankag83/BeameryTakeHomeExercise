# Senior Test Automation Engineer - Take Home Exercise

### Design decisions
1. Tech stack used - Java, Selenium WebDriver, Cucumber, JUnit runner.
2. Pattern - simple modular design pattern. 
3. All the object locators have been decoupled from code and has been placed under a seperate file [/Locators/All_Objects.json]
4. All the test input data has been decoupled from code and has been placed under a seperate file [/Data/UsersInfo.json]
5. Environment variables such as application URL etc have been placed under /Env_Vars.json.
6. For now, the code has been executed and tested on Chrome browser (MAC) only.

### Scenarios being tested
1. New user registration process - Happy path validation.
2. Mandatory fields error message validation on the new user registration form.
3. New user registration process with existing email should display appropriate error message.
4. Field validations for Email field.

### Prerequisites:
1. Java JDK
2. Eclipse or any other Java IDE.

### How to run the tests:
1. Clone the git repository - https://github.com/priyankag83/BeameryTakeHomeExercise.git
2. Import the project in Eclipse.
3. Run the project as Maven test.
4. The 'chromedriver' (present at ./Drivers/chromedriver) is the one compatible with latest chrome version 85. chromedriver can be replaced here with the corresponding version if you are not using chrome version 85.

### Troubleshooting:
If you get "Illegal state exception while executing the test and chrome does not comes up or crashes, then you can try changing the permissions on 'chromedriver' by executing the following command from terminal:
chmod +x chromedriver

### Reports
HTML Reports can be found under - target/cucumber-reports/index.html (target folder will be created after Project is run as Maven Test)

### Suggested Improvements (if provided with more time)
1. Design can be improved to incorporate page object model design pattern.
2. The reporting can be integrated with cucumber reporting plugin to generate pretty cucumber reports.
3. The execution has been tested on Chrome on MAC only right now. Can be extended to other browsers like Firefox, Safari, Edge etc and mobile platforms.
4. Addition of More TestCases e.g- Specific field level validations on the new user registration form.