## Description:
        
This project contains UI tests based 
on Selenium 4 and covers big part of saucedemo.com website functionality.

###Stack that was used:

Maven, TestNG, Allure, Selenium 4, Docker

###Parameters for running

You can set your bunch of parameters by changing value inside testng.xml file.

Allowed values for browser can be "chrome", "firefox" or "edge".

All values of other parameters you can define intuitively by exploring of saucedemo.com and this project.

###How to run tests

I used Selenium 4 Dynamic Grid concept to run this project remotely in container.
To do so, you should have at least Docker on your OS(with exposing daemon on TCP - on), Git and Java IDE of course.

Steps:
1. Pull this project to your machine.
2. Start docker container being in project folder from powershell by next command:

        docker run --rm --name selenium-docker -p 4444:4444 `
        -v ${PWD}/config.toml:/opt/bin/config.toml `
        -v ${PWD}/assets:/opt/selenium/assets `
        -v /var/run/docker.sock:/var/run/docker.sock `
        selenium/standalone-docker:4.5.0

3. Run tests from testng.xml

####Bonus

You can see test report visually in browser.
After running tests you will have folder with name "allure-results".
Please copy full path of this folder and execute next command in terminal :

`allure serve [full path of allure-results]`
