[![Build Status](https://travis-ci.org/WeDism/SpringPetSpace.svg?branch=master)](https://travis-ci.org/WeDism/SpringPetSpace) 
[![codecov](https://codecov.io/gh/WeDism/SpringPetSpace/branch/master/graph/badge.svg)](https://codecov.io/gh/WeDism/SpringPetSpace)

# This is a social network for pets. You can click for test use next url: [SpringPetSpace](https://spring-pet-space.herokuapp.com) 
## Table of contents
1. [How to run app](#for-run-this-app-you-have-to-do-next-steps)
1. [Tested systems](#tested-systems)
1. [Tutorial](#tutorial)
    1. [Intro](#intro-by-design)
        1. [Use Case diagram](#use-case-diagram)
        1. [Logical DB Schema](#logical-db-schema)
    1. [How Pet Space use](#how-pet-space-use)
        1. [USER role](#user-role)
        1. [ADMIN role](#admin-role)
        1. [ROOT role](#root-role)

## For run this app you have to do next steps
1. [Download](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and install java 9
1. [Download](https://www.postgresql.org/download/) and install postgres 10
1. [Download](https://tomcat.apache.org/download-80.cgi#8.5.31) and install tomcat 8.5
1. [Download](https://github.com/WeDism/CRUDPetSpace/releases) latest war file from releases
1. Deploy SpringPetSpace-1.0.war by tomcat 8.5
1. cd $YOUR_PATH\apache-tomcat-8.5.24\webapps\CRUDPetSpace-1.0\WEB-INF\classes
1. Find schema.sql
1. Uncomment CREATE DATABASE pet_space; in schema.sql file and run sql script
1. Find data.sql and run sql script

### Tested systems
1. Windows 8
1. [Heroku](https://www.heroku.com/) (PaaS)

### Tutorial
#### Intro by design
##### Use Case diagram
This is social network service [SNS](https://en.wikipedia.org/wiki/Social_networking_service).
This system contains three type user essences: __USER__, __ADMIN__ and __ROOT__. These essences extends is an abstract essence.
The __ROOT__ essence are main and most credential user. This essence may be management other essences. 
The __USER__ is a most popular essences because it's contains main functions such as find friends and adding friend, 
send messages few friends each shipment new message, follow and unfollow pets. The __ADMIN__ essence extend the __USER__ 
and adds a ___${Add species}___ function. See all [use cases](https://en.wikipedia.org/wiki/Use_case) at the next diagram.

![Diagram of Use Case the social network](/design/UML/UseCase.png)

##### Logical DB schema
DB schema build of using [IDEF1X](https://en.wikipedia.org/wiki/IDEF1X) data modeling language. 
This logical db scheme was develop with restrictions which helps implements integrity of the database.
See the details in the following scheme.

![DB Schema](/design/DB/Model_BD.png)

#### How Pet Space use
##### Sign Up and Sign In
Go to [Pet Space](https://spring-pet-space.herokuapp.com). You are see next image.

![Sign In](/readme_images/sign_up_and_sign_in/sign_in.png)

Click to ___${Sign Up}___ button. You have to see next image with empty fields.

![Sign Up](/readme_images/sign_up_and_sign_in/sign_up.png)

Fill registration data. You can see example in the next image.

![Sign Up with filled data](/readme_images/sign_up_and_sign_in/filled_sing_up.png)

If you enter verified data. You should be see next image.

![Success Sign Up](/readme_images/sign_up_and_sign_in/success_sign_up.png) 

Click on ___${Sign In}___ left button. Enter your nickname and email.

![Filled Sing In](/readme_images/sign_up_and_sign_in/filled_sign_in.png)

After success registration you have to enter your sign up data and click ___${Sign In}___ button.

![Success Sign In](/readme_images/sign_up_and_sign_in/success_sign_in.png)

##### USER Role

##### ADMIN Role

##### ROOT Role
