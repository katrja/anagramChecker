# anagramChecker

Requirements:
It's test java program that checks if two texts are anagrams of each other. 
Anagram definition was taken from https://en.wikipedia.org/wiki/Anagram.

As a given requirement, I considered to accept text with numbers and special cherecters, but they are ignored during comparison.
UI is a simple JSP page, it has 2 inputs (not blank and less than 200 symbols, at least one letter considred as word) and click” Submit" button. Result will be shown below the form.

Used technologies:
 - Java 17
 - Spring Boot 3.0.1 framework
 
This app is running in Docker container.

How to run/test it:
Application is deployed in GCP. Push in main repo branch riggers a new cloud deploy, so application will be updated with lastest changes.
It can be accessed by link: https://anagram-continious-deploy-l6wqomjdwa-uc.a.run.app/anagram

To run locally:
- clone this repo
- $ mvn clean install
- $ mvn spring-bot:run

To run tests locally:
- $ mvn test

