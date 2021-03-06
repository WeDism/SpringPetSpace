[![Build Status](https://travis-ci.org/WeDism/SpringPetSpace.svg?branch=master)](https://travis-ci.org/WeDism/SpringPetSpace) 
[![codecov](https://codecov.io/gh/WeDism/SpringPetSpace/branch/master/graph/badge.svg)](https://codecov.io/gh/WeDism/SpringPetSpace)

# This is a social network for pets. It may be tested here: [SpringPetSpace](https://spring-pet-space.herokuapp.com) 
## Table of contents
1. [How to run app](#the-following-steps-are-required-for-running-the-application)
1. [Tested systems](#tested-systems)
1. [Tested browsers](#tested-browsers)
1. [Tutorial](#tutorial)
    1. [Intro](#intro-to-design)
        1. [Use Case diagram](#use-case-diagram)
        1. [Logical DB Schema](#logical-db-schema)
        1. [Physical DB Schema](#physical-db-schema)
    1. [How to use Pet Space](#how-to-use-pet-space)
        1. [Sign Up and Sign In](#sign-up-and-sign-in)
        1. [USER role](#user-role)
            1. [USER role intro](#user-role-intro)
            1. [How to add pet](#how-to-add-pet)
            1. [How to find people and send them friend requests](#how-to-find-people-and-send-them-friend-requests)
            1. [How to approve your friend request](#how-to-approve-your-friend-request)
            1. [How to write a message to your friend](#how-to-write-a-message-to-your-friend)
            1. [How to mark a message as read](#how-to-mark-a-message-as-read)
            1. [How to view another profile](#how-to-view-another-profile)
        1. [ADMIN role](#admin-role)
            1. [ADMIN role intro](#admin-role-intro)
            1. [How to add pet](#how-to-add-pet)
            1. [How to find people and send them friend requests](#how-to-find-people-and-send-them-friend-requests)
            1. [How to approve your friend request](#how-to-approve-your-friend-request)
            1. [How to write a message to your friend](#how-to-write-a-message-to-your-friend)
            1. [How to mark a message as read](#how-to-mark-a-message-as-read)
            1. [How to view another profile](#how-to-view-another-profile)
            1. [How to add new species](#how-to-add-new-species)
        1. [ROOT role](#root-role)
            1. [ROOT role intro](#root-role-intro)
            1. [How to view another profile](#how-to-view-another-profile)
            1. [How to add new species](#how-to-add-new-species)
            1. [How to delete user](#how-to-delete-user)
            1. [How to change user role](#how-to-change-user-role)
            1. [How to add new species](#how-to-add-new-species)



## The following steps are required for running the application
1. [Download](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and install java 9
1. [Download](https://www.postgresql.org/download/) and install postgres 10
1. [Download](https://tomcat.apache.org/download-80.cgi#8.5.31) and install tomcat 8.5
1. [Download](https://github.com/WeDism/CRUDPetSpace/releases) latest war file from releases
1. Deploy SpringPetSpace-1.0.war by tomcat 8.5
1. cd $YOUR_PATH\apache-tomcat-8.5.24\webapps\CRUDPetSpace-1.0\WEB-INF\classes
1. Find schema.sql
1. Uncomment CREATE DATABASE pet_space; in schema.sql file and run sql script
1. Find data.sql and run sql script

## Tested systems
1. Windows 8
1. [Heroku](https://www.heroku.com/) (PaaS)

## Tested browsers
1. Google chrome desktop 64 version and above
1. Google chrome mobile 64 version and above

## Tutorial
### Intro to design
#### Use Case diagram
This is social network service [SNS](https://en.wikipedia.org/wiki/Social_networking_service).
This system contains three type of user essences: __USER__, __ADMIN__ and __ROOT__. These essences extends is an abstract essence. 
The __ROOT__ essence is main and most credential user. This essence may be management other essences. 
The __USER__ is a most popular essences because it's contains main functions such as find friends and adding friend, 
send messages few friends each shipment new message, follow and unfollow pets. The __ADMIN__ essence extend the __USER__ 
and adds a ___${Add species}___ function. See all [use cases](https://en.wikipedia.org/wiki/Use_case) at the next diagram. 

![Diagram of Use Case the social network](/design/UML/UseCase.png)

#### Logical DB schema
DB schema build of using [IDEF1X](https://en.wikipedia.org/wiki/IDEF1X) data modeling language. 
This logical db scheme was develop with restrictions which helps implements integrity of the database. 
See the details in the following scheme. 

![Logical DB Schema](/design/DB/Model_BD.png)

#### Physical DB schema
In this project was tested postgres DBMS physical schema in the following image you can see physical diagram. 

![Physical DB Schema](/readme_images/db/physical_db.png)

This physical db scheme was develop with restrictions which helps implements integrity of the database. 

### How to use Pet Space
#### Sign Up and Sign In
Go to [Pet Space](https://spring-pet-space.herokuapp.com). You are see next image. 

![Sign In](/readme_images/sign_up_and_sign_in/sign_in.png)

Click to ___${Sign Up}___ button. Fill registration data. You can see example in the next image. If you enter verified data. 
You should be see green notification about completed action. Then click on ___${Sign In}___ left button and enter your 
nickname and email. After success registration you have to enter your sign up data and click ___${Sign In}___ button. 
As a result you will sign in __Pet Space__ system.

![Success Sign In](/readme_images/sign_up_and_sign_in/sign_up_and_then_sigin_in.gif)

#### USER Role
##### USER role intro
User role is a base role. The role can use main system capabilities. All capabilities see in [Use Case diagram](#use-case-diagram).

Sign in __Pet Space__ as user essence. After success authentication you should be see the next image. 
Your role should be __USER__. 

![Sign in as user](/readme_images/user/sign_in_as_user.png)

##### How to add pet
To adds your pet click on the ___${Add pet}___ button and fill data. 
Click on the ___${Submit}___ button. If you enter valid data you should be see green ___${Pet added}___ message 
in the left bottom part. If you back to home page you can see your pet in a ___${Your pets}___ table 
of the bottom part your page. 

![Add your pet](/readme_images/user/add_your_pet.gif)

##### How to find people and send them friend requests
To request to friend click on the ___${Find friend}___ button. After open new page fill data as the next image. 

![Filled data of find friend](/readme_images/user/find_friend/filled_data_of_find_friend.png)

If your data was valid you have to see another account. Click on ___${Request}___ checkbox under 
___${Add friend request}___ column. Next you should be see success notification of the top right corner as the next image. 

![Complete friend request](/readme_images/user/find_friend/complete_friend_request.png)

If you back to home page you can see your request of friend in a ___${Your friends}___ table ahead of the your 
___${Your pets}___ table on your page as the next image. 

![Requested friend of your account](/readme_images/user/friend_request/requested_friend_of_your_account.png)

##### How to approve your friend request
Requested account have to change in drop-down list state REQUSTED to APPOVED 
in the table name ___${Your friends}___ and column name ___${Friend state}___ on the home page. 
You can see as the next image. 

![Change drop-down list](/readme_images/user/friend_request/change_drop_down_list_REQUESTED_to_APPOVED.gif)

##### How to write a message to your friend
After success request your friend you have to see changed state REQUSTED to APPOVED as ahead chapter. 
You should be see next presentation. 

![APPROVED state friend request](/readme_images/user/chat/APPROVED_state_friend_request.png)

To send message your friend click ___${Messages}___ link. After change location from ___${Home}___ page to ___${Messages}___ 
page choose one or a lot of accounts and write new message. If you do preview steps you should be see success message 
in the top right corner. These steps are present in the next presentation. 

![Send message my friend](/readme_images/user/chat/send_message_my_friend.gif)

After success send your message your should me see next notification on the right bottom corner. 
Your message notification you can see the next image. 

![Message notification about your message](/readme_images/user/chat/message_notification_about_your_message.png)

##### How to mark a message as read
After get your new message you can mark as read new message with two ways: 
First way click ___${✓}___ on the message notification. See as the next presentations. 

![First way mark as read](/readme_images/user/state_message/first_way_mark_as_read.gif)

Second way click ___${Messages}___ on the top page and all messages will be marked as read. 

![Second way mark as read](/readme_images/user/state_message/second_way_mark_as_read.gif)

##### How to view another profile

![View another profile](/readme_images/user/view_profile/view_profile_another_user.gif)

#### ADMIN Role
##### ADMIN role intro
Admin role extends user. The admin difference of user only one option. 
The option is adds new pet species. Other capabilities see in [Use Case diagram](#use-case-diagram). 

Sign in __Pet Space__ as admin essence. After success authentication you should be see the next image. Your role should be __ADMIN__.  

![Sign in as admin](/readme_images/admin/sign_in_as_admin.png)

##### [How to add pet](#how-to-add-pet)
##### [How to find people and send them friend requests](#how-to-find-people-and-send-them-friend-requests)
##### [How to approve your friend request](#how-to-approve-your-friend-request)
##### [How to write a message to your friend](#how-to-write-a-message-to-your-friend)
##### [How to mark a message as read](#how-to-mark-a-message-as-read)

##### How to add new species
To add new genus pet you should be has ___${ADMIN}___ role. Next go to ___${Home}___ page and click on the 
___${Add genus pet}___ button. After redirect ___${Add genus pet}___ page input new ___${Genus pet}___ name and click 
on the  ___${Submit}___ button. After valid data you have to see success ___${Genus pet added}___ message. New added 
genus you can see in the ___{Genus pet}___ table. If your genus pet is success added you and other users can use your 
genus pet name. See as the next presentation. 

![Add new genus pet](/readme_images/admin/adding_new_genus_pet.gif)

#### ROOT Role
##### ROOT role intro
Root role is a general role. The role can manage other roles such as user and admin. 
Other capabilities see in [Use Case diagram](#use-case-diagram).

Sign in __Pet Space__ as root essence. After success authentication you should be see the next image. 
Your role should be __ROOT__. 

![Sign in as root](/readme_images/root/sign_in_as_root.png)

##### [How to view another profile](#how-to-view-another-profile)
##### [How to add new species](#how-to-add-new-species)
##### How to delete user
Sign in __Pet Space__ and go to home page. Next search ___${Actions}___ column and click needed button in row. 
After successful operation you saw green notification about completed action. See as the next presentation. 

![delete user from system](/readme_images/root/delete_user_from_system.gif)

##### How to change user role
Sign in __Pet Space__ and go to home page. Next search ___${Role}___ column and choose needed option in few variants. 
After successful operation you saw green notification about completed action. See as the next presentation. 

![change role user](/readme_images/root/change_role_user.gif)

##### [How to add new species](#how-to-add-new-species)

