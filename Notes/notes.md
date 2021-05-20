# 1
BDD = Behavior Driven Development
GHERKIN =
CUCUMBER = 
SERENITY = 

- The BDD requirements discovery process
- Writing requirements in Gherkin (The language of Cucumber)
- Turning Gherkin requirements into automated tests
- How tests can act as documentation for your features

# 2

- What problem does BDD solve?
Business -> BA -> Devs -> QA -> Business

- What is BDD?
Collaboration and conversation
Concrete examples and business rules
A common language and shared understanding
Faster feedback through early and effective automation

- The BDD process
Illustrate
Formulate (Given / When / Then ...)
Automate
Validate

- What is GHERKIN? 
A business-readable notation for executable specifications
Is above all a communication tool

- What is CUCUMBER? 
Automation library
Software you use to run executable specifications written in Gherkin in languages like Java, JS, Ruby or many others

- Gherkin: Executable Business Language
Feature:
    Scenario:   //Each scenario illustrates a business rule
        Given   //Precondition
        And     //Precondition
        When    //It's where all the action happens
        Then    //Describes the outcomes we expected

- Cucumber        
@Given("(.*) is a Morning Freshness member")
public void a_morning_freshness_member(String name)

GHERKIN: Notation we use to write our executable specifications
CUCUMBER: Software we use to automate these executable specifications
APPLICATION: The software interacts with the app that we are testing      


# 3



 # 4
 * Library dependencies
 - Cucumber Java: 
 cucumber-java or cucumber-java8
 cucumber-junit

- A Test Runner
JUnit 4.12

- An Assertion Library
 Hamcrest or  AssertJ

 * Cucumber Dependencies in Maven

 Project Structure
 
 project        // pom.xml
 |
 |\src
 |  |
 |   \main      // application code
 | 
  \test
   |
   |\java       // step definitions and test code
   |
    \resources  //features files


# 5

# 6

- Glue code: Automating Scenarios

- Test Runner Class

@RunWith(Cucumber.class)    
@CucumberOptions(plugin = {"pretty"},   
                 glue = "com.pluralsight.bdd.steps" 
                 features = "classpath:features")
public class CalculatorTest {
}

- @RunWith  //tells JUnit to hand over control to Cucumber when it runs the tests
- plugin    //Lets us specify configuration options such as where Cucumber should look for the features files in the glu code
- glue      //Lets you define the package where Cucumber will look for your step definitions. By default, this will be the same package as your feature file, but many teams find it more readable to place their features in a meaningful directory structure under the /src/test/features directory rather than in a Java pkg structure
- features  //You tell Cucumber to look for feature files on the classPath under the fratures pkg