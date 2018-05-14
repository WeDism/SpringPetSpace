[![Build Status](https://travis-ci.org/WeDism/SpringPetSpace.svg?branch=master)](https://travis-ci.org/WeDism/SpringPetSpace) 
[![codecov](https://codecov.io/gh/WeDism/SpringPetSpace/branch/master/graph/badge.svg)](https://codecov.io/gh/WeDism/SpringPetSpace)

# This is a social network for pets. You can click for test use next url: [SpringPetSpace](https://spring-pet-space.herokuapp.com) 
## Table of contents
1. [How to run app](#for-run-this-app-you-have-to-do-next-steps)
1. [Tested systems](#tested-systems)
1. [Tutorial](#tutorial)
    1. [Intro](#intro-by-design)
        1. [Use Case diagram](#[use-case-diagram](https://en.wikipedia.org/wiki/use_case))
        1. [IDEF1X DB Schema](#[idef1x](https://en.wikipedia.org/wiki/idef1x)-db-schema)

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
##### [Use Case diagram](https://en.wikipedia.org/wiki/Use_case)
This is social network service [SNS](https://en.wikipedia.org/wiki/Social_networking_service).
This system contains three type user essences: __USER__, __ADMIN__ and __ROOT__. These essences extends is an abstract essence.
The __ROOT__ essence are main and most credential user. This essence may be management other essences. 
The __USER__ is a most popular essences because it's contains main functions such as find friends and adding friend, 
send messages few friends each shipment new message, follow and unfollow pets. The __ADMIN__ essence extend __USER__ adds
function __${Add species}__. See all use cases at the next diagram.

![Diagram of Use Case the social network](/design/UML/UseCase.png)

##### [IDEF1X](https://en.wikipedia.org/wiki/IDEF1X) DB Schema
DB schema build of using [IDEF1X](https://en.wikipedia.org/wiki/IDEF1X) data modeling language. 

