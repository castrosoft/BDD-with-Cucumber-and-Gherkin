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

* Building a shared understanding: BDD Requirenments Discovery
- What problems are we trying to solve?  

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

* Guerkin: is at the frontier of specifications and automation
- Basic Gherkin scenarios
- Scenarios and features
- Feature files and directory conventions
- Data tables
- Backgrounds 

//Guerkin
Given Michael is a Morning Freshness member
When he orders an "Apple and Kale Smoothie"
Then his order should be placed in the preparation queue

//Cucumber
@Given("{string} is a Morning Freshness member")
public void a_morning_freshness_member(String memberName){
    registerAMorningFreshnessMember.named(memberName);
}

- Anatomy of a Guerkin scenario
Scenario -> describes the business context or situation/What does the user want to do?/What business rule are you illustrating?
    Given -> preconditions
    When -> action under test
    Then -> postcondition

//Examples
Scenario: 
            Micheal orders a smoothie
            Smoothies should respect health guidelines
            The shop runs out of apples


//More complex scenarios 

- Multiple Given steps (And)

Scenario: Micheal is on a diet
Given Michael is a Morning Freshness member
And Michael is on a low-sugar diet plan
When he orders an "Apple and Kale Smoothie"
Then his order should be placed in the preparation queue

- Multiple Then steps (And & But)

Scenario: Micheal is on a diet
Given Michael is a Morning Freshness member
And Michael is on a low-sugar diet plan
When he orders an "Apple and Mango Smoothie"
Then he should be told that his order is high in sugar
And he shuold be able to modify his order before confirming
But he should be able to confirm his order


* Features and Scenarios

A feature is a piece of useful functionality that we can deliver to our users and that our users will be happy with. 
We describe these features using business rules and examples that we write as SCENARIOS. 
We can group the scenarios together to form user stories 

//Structure features
src/test/resources/features/
                            ordering_smoothies.feature
                            delivering_orders.feature
                            scheduling_orders.feature

Feature: Ordering smoothies

    In order to maintain a healthy lifestyle
    AS a busy professional
    I want to be able to order fresh smoothies to my home each morning

    Scenario: Micheal orders a smoothie
        Given Michael is a Morning freshness member
        When he orders an "Apple and Mango Smoothie"
        Then his order should go to the preparation queue    
    
    Scenario: Micheal is on a diet
        Given Michael is a Morning Freshness member
        And Michael is on a low-sugar diet plan
        When he orders an "Apple and Mango Smoothie"
        Then he should be told that his order is high in sugar

Scenarios in the same feature file often cover similar business rules (So they often share given steps). Now we can use what we call a background section to make these scenarios a little bit more concise.
Every step in the background section will be run before every scenario in the feature file


Feature: Ordering smoothies

    In order to maintain a healthy lifestyle
    AS a busy professional
    I want to be able to order fresh smoothies to my home each morning

    Background
        Given Michael is a Morning freshness member

    Scenario: Micheal orders a smoothie
        When he orders an "Apple and Mango Smoothie"
        Then his order should go to the preparation queue    
    
    Scenario: Micheal is on a diet
        When Michael is on a low-sugar diet plan
        When he orders an "Apple and Mango Smoothie"
        Then he should be told that his order is high in sugar


* Data tables

//Example 1

    Scenario: Micheal gets daily health news updates
        Given Michael is a Morning Freshness member
        And he is interested in Running
        And he is interested in Gym
        And he is interested in Dieting
        When he receives his morning health news updates
        Then the articles should only include things about Running, Gym and Dieting

//Using data table

    Scenario: Micheal gets daily health news updates
        Given Michael is a Morning Freshness member
        And he is interested in the following areas:
            | Running |
            | Gym     |
            | Dieting |
        When he receives his morning health news updates
        Then the articles should only include things about:
            | Running |
            | Gym     |
            | Dieting |

//Example 2

    Scenario: Micheal orders many things
        Given Michael is a Morning Freshness member
        When he orders an "Apple and Mango Smoothie" and a cappuccino and 2 croissants
        Then his order should go to the prep queue

 //Using data table

     Scenario: Micheal orders many things
        Given Michael is a Morning Freshness member
        When he places the following order:
            | Product                       |   Quantity    |
            | Apple and Mango Smoothie      |   1           |
            | Cappuccino                    |   1           |
            | Croissants                    |   2           |
        Then his order should go to the prep queue


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



- Cucumber expressions

Cucumber has 2 ways to associate your scenarios with your test code and pass date bewtween scenarios and your automation methods
You can either use regular expressions or cucumber expressions (more readable)

//Example int

Scenario: Ordering many smoothies
    Given I have ordered 9 smoothies

public class orderingSmoothiesStepDefiniiots {
    @Given("I have ordered {int} smoothies")
    public void iHaveOrdered(int smoothieCount){
        //...
    }
}    

//Example string

Scenario: Ordering many smoothies
    Given I have ordered a "Green Goodness Smoothie"

public class orderingSmoothiesStepDefinitions {
    @Given("I have ordered {string}")
    public void iHaveOrdered(String order){
        //...
    }
}  

//Example int and String

Scenario: Ordering many smoothies
    Given I have ordered 9 "Green Goodness Smoothie"

public class orderingSmoothiesStepDefiniiots {
    @Given("I have ordered {int} {string}")
    public void iHaveOrdered(int count, String order){
        //...
    }
}  

//Example word

Scenario: Ordering smoothies as a Gold member
    Given I am a Gold Morning Freshness member

public class orderingSmoothiesStepDefiniiots {
    @Given("I am a {word} Morning Freshness member")
    public void iAmAMorningFreshnessMember(String memberLevel){
        //...
    }
}  

//Example string and float

Scenario: Ordering a smoothie
    Given a "Strawberry Smoothie" costs $5.95

public class orderingSmoothiesStepDefiniiots {
    @Given("a {string} costs ${float}")
    public void costOfASmoothie(String smoothie, float cost){
        //...
    }
}  

//Example anonymous matcher

Scenario: Ordering a smoothie
    Given Michael is a Morning Smoothie member
    When he orders a Green Goodness Smoothie

public class orderingSmoothiesStepDefiniiots {
    @Given("he orders a {} Smoothie")
    public void orderASmoothie(String smoothie){
        //...
    }
} 








- Regular expressions










//Example

Feature: Super Smoothie Loyalty Card Program

    The more smoothie you buy, the more points you earn.

    Background:
        Given the following drink categories:
            | Drink                 | Category  | Points
            | Apple and Kale        | Regular   | 15
            | Triple Berry Blend    | Fancy     | 20
            | Earl Gray             | Tea       | 10

    Scenario Outline: Earning points when purchasing smoothies
        Given Michael is a Morning Freshness Member            
        When Michael purchases <Quantity> <Drink> drinks
        Then he should earn <Points> points
        Examples:
            | Drink                 | Quantity  | Points
            | Apple and Kale        | 2   | 30
            | Triple Berry Blend    | 1   | 20
            | Earl Gray             | 3   | 30